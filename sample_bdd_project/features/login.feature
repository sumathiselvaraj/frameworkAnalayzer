Feature: LMS Login Functionality
  As a user of the Learning Management System
  I want to be able to login with my credentials
  So that I can access my courses and learning materials

  Background:
    Given I am on the LMS login page

  Scenario: Successful login with valid credentials for Admin
    When I enter username "admin@lms.com"
    And I enter password "admin123"
    And I select role "Admin"
    And I click the login button
    Then I should be redirected to the dashboard page
    And I should see welcome message containing "Admin"
    And I should see navigation menu with "Programs", "Batch", "Class", "User", "Assignment", "Attendance" options

  Scenario: Failed login with invalid credentials
    When I enter username "invalid@lms.com"
    And I enter password "wrongpassword"
    And I select role "Student"
    And I click the login button
    Then I should see an error message "Invalid username or password"
    And I should remain on the login page

  Scenario: Failed login with empty credentials
    When I click the login button without entering any credentials
    Then I should see validation messages for required fields
    And I should remain on the login page

  Scenario: Password reset functionality
    When I click on forgot password link
    Then I should be redirected to password reset page
    When I enter email "user@lms.com" for password reset
    And I click reset password button
    Then I should see confirmation message "Password reset instructions sent to your email"
    When I click back to login link
    Then I should be redirected to the login page