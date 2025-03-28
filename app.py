import os
import logging
from flask import Flask, render_template, request, jsonify, send_file, session, flash, redirect, url_for
from analyzer import analyze_bdd_framework
from report_generator import generate_scoring_guide

# Configure logging
logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger(__name__)

# Create Flask app
app = Flask(__name__)
app.secret_key = os.environ.get("SESSION_SECRET", "dev_secret_key")

@app.route('/')
def index():
    """Render the main page of the BDD Framework Analyzer."""
    return render_template('index.html')

@app.route('/analyze', methods=['POST'])
def analyze():
    """
    Process the project path and analyze the BDD framework.
    Returns analysis results or error message.
    """
    project_path = request.form.get('project_path')
    
    if not project_path:
        flash('Please enter a project path', 'error')
        return redirect(url_for('index'))
    
    try:
        logger.debug(f"Analyzing project at path: {project_path}")
        results = analyze_bdd_framework(project_path)
        
        # Extract the final part of the path to use as project name
        project_name = os.path.basename(project_path)
        
        # Force the project name to be just the last part of the path, no matter what
        if '\\' in project_path:
            project_name = project_path.split('\\')[-1]
        elif '/' in project_path:
            project_name = project_path.split('/')[-1]
        
        # Store results in session for later use
        session['analysis_results'] = results
        session['project_path'] = project_path
        session['project_name'] = project_name
        
        return render_template('results.html', results=results, project_path=project_path, project_name=project_name)
    
    except Exception as e:
        logger.error(f"Error analyzing project: {str(e)}")
        flash(f'Error analyzing project: {str(e)}', 'error')
        return redirect(url_for('index'))

@app.route('/health-overview')
def health_overview():
    """
    Show the Framework Health Overview dashboard.
    """
    # Get results from session if available
    results = session.get('analysis_results')
    project_path = session.get('project_path', 'Unknown Project')
    project_name = session.get('project_name')
    
    # If project_name is not in session, extract it from path
    if not project_name:
        project_name = os.path.basename(project_path)
        # Force the project name to be just the last part of the path, no matter what
        if '\\' in project_path:
            project_name = project_path.split('\\')[-1]
        elif '/' in project_path:
            project_name = project_path.split('/')[-1]
    
    if not results:
        flash('Please analyze a project first', 'error')
        return redirect(url_for('index'))
    
    return render_template('health_overview.html', results=results, project_path=project_path, project_name=project_name)

@app.route('/download-guide')
def download_guide():
    """Generate and download the BDD framework scoring guide."""
    try:
        # Get results from session if available
        results = session.get('analysis_results')
        project_path = session.get('project_path', 'Unknown Project')
        project_name = session.get('project_name')
        
        # If project_name is not in session, extract it from path
        if not project_name:
            project_name = os.path.basename(project_path)
            # Force the project name to be just the last part of the path, no matter what
            if '\\' in project_path:
                project_name = project_path.split('\\')[-1]
            elif '/' in project_path:
                project_name = project_path.split('/')[-1]
        
        # Generate PDF guide
        pdf_path = generate_scoring_guide(results, project_path)
        
        return send_file(
            pdf_path,
            as_attachment=True,
            download_name=f'bdd_scoring_guide_{project_name}.pdf'
        )
    
    except Exception as e:
        logger.error(f"Error generating scoring guide: {str(e)}")
        flash(f'Error generating scoring guide: {str(e)}', 'error')
        return redirect(url_for('index'))

@app.route('/api/analyze', methods=['POST'])
def api_analyze():
    """API endpoint for analyzing BDD framework."""
    data = request.json
    project_path = data.get('project_path')
    
    if not project_path:
        return jsonify({'error': 'Project path is required'}), 400
    
    try:
        results = analyze_bdd_framework(project_path)
        return jsonify({'results': results})
    
    except Exception as e:
        logger.error(f"API error: {str(e)}")
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
