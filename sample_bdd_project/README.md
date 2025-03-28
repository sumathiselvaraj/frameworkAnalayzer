# LMS BDD Testing Framework

This project contains a comprehensive BDD testing framework for a Learning Management System (LMS) application. The framework follows best practices for test automation and demonstrates proper implementation of the Page Object Model with Selenium WebDriver.

## Project Structure

```
sample_bdd_project/
├── features/                        # Gherkin feature files
│   ├── login.feature                # Login functionality tests
│   ├── programs.feature             # Program management tests
│   ├── batch.feature                # Batch management tests
│   └── ...                          # Other feature files
├── src/
│   └── test/
│       ├── java/
│       │   ├── runner/              # Test runners
│       │   │   └── TestRunner.java  # JUnit test runner for Cucumber
│       │   ├── stepdefs/            # Step definitions
│       │   │   ├── Hooks.java       # Before/After hooks
│       │   │   ├── LoginSteps.java  # Login steps
│       │   │   └── ...              # Other step definitions
│       │   └── selenium/
│       │       ├── utils/           # Utilities and helpers
│       │       └── pages/           # Page objects
│       │           └── lms/         # LMS application page objects
│       │               ├── BasePage.java           # Base page with common methods
│       │               ├── LoginPage.java          # Login page
│       │               ├── DashboardPage.java      # Dashboard page
│       │               ├── ProgramsPage.java       # Programs page
│       │               ├── BatchPage.java          # Batch page
│       │               ├── ClassPage.java          # Class page
│       │               ├── UserPage.java           # User page
│       │               ├── AssignmentPage.java     # Assignment page
│       │               ├── AttendancePage.java     # Attendance page
│       │               └── ForgotPasswordPage.java # Forgot password page
│       └── resources/
│           ├── data/                # Test data
│           ├── config/              # Configuration files
│           └── drivers/             # WebDriver binaries
└── pom.xml                          # Maven project configuration
```

## Framework Features

This BDD framework includes the following features:

1. **Page Object Model**: Comprehensive implementation of the Page Object Model pattern for easy test maintenance.

2. **Explicit Waits**: Smart explicit wait methods for robust test execution.

3. **BDD Implementation**: Well-structured Gherkin feature files with proper Given-When-Then scenarios.

4. **Proper Test Organization**: Features organized by functionality.

5. **Selenium Best Practices**: 
   - Fluent interface design with method chaining
   - Proper element locators
   - Reusable methods in base page
   - Configurable timeouts

6. **Well-documented Code**: Comprehensive JavaDoc comments on all classes and methods.

7. **Error Handling**: Robust exception handling for better test stability.

## Page Objects

The framework includes page objects for all key pages in the LMS application:

1. **BasePage**: Contains common methods used across all pages, like waits, clicks, etc.

2. **LoginPage**: Handles login functionality, including error cases and password reset.

3. **DashboardPage**: Represents the main dashboard after login with navigation to other modules.

4. **ProgramsPage**: Manages program creation, editing, and deletion.

5. **BatchPage**: Manages batch creation, editing, and deletion.

6. **ClassPage**: Manages class scheduling and details.

7. **UserPage**: Manages user (student, staff, admin) profiles.

8. **AssignmentPage**: Manages assignments and grading.

9. **AttendancePage**: Manages attendance tracking and reporting.

## Running the Tests

To run the tests, execute the TestRunner class or use Maven:

```
mvn clean test
```

## Test Configuration

Configuration parameters like browser type, application URL, timeouts, etc. can be modified in the `config.properties` file.

## Test Reports

After test execution, detailed HTML reports are generated in the `target/cucumber-reports` directory.