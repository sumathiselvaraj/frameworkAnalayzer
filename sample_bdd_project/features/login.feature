Feature: User Authentication
  As a registered user
  I want to log in to the application
  So that I can access my account

  Background:
    Given I am on the login page

  @smoke @critical
  Scenario: Successful login with valid credentials
    When I enter valid username "testuser"
    And I enter valid password "Password123"
    And I click the login button
    Then I should be redirected to the dashboard
    And I should see a welcome message with my username

  @regression
  Scenario: Failed login with invalid password
    When I enter valid username "testuser"
    And I enter invalid password "wrongpassword"
    And I click the login button
    Then I should see an error message "Invalid credentials"
    And I should remain on the login page

  @regression
  Scenario: Failed login with non-existent user
    When I enter username "nonexistentuser"
    And I enter password "anypassword"
    And I click the login button
    Then I should see an error message "User does not exist"
    And I should remain on the login page

  @regression
  Scenario Outline: Login validation for various inputs
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should see message "<message>"
    And I should be on page "<page>"

    Examples:
      | username | password     | message               | page       |
      | testuser | Password123  | Welcome, testuser!    | dashboard  |
      | testuser | wrong        | Invalid credentials   | login      |
      | admin    | admin123     | Welcome, admin!       | dashboard  |
      |          | Password123  | Username is required  | login      |
      | testuser |              | Password is required  | login      |