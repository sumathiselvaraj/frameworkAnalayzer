/**
 * BDD Framework Analyzer - Main JavaScript functionality
 */

document.addEventListener('DOMContentLoaded', function() {
    // Initialize form validation and event listeners
    initFormValidation();
    
    // Initialize tooltips if any
    initTooltips();
    
    // Add flash message dismissal functionality
    initFlashMessages();
    
    // Initialize any charts or data visualization if on results page
    if (document.querySelector('.results-container')) {
        initResultsVisualization();
    }
});

/**
 * Initialize form validation for the project path input
 */
function initFormValidation() {
    const analyzerForm = document.getElementById('analyzer-form');
    const pathInput = document.getElementById('project-path');
    
    if (!analyzerForm || !pathInput) return;
    
    analyzerForm.addEventListener('submit', function(event) {
        // Simple validation to ensure path is not empty
        if (!pathInput.value.trim()) {
            event.preventDefault();
            showError(pathInput, 'Please enter a project path');
        } else {
            // Show loading state
            const analyzeButton = document.querySelector('button[type="submit"]');
            if (analyzeButton) {
                analyzeButton.disabled = true;
                analyzeButton.innerHTML = '<svg class="spin" width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M12 2C6.48 2 2 6.48 2 12C2 17.52 6.48 22 12 22C17.52 22 22 17.52 22 12" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/></svg> Analyzing...';
            }
        }
    });
    
    // Clear error when input changes
    pathInput.addEventListener('input', function() {
        clearError(pathInput);
    });
}

/**
 * Show error for an input element
 */
function showError(inputElement, message) {
    clearError(inputElement);
    
    inputElement.classList.add('error');
    
    const errorElement = document.createElement('div');
    errorElement.className = 'input-error';
    errorElement.textContent = message;
    
    inputElement.parentNode.appendChild(errorElement);
}

/**
 * Clear error from an input element
 */
function clearError(inputElement) {
    inputElement.classList.remove('error');
    
    const errorElement = inputElement.parentNode.querySelector('.input-error');
    if (errorElement) {
        errorElement.remove();
    }
}

/**
 * Initialize tooltips for help/info icons
 */
function initTooltips() {
    const tooltipElements = document.querySelectorAll('[data-tooltip]');
    
    tooltipElements.forEach(element => {
        // Simple tooltip implementation
        element.addEventListener('mouseenter', function() {
            const tooltipText = this.getAttribute('data-tooltip');
            
            const tooltip = document.createElement('div');
            tooltip.className = 'tooltip';
            tooltip.textContent = tooltipText;
            
            document.body.appendChild(tooltip);
            
            const rect = this.getBoundingClientRect();
            tooltip.style.left = rect.left + (rect.width / 2) - (tooltip.offsetWidth / 2) + 'px';
            tooltip.style.top = rect.bottom + 10 + 'px';
        });
        
        element.addEventListener('mouseleave', function() {
            const tooltip = document.querySelector('.tooltip');
            if (tooltip) tooltip.remove();
        });
    });
}

/**
 * Initialize flash message dismissal functionality
 */
function initFlashMessages() {
    const flashMessages = document.querySelectorAll('.flash-message');
    
    flashMessages.forEach(message => {
        // Add close button
        const closeButton = document.createElement('button');
        closeButton.className = 'flash-close';
        closeButton.innerHTML = '&times;';
        closeButton.setAttribute('aria-label', 'Close');
        
        message.appendChild(closeButton);
        
        // Add event listener to close button
        closeButton.addEventListener('click', function() {
            message.remove();
        });
        
        // Auto-dismiss after 5 seconds
        setTimeout(() => {
            message.style.opacity = '0';
            setTimeout(() => {
                if (message.parentNode) {
                    message.remove();
                }
            }, 300);
        }, 5000);
    });
}

/**
 * Initialize visualization for results page
 */
function initResultsVisualization() {
    // Get score elements
    const scoreElements = document.querySelectorAll('.score-value');
    
    // Animate scores counting up
    scoreElements.forEach(element => {
        const finalScore = parseInt(element.textContent, 10);
        element.textContent = '0';
        
        let currentScore = 0;
        const interval = setInterval(() => {
            currentScore += 1;
            element.textContent = currentScore;
            
            if (currentScore >= finalScore) {
                element.textContent = finalScore;
                clearInterval(interval);
                
                // Add color class based on score
                if (finalScore >= 80) {
                    element.classList.add('score-high');
                } else if (finalScore >= 50) {
                    element.classList.add('score-medium');
                } else {
                    element.classList.add('score-low');
                }
            }
        }, 20);
    });
    
    // Initialize copy to clipboard functionality for code paths
    const copyButtons = document.querySelectorAll('.copy-button');
    copyButtons.forEach(button => {
        button.addEventListener('click', function() {
            const textToCopy = this.getAttribute('data-copy');
            navigator.clipboard.writeText(textToCopy).then(() => {
                const originalText = this.textContent;
                this.textContent = 'Copied!';
                setTimeout(() => {
                    this.textContent = originalText;
                }, 2000);
            });
        });
    });
}
