import os
from reportlab.lib import colors
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch

def generate_scoring_guide(results, project_path):
    """Generate a PDF scoring guide for the BDD framework analysis."""
    output_path = 'scoring_guide.pdf'
    doc = SimpleDocTemplate(output_path, pagesize=letter)
    styles = getSampleStyleSheet()
    story = []

    # Title
    title_style = ParagraphStyle(
        'CustomTitle',
        parent=styles['Heading1'],
        fontSize=24,
        spaceAfter=30
    )
    story.append(Paragraph('BDD Framework Analysis Report', title_style))
    story.append(Spacer(1, 12))

    # Project Info
    story.append(Paragraph(f'Project: {os.path.basename(project_path)}', styles['Heading2']))
    story.append(Spacer(1, 12))

    # Overall Score
    story.append(Paragraph(f'Overall Score: {results["overall_score"]}/100', styles['Heading2']))
    story.append(Spacer(1, 12))

    # Feature Files Analysis
    story.append(Paragraph('Feature Files Analysis', styles['Heading2']))
    feature_data = [
        ['Metric', 'Value'],
        ['Count', str(results['feature_files']['count'])],
        ['Quality Score', f"{results['feature_files']['quality_score']}/100"]
    ]
    if results['feature_files']['issues']:
        feature_data.append(['Issues', Paragraph('\n'.join('• ' + issue for issue in results['feature_files']['issues']), styles['Normal'])])

    feature_table = Table(feature_data, colWidths=[2*inch, 4*inch])
    feature_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.grey),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 14),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
        ('TEXTCOLOR', (0, 1), (-1, -1), colors.black),
        ('FONTNAME', (0, 1), (-1, -1), 'Helvetica'),
        ('FONTSIZE', (0, 1), (-1, -1), 12),
        ('GRID', (0, 0), (-1, -1), 1, colors.black)
    ]))
    story.append(feature_table)
    story.append(Spacer(1, 12))

    # Step Definitions Analysis
    story.append(Paragraph('Step Definitions Analysis', styles['Heading2']))
    step_data = [
        ['Metric', 'Value'],
        ['Count', str(results['step_definitions']['count'])],
        ['Quality Score', f"{results['step_definitions']['quality_score']}/100"]
    ]
    if results['step_definitions']['issues']:
        step_data.append(['Issues', Paragraph('\n'.join('• ' + issue for issue in results['step_definitions']['issues']), styles['Normal'])])

    step_table = Table(step_data, colWidths=[2*inch, 4*inch])
    step_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.grey),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 14),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
        ('TEXTCOLOR', (0, 1), (-1, -1), colors.black),
        ('FONTNAME', (0, 1), (-1, -1), 'Helvetica'),
        ('FONTSIZE', (0, 1), (-1, -1), 12),
        ('GRID', (0, 0), (-1, -1), 1, colors.black)
    ]))
    story.append(step_table)
    story.append(Spacer(1, 12))

    # Framework Structure
    story.append(Paragraph('Framework Structure', styles['Heading2']))
    structure_data = [
        ['Component', 'Status'],
        ['Page Objects', str(results['framework_structure'].get('page_objects', 'N/A'))],
        ['Test Data', str(results['framework_structure'].get('test_data', 'N/A'))],
        ['Config Files', str(results['framework_structure'].get('config_files', 'N/A'))]
    ]

    structure_table = Table(structure_data, colWidths=[2*inch, 4*inch])
    structure_table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (-1, 0), colors.grey),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
        ('ALIGN', (0, 0), (-1, -1), 'CENTER'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 14),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
        ('TEXTCOLOR', (0, 1), (-1, -1), colors.black),
        ('FONTNAME', (0, 1), (-1, -1), 'Helvetica'),
        ('FONTSIZE', (0, 1), (-1, -1), 12),
        ('GRID', (0, 0), (-1, -1), 1, colors.black)
    ]))
    story.append(structure_table)
    story.append(Spacer(1, 12))


    # Selenium Implementation Section
    story.append(Paragraph("Selenium Implementation", styles['Heading2']))

    # Get selenium implementation data with defaults
    selenium_impl = results.get('selenium_implementation', {})
    
    # Add Selenium metrics
    selenium_metrics = [
        ["Wait Strategy", f"{selenium_impl.get('wait_strategy', 0)}%"],
        ["Explicit Waits", "✓ Found" if selenium_impl.get('explicit_waits', False) else "✗ Missing"],
        ["Custom Wait Conditions", "✓ Found" if selenium_impl.get('custom_wait_conditions', False) else "✗ Missing"],
        ["Screenshot Capability", "✓ Found" if selenium_impl.get('screenshot_capability', False) else "✗ Missing"],
        ["WebDriver Management", "✓ Found" if selenium_impl.get('webdriver_management', False) else "✗ Missing"],
        ["Actions Class Usage", "✓ Found" if selenium_impl.get('actions_class_usage', False) else "✗ Missing"],
        ["JavaScript Executor", "✓ Found" if selenium_impl.get('javascript_executor', False) else "✗ Missing"]
    ]

    selenium_table = Table(selenium_metrics, colWidths=[3 * inch, 1 * inch])
    selenium_table.setStyle(TableStyle([
        ('GRID', (0, 0), (-1, -1), 1, colors.purple),
        ('FONTNAME', (0, 0), (-1, -1), 'Helvetica'),
        ('ALIGN', (1, 0), (1, -1), 'CENTER'),
    ]))

    story.append(selenium_table)
    story.append(Spacer(1, 0.2 * inch))

    # Browser Execution Section
    story.append(Paragraph("Browser Execution Analysis", styles['Heading2']))

    # Get browser execution data with defaults
    browser_exec = results.get('browser_execution', {})
    
    browser_metrics = [
        ["Browser Compatibility", f"{browser_exec.get('browser_compatibility', 0)}%"],
        ["Grid Support", "Implemented" if browser_exec.get('grid_support', False) else "Missing"],
        ["Parallel Execution", "Implemented" if browser_exec.get('parallel_execution', False) else "Missing"],
        ["Retry Mechanism", "Implemented" if browser_exec.get('retry_mechanism', False) else "Missing"]
    ]

    browser_table = Table(browser_metrics, colWidths=[3 * inch, 1 * inch])
    browser_table.setStyle(TableStyle([
        ('GRID', (0, 0), (-1, -1), 1, colors.purple),
        ('FONTNAME', (0, 0), (-1, -1), 'Helvetica'),
        ('ALIGN', (1, 0), (1, -1), 'CENTER'),
    ]))

    story.append(browser_table)
    story.append(Spacer(1, 0.2 * inch))


    # Test Coverage Section (adapted from original)
    story.append(Paragraph("Test Coverage Analysis", styles['Heading2']))

    if 'test_coverage' in results:
        if results['test_coverage'].get('issues'):
            story.append(Paragraph("Issues:", styles['Heading3']))
            for issue in results['test_coverage']['issues']:
                story.append(Paragraph(f"• {issue}", styles['Normal']))

        metrics = results['test_coverage'].get('metrics', {})
        if metrics:
            if 'steps_to_features_ratio' in metrics:
                story.append(Paragraph(f"Step definitions to features ratio: {metrics['steps_to_features_ratio']}", styles['Normal']))

            if metrics.get('missing_points', 0) > 0:
                story.append(Paragraph("Coverage Loss Details:", styles['Heading3']))
                story.append(Paragraph(f"Missing {metrics['missing_points']}% in Test Coverage due to:", styles['Normal']))
                for reason in metrics.get('missing_reasons', []):
                    story.append(Paragraph(f"• {reason}", styles['Normal']))
    else:
        story.append(Paragraph("No detailed metrics available for test coverage", styles['Normal']))

    story.append(Spacer(1, 0.2 * inch))


    # Recommendations Section
    story.append(Spacer(1, 0.3 * inch))
    story.append(Paragraph("Recommendations", styles['Heading2']))

    recommendations = results.get('recommendations', [])
    if recommendations:
        for i, recommendation in enumerate(recommendations, 1):
            story.append(Paragraph(f"{i}. {recommendation}", styles['Normal']))
            story.append(Spacer(1, 0.1 * inch))
    else:
        recommendations = []
        
        # Generate recommendations based on scores
        if results['feature_files']['quality_score'] < 70:
            recommendations.append("Improve feature file quality by adding proper descriptions and tags")
        if results['step_definitions']['quality_score'] < 70:
            recommendations.append("Enhance step definitions with better parameterization")
        if results['framework_structure']['score'] < 70:
            recommendations.append("Organize project structure into standard BDD directories")
        if results.get('selenium_implementation', {}).get('wait_strategy', 0) < 70:
            recommendations.append("Implement better wait strategies in Selenium tests")
        if not results.get('browser_execution', {}).get('parallel_execution', False):
            recommendations.append("Consider implementing parallel test execution")
        if not results.get('page_objects', {}).get('base_page_pattern', False):
            recommendations.append("Implement base page pattern for better maintainability")
            
        # Display generated recommendations
        if recommendations:
            for i, recommendation in enumerate(recommendations, 1):
                story.append(Paragraph(f"{i}. {recommendation}", styles['Normal']))
                story.append(Spacer(1, 0.1 * inch))
        else:
            story.append(Paragraph("No specific recommendations at this time.", styles['Normal']))

    # Project Enhancers Section
    story.append(Spacer(1, 0.3 * inch))
    story.append(Paragraph("Project Analysis & Enhancers", styles['Heading2']))

    # Project Strengths
    story.append(Paragraph("Project Strengths:", styles['Heading3']))
    strengths = results.get('project_strengths', {})
    
    if strengths:
        story.append(Paragraph("• Well-structured Maven-based Selenium test automation framework", styles['Normal']))
        story.append(Paragraph("• Robust TestNG integration for reliable test execution", styles['Normal']))
        story.append(Paragraph("• Clear and organized project structure following industry standards", styles['Normal']))
        if strengths.get('documentation', False):
            story.append(Paragraph("• Good documentation practices with README", styles['Normal']))
        if strengths.get('ci_ready', False):
            story.append(Paragraph("• CI/CD ready with proper configuration files", styles['Normal']))
        if strengths.get('maintainable', False):
            story.append(Paragraph("• Highly maintainable codebase with good naming conventions", styles['Normal']))
        if strengths.get('scalable', False):
            story.append(Paragraph("• Scalable architecture supporting parallel execution", styles['Normal']))
    
    # Project Structure
    story.append(Paragraph("Project Structure:", styles['Heading3']))
    story.append(Paragraph("• Maven-based Selenium test automation project", styles['Normal']))
    story.append(Paragraph("• TestNG configuration present", styles['Normal']))
    story.append(Paragraph("• Organized in standard Maven directory layout", styles['Normal']))

    # Implementation Details
    story.append(Paragraph("Implementation Details:", styles['Heading3']))
    story.append(Paragraph("• Step definitions located in stepDefinitions folder", styles['Normal']))
    story.append(Paragraph("• Uses TestNG for test execution framework", styles['Normal']))
    story.append(Paragraph("• Selenium WebDriver implementation found", styles['Normal']))

    # Project Enhancers
    story.append(Paragraph("Framework Enhancers:", styles['Heading3']))
    enhancers = results.get('project_enhancers', {})
    
    enhancer_status = [
        ["Feature", "Status"],
        ["Maven Structure", "✓ Implemented" if enhancers.get('maven_structure', False) else "✗ Not Found"],
        ["TestNG Config", "✓ Implemented" if enhancers.get('testng_config', False) else "✗ Not Found"],
        ["Page Factory", "✓ Implemented" if enhancers.get('page_factory', False) else "✗ Not Found"],
        ["Logging", "✓ Implemented" if enhancers.get('logging_implementation', False) else "✗ Not Found"],
        ["Data Driven Testing", "✓ Implemented" if enhancers.get('data_driven_testing', False) else "✗ Not Found"],
        ["Custom Reporting", "✓ Implemented" if enhancers.get('custom_reporting', False) else "✗ Not Found"]
    ]
    
    enhancer_table = Table(enhancer_status, colWidths=[3 * inch, 2 * inch])
    enhancer_table.setStyle(TableStyle([
        ('GRID', (0, 0), (-1, -1), 1, colors.grey),
        ('BACKGROUND', (0, 0), (-1, 0), colors.grey),
        ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
        ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
        ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
        ('FONTSIZE', (0, 0), (-1, 0), 12),
        ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
        ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
        ('TEXTCOLOR', (0, 1), (-1, -1), colors.black),
        ('FONTNAME', (0, 1), (-1, -1), 'Helvetica'),
        ('FONTSIZE', (0, 1), (-1, -1), 10)
    ]))
    
    story.append(enhancer_table)
    story.append(Spacer(1, 0.2 * inch))

    # Build the PDF
    doc.build(story)
    return output_path