import os
import io
import tempfile
import logging
from reportlab.lib import colors
from reportlab.lib.pagesizes import letter
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle, Image
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch

# Configure logging
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)

def generate_scoring_guide(results, project_path):
    """
    Generate a PDF scoring guide based on the analysis results.
    
    Args:
        results (dict): Analysis results from the BDD framework analyzer
        project_path (str): Path to the analyzed project
    
    Returns:
        str: Path to the generated PDF file
    """
    logger.debug("Generating scoring guide PDF")
    
    # Check if results is None
    if results is None:
        # Create a sample empty results structure
        results = {
            'overall_score': 0,
            'feature_files': {
                'count': 0,
                'quality_score': 0,
                'issues': ['No analysis results available'],
                'metrics': {}
            },
            'step_definitions': {
                'count': 0,
                'quality_score': 0,
                'issues': ['No analysis results available'],
                'metrics': {}
            },
            'test_coverage': {
                'score': 0,
                'issues': ['No analysis results available']
            },
            'framework_structure': {
                'score': 0,
                'issues': ['No analysis results available'],
                'metrics': {'found_directories': 0, 'expected_directories': []}
            },
            'recommendations': ['Run a project analysis to get personalized recommendations']
        }
    
    # Create a temporary file for the PDF
    temp_file = tempfile.NamedTemporaryFile(suffix='.pdf', delete=False)
    pdf_path = temp_file.name
    temp_file.close()
    
    # Create PDF document
    doc = SimpleDocTemplate(
        pdf_path,
        pagesize=letter,
        rightMargin=72,
        leftMargin=72,
        topMargin=72,
        bottomMargin=72
    )
    
    # Get styles
    styles = getSampleStyleSheet()
    
    # Create custom styles
    title_style = ParagraphStyle(
        'Title',
        parent=styles['Title'],
        fontSize=24,
        textColor=colors.purple,
        spaceAfter=12
    )
    
    heading_style = ParagraphStyle(
        'Heading',
        parent=styles['Heading2'],
        fontSize=14,
        textColor=colors.purple,
        spaceAfter=6
    )
    
    subheading_style = ParagraphStyle(
        'Subheading',
        parent=styles['Heading3'],
        fontSize=12,
        textColor=colors.purple,
        spaceAfter=6
    )
    
    normal_style = styles['Normal']
    
    # Build document content
    content = []
    
    # Title
    content.append(Paragraph("BDD Framework Scoring Guide", title_style))
    content.append(Spacer(1, 0.25 * inch))
    
    # Project info
    content.append(Paragraph(f"Project: {project_path}", normal_style))
    content.append(Paragraph(f"Overall Score: {results['overall_score']}/100", heading_style))
    content.append(Spacer(1, 0.2 * inch))
    
    # Summary of scores
    data = [
        ["Category", "Score"],
        ["Feature Files", f"{results['feature_files']['quality_score']}/100"],
        ["Step Definitions", f"{results['step_definitions']['quality_score']}/100"],
        ["Test Coverage", f"{results['test_coverage']['score']}/100"],
        ["Framework Structure", f"{results['framework_structure']['score']}/100"]
    ]
    
    # Create table with scores
    table = Table(data, colWidths=[3 * inch, 1 * inch])
    table.setStyle(TableStyle([
        ('BACKGROUND', (0, 0), (1, 0), colors.lavender),
        ('TEXTCOLOR', (0, 0), (1, 0), colors.purple),
        ('ALIGN', (0, 0), (1, 0), 'CENTER'),
        ('FONTNAME', (0, 0), (1, 0), 'Helvetica-Bold'),
        ('BOTTOMPADDING', (0, 0), (1, 0), 12),
        ('GRID', (0, 0), (-1, -1), 1, colors.purple),
    ]))
    
    content.append(table)
    content.append(Spacer(1, 0.3 * inch))
    
    # Feature Files Section
    content.append(Paragraph("Feature Files Analysis", heading_style))
    content.append(Paragraph(f"Found {results['feature_files']['count']} feature files", normal_style))
    
    if results['feature_files']['metrics'].get('scenarios_count'):
        metrics = results['feature_files']['metrics']
        content.append(Paragraph(f"• Total scenarios: {metrics['scenarios_count']}", normal_style))
        content.append(Paragraph(f"• Scenarios with examples: {metrics['scenarios_with_examples']}", normal_style))
        content.append(Paragraph(f"• Scenarios with tags: {metrics['scenarios_with_tags']}", normal_style))
    
    if results['feature_files']['issues']:
        content.append(Paragraph("Issues:", subheading_style))
        for issue in results['feature_files']['issues'][:5]:  # Limit to top 5 issues
            content.append(Paragraph(f"• {issue}", normal_style))
    
    content.append(Spacer(1, 0.2 * inch))
    
    # Step Definitions Section
    content.append(Paragraph("Step Definitions Analysis", heading_style))
    content.append(Paragraph(f"Found {results['step_definitions']['count']} step definition files", normal_style))
    
    if results['step_definitions']['metrics'].get('total_steps'):
        metrics = results['step_definitions']['metrics']
        content.append(Paragraph(f"• Total step definitions: {metrics['total_steps']}", normal_style))
        content.append(Paragraph(f"• Parameterized steps: {metrics['parameterized_steps']}", normal_style))
        param_ratio = metrics['parameterized_steps'] / max(metrics['total_steps'], 1) * 100
        content.append(Paragraph(f"• Parameterization ratio: {param_ratio:.1f}%", normal_style))
    
    if results['step_definitions']['issues']:
        content.append(Paragraph("Issues:", subheading_style))
        for issue in results['step_definitions']['issues'][:5]:  # Limit to top 5 issues
            content.append(Paragraph(f"• {issue}", normal_style))
    
    content.append(Spacer(1, 0.2 * inch))
    
    # Test Coverage Section
    content.append(Paragraph("Test Coverage Analysis", heading_style))
    
    if results['test_coverage']['issues']:
        content.append(Paragraph("Issues:", subheading_style))
        for issue in results['test_coverage']['issues']:
            content.append(Paragraph(f"• {issue}", normal_style))
    
    content.append(Spacer(1, 0.2 * inch))
    
    # Framework Structure Section
    content.append(Paragraph("Framework Structure Analysis", heading_style))
    
    if results['framework_structure']['metrics'].get('expected_directories'):
        metrics = results['framework_structure']['metrics']
        found = metrics['found_directories']
        expected = len(metrics['expected_directories'])
        content.append(Paragraph(f"• Found {found} out of {expected} recommended directories", normal_style))
        content.append(Paragraph(f"• Has configuration files: {'Yes' if metrics.get('has_config') else 'No'}", normal_style))
    
    if results['framework_structure']['issues']:
        content.append(Paragraph("Issues:", subheading_style))
        for issue in results['framework_structure']['issues']:
            content.append(Paragraph(f"• {issue}", normal_style))
    
    content.append(Spacer(1, 0.3 * inch))
    
    # Recommendations Section
    content.append(Paragraph("Recommendations", heading_style))
    
    if results['recommendations']:
        for i, recommendation in enumerate(results['recommendations'], 1):
            content.append(Paragraph(f"{i}. {recommendation}", normal_style))
            content.append(Spacer(1, 0.1 * inch))
    else:
        content.append(Paragraph("No specific recommendations at this time.", normal_style))
    
    # Build the PDF
    try:
        doc.build(content)
        logger.debug(f"PDF generated successfully at {pdf_path}")
        return pdf_path
    
    except Exception as e:
        logger.error(f"Error generating PDF: {str(e)}")
        raise
