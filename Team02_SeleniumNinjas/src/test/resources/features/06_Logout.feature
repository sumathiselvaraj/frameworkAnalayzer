
Feature: Validation on Logout button
   
  Scenario: Verify logout function
    Given Admin is in dashboard page after login.
    When Admin clicks on the logout in the menu bar
    Then Admin should be redirected to login page
    
     Scenario: Verify back button function after clicked logout option.
    Given Admin is in login page after logout
    When Admin clicks  browser back button
    Then Admin should receive error message