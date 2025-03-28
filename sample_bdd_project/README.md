# Sample BDD Framework

This is a sample Behavior-Driven Development (BDD) framework using Cucumber and Selenium WebDriver. It demonstrates best practices for implementing a testing automation framework with a focus on maintainability, scalability, and code quality.

## Framework Structure

```
sample_bdd_project/
├── config/
│   └── config.properties        # Configuration for browser, wait times, etc.
├── features/
│   ├── login.feature            # Login functionality scenarios
│   └── search.feature           # Search functionality scenarios
├── src/
│   └── test/
│       └── java/
│           ├── runner/
│           │   └── TestRunner.java          # Test runner for Cucumber tests
│           ├── selenium/
│           │   └── pages/
│           │       ├── BasePage.java        # Base page with common methods
│           │       ├── DashboardPage.java   # Dashboard page object
│           │       ├── HomePage.java        # Home page object
│           │       ├── LoginPage.java       # Login page object
│           │       └── SearchResultsPage.java  # Search results page object
│           ├── stepdefs/
│           │   ├── Hooks.java               # Setup/teardown hooks
│           │   ├── LoginSteps.java          # Login step definitions
│           │   └── SearchSteps.java         # Search step definitions
│           └── utils/
│               └── DriverFactory.java       # WebDriver initialization
└── pom.xml                      # Maven dependencies
```

## Key Features

- **Page Object Model**: Separation of page objects from test steps
- **Explicit Wait Strategy**: Robust element handling with explicit waits
- **Proper Encapsulation**: Page objects encapsulate page elements and actions
- **Base Page Pattern**: Common methods in a base page
- **Hooks for Setup/Teardown**: Proper resource management
- **Screenshot on Failure**: Automated screenshot capture for failed tests
- **Configuration Management**: External configuration file
- **Parallel Execution Support**: Can run tests in parallel
- **Data-Driven Testing**: Example-based scenarios for data-driven tests
- **Cross-Browser Support**: Firefox, Chrome, and Edge browser support
- **Headless Mode**: Support for headless testing
- **Tag-Based Execution**: Test filtering using tags (@smoke, @regression)
- **Pretty HTML Reports**: Detailed HTML test reports

## Running the Tests

```bash
mvn clean test
```

## Test Reports

Reports are generated in: `target/cucumber-reports`