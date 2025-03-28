import os
import re
import logging
from pathlib import Path
from collections import defaultdict

# Configure logging
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)

def analyze_bdd_framework(project_path):
    """
    Analyze a BDD framework project and return metrics and recommendations.
    
    Args:
        project_path (str): Path to the BDD project directory
    
    Returns:
        dict: Analysis results including metrics and recommendations
    """
    logger.debug(f"Starting analysis of project at: {project_path}")
    
    # Validate path exists
    path = Path(project_path)
    if not path.exists():
        raise FileNotFoundError(f"Project path does not exist: {project_path}")
    
    # Initialize results dictionary
    results = {
        'overall_score': 0,
        'feature_files': {
            'count': 0,
            'quality_score': 0,
            'issues': []
        },
        'step_definitions': {
            'count': 0,
            'quality_score': 0,
            'issues': []
        },
        'test_coverage': {
            'score': 0,
            'issues': []
        },
        'framework_structure': {
            'score': 0,
            'issues': []
        },
        'recommendations': []
    }
    
    try:
        # Analyze feature files
        analyze_feature_files(path, results)
        
        # Analyze step definitions
        analyze_step_definitions(path, results)
        
        # Analyze framework structure
        analyze_framework_structure(path, results)
        
        # Calculate test coverage
        calculate_test_coverage(results)
        
        # Generate recommendations
        generate_recommendations(results)
        
        # Calculate overall score
        calculate_overall_score(results)
        
        logger.debug(f"Analysis completed with overall score: {results['overall_score']}")
        return results
    
    except Exception as e:
        logger.error(f"Error during analysis: {str(e)}")
        raise

def analyze_feature_files(project_path, results):
    """Analyze feature files in the project."""
    feature_files = list(project_path.glob('**/*.feature'))
    results['feature_files']['count'] = len(feature_files)
    
    if len(feature_files) == 0:
        results['feature_files']['issues'].append("No feature files found")
        results['feature_files']['quality_score'] = 0
        return
    
    # Metrics for quality assessment
    quality_issues = []
    scenarios_count = 0
    scenarios_with_examples = 0
    scenarios_with_tags = 0
    
    for file_path in feature_files:
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
                
                # Count scenarios
                scenario_matches = re.findall(r'Scenario:', content)
                scenarios_count += len(scenario_matches)
                
                # Count scenarios with examples (Scenario Outline)
                outline_matches = re.findall(r'Scenario Outline:', content)
                scenarios_with_examples += len(outline_matches)
                
                # Count scenarios with tags
                tag_matches = re.findall(r'@\w+\s+Scenario', content)
                scenarios_with_tags += len(tag_matches)
                
                # Check for best practices
                if not re.search(r'Feature:', content):
                    quality_issues.append(f"Missing Feature description in {file_path.name}")
                
                if not re.search(r'Background:', content) and len(scenario_matches) > 1:
                    quality_issues.append(f"Consider using Background for common steps in {file_path.name}")
        
        except Exception as e:
            quality_issues.append(f"Error analyzing feature file {file_path.name}: {str(e)}")
    
    # Calculate quality score based on metrics
    tag_ratio = scenarios_with_tags / max(scenarios_count, 1)
    examples_ratio = scenarios_with_examples / max(scenarios_count, 1)
    
    base_score = 70
    tag_score = min(15, tag_ratio * 15)
    examples_score = min(15, examples_ratio * 15)
    
    quality_score = base_score + tag_score + examples_score
    quality_score = max(0, quality_score - (len(quality_issues) * 5))  # Reduce score for issues
    
    results['feature_files']['quality_score'] = round(min(100, quality_score))
    results['feature_files']['issues'] = quality_issues
    results['feature_files']['metrics'] = {
        'scenarios_count': scenarios_count,
        'scenarios_with_examples': scenarios_with_examples,
        'scenarios_with_tags': scenarios_with_tags
    }

def analyze_step_definitions(project_path, results):
    """Analyze step definition files in the project."""
    # Look for common step definition file extensions in various BDD frameworks
    step_files = []
    for ext in ['.py', '.js', '.ts', '.rb', '.java', '.cs']:
        step_files.extend(list(project_path.glob(f'**/*steps*{ext}')))
        step_files.extend(list(project_path.glob(f'**/*step_definitions*{ext}')))
    
    results['step_definitions']['count'] = len(step_files)
    
    if len(step_files) == 0:
        results['step_definitions']['issues'].append("No step definition files found")
        results['step_definitions']['quality_score'] = 0
        return
    
    # Metrics for quality assessment
    quality_issues = []
    total_steps = 0
    parameterized_steps = 0
    
    # Regular expressions to match step definitions in different languages
    step_patterns = {
        '.py': (r'@(given|when|then|step|and|but)\s*\(\s*[\'"](.+?)[\'"]\s*\)', r'<.*?>'),  # Python (behave, pytest-bdd)
        '.js': (r'(Given|When|Then|And|But)\s*\(\s*[\'"](.+?)[\'"]\s*,', r'<.*?>'),  # JavaScript (cucumber-js)
        '.ts': (r'(Given|When|Then|And|But)\s*\(\s*[\'"](.+?)[\'"]\s*,', r'<.*?>'),  # TypeScript
        '.rb': (r'(Given|When|Then|And|But)\s*\(\s*[\'"/](.+?)[\'"/]\s*\)', r'{.+?}'),  # Ruby (cucumber)
        '.java': (r'@(Given|When|Then|And|But)\s*\(\s*[\'"](.+?)[\'"]\s*\)', r'[{].*?[}]'),  # Java (cucumber-jvm)
        '.cs': (r'\[(Given|When|Then|And|But)\s*\(\s*[\'"](.+?)[\'"]\s*\)', r'[{].*?[}]')  # C# (SpecFlow)
    }
    
    for file_path in step_files:
        ext = file_path.suffix
        if ext not in step_patterns:
            continue
            
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                content = f.read()
                
                # Get appropriate pattern for the file type
                step_pattern, param_pattern = step_patterns[ext]
                
                # Find all step definitions
                steps = re.findall(step_pattern, content, re.IGNORECASE)
                total_steps += len(steps)
                
                # Count parameterized steps
                if isinstance(steps[0], tuple) if steps else False:
                    # For patterns that return tuples (capturing groups)
                    for _, step_text in steps:
                        if re.search(param_pattern, step_text):
                            parameterized_steps += 1
                else:
                    # For patterns that don't have capturing groups
                    for step_text in steps:
                        if re.search(param_pattern, step_text):
                            parameterized_steps += 1
                
                # Check for potential issues
                if len(steps) > 20:
                    quality_issues.append(f"Too many step definitions in {file_path.name}, consider refactoring")
        
        except Exception as e:
            quality_issues.append(f"Error analyzing step file {file_path.name}: {str(e)}")
    
    # Calculate quality score
    param_ratio = parameterized_steps / max(total_steps, 1)
    
    base_score = 60
    param_score = min(40, param_ratio * 40)
    
    quality_score = base_score + param_score
    quality_score = max(0, quality_score - (len(quality_issues) * 5))  # Reduce score for issues
    
    results['step_definitions']['quality_score'] = round(min(100, quality_score))
    results['step_definitions']['issues'] = quality_issues
    results['step_definitions']['metrics'] = {
        'total_steps': total_steps,
        'parameterized_steps': parameterized_steps
    }

def analyze_framework_structure(project_path, results):
    """Analyze the overall structure of the BDD framework."""
    expected_dirs = ['features', 'step_definitions', 'support', 'reports']
    found_dirs = 0
    
    # Check for common BDD framework directories
    for dir_name in expected_dirs:
        if any(d.name.lower() == dir_name.lower() for d in project_path.glob('**') if d.is_dir()):
            found_dirs += 1
    
    issues = []
    
    # Check for config files
    config_files = list(project_path.glob('**/*cucumber*.json')) + \
                   list(project_path.glob('**/*behave*.ini')) + \
                   list(project_path.glob('**/*specflow*.json'))
    
    if not config_files:
        issues.append("No configuration files found for the BDD framework")
    
    # Calculate structure score
    dir_score = (found_dirs / len(expected_dirs)) * 80
    config_score = 20 if config_files else 0
    
    structure_score = dir_score + config_score
    
    results['framework_structure']['score'] = round(min(100, structure_score))
    results['framework_structure']['issues'] = issues
    results['framework_structure']['metrics'] = {
        'expected_directories': expected_dirs,
        'found_directories': found_dirs,
        'has_config': bool(config_files)
    }

def calculate_test_coverage(results):
    """Calculate the test coverage score based on other metrics."""
    feature_score = results['feature_files']['quality_score']
    steps_score = results['step_definitions']['quality_score']
    
    # Simple heuristic to estimate coverage
    if results['feature_files']['count'] == 0 or results['step_definitions']['count'] == 0:
        coverage_score = 0
        issues = ["Missing either feature files or step definitions, cannot determine coverage"]
    else:
        # Calculate ratio of step definitions to feature files as a rough coverage metric
        steps_to_features_ratio = results['step_definitions']['count'] / results['feature_files']['count']
        
        coverage_base = (feature_score + steps_score) / 2
        
        # Adjust based on ratio (ideally around 1:1 or higher)
        if steps_to_features_ratio < 0.5:
            coverage_score = coverage_base * 0.7
            issues = ["Possible insufficient step definitions for feature coverage"]
        elif steps_to_features_ratio > 3:
            coverage_score = coverage_base * 0.9
            issues = ["Many step definitions compared to features - possible over-engineering"]
        else:
            coverage_score = coverage_base
            issues = []
    
    results['test_coverage']['score'] = round(min(100, coverage_score))
    results['test_coverage']['issues'] = issues

def generate_recommendations(results):
    """Generate recommendations based on the analysis results."""
    recommendations = []
    
    # Feature file recommendations
    if results['feature_files']['quality_score'] < 70:
        if 'Missing Feature description' in str(results['feature_files']['issues']):
            recommendations.append("Add proper Feature descriptions to all feature files")
        
        if 'Consider using Background' in str(results['feature_files']['issues']):
            recommendations.append("Use Background sections for common steps in feature files")
        
        if results['feature_files']['metrics'].get('scenarios_with_tags', 0) < results['feature_files']['metrics'].get('scenarios_count', 0) * 0.7:
            recommendations.append("Add more tags to scenarios for better organization and filterable test runs")
    
    # Step definition recommendations
    if results['step_definitions']['quality_score'] < 70:
        if results['step_definitions']['metrics'].get('parameterized_steps', 0) < results['step_definitions']['metrics'].get('total_steps', 0) * 0.5:
            recommendations.append("Increase use of parameterized step definitions to improve reusability")
        
        if 'Too many step definitions' in str(results['step_definitions']['issues']):
            recommendations.append("Refactor large step definition files into smaller, more focused modules")
    
    # Framework structure recommendations
    if results['framework_structure']['score'] < 70:
        if results['framework_structure']['metrics'].get('found_directories', 0) < 3:
            recommendations.append("Improve project structure by organizing into features, step_definitions, and support directories")
        
        if not results['framework_structure']['metrics'].get('has_config', False):
            recommendations.append("Add proper configuration files for your BDD framework")
    
    # General recommendations
    if results['overall_score'] < 50:
        recommendations.append("Consider providing more comprehensive training on BDD practices to the team")
    
    # Add recommendations if we don't have enough
    if len(recommendations) < 2:
        if results['overall_score'] < 90:
            recommendations.append("Document common step definitions to encourage reuse and consistency")
            recommendations.append("Implement continuous integration that runs BDD tests and reports results")
    
    results['recommendations'] = recommendations

def calculate_overall_score(results):
    """Calculate the overall score based on all metrics."""
    feature_weight = 0.3
    step_weight = 0.3
    coverage_weight = 0.25
    structure_weight = 0.15
    
    overall_score = (
        results['feature_files']['quality_score'] * feature_weight +
        results['step_definitions']['quality_score'] * step_weight +
        results['test_coverage']['score'] * coverage_weight +
        results['framework_structure']['score'] * structure_weight
    )
    
    results['overall_score'] = round(overall_score)
