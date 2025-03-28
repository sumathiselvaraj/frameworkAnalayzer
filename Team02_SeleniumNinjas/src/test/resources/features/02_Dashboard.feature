Feature: LMS portal-Home Page Verification.

 Background: Verify if admin is sucssessfuly logged into LMS portal.
    Given Admin is in landing page
    When  Admin logged into the LMS portal
    Then  Admin should be navigating to home page
    
 Scenario: Verify after login  admin lands on manage program as dashboard page
    Then Admin should see LMS-Learning Management Systemm as header
 
 Scenario: Verify LMS title in home page
    Then Admin should see "LMS"  as title
    
    ######################################################################
    
  Scenario: Verify LMS title alignment
    Then LMS title should be on the top left corner of page

  Scenario: Validate navigation bar text
    Then Admin should see correct spelling in navigation bar text

  
  Scenario: Validate LMS title has correct spelling and space
    Then Admin should see correct spelling and space in LMS title

  
  Scenario: Validate alignment for navigation bar
    Then Admin should see the navigation bar text on the top right side

 
  Scenario: Validate navigation bar order 1st home
    Then Admin should see Home in the 1st place

  Scenario: Validate navigation bar order 2nd program
    Then Admin should see program in the 2nd place

  Scenario: Validate navigation bar order 3rd batch
    Then Admin should see batch in the 3rd place


  Scenario: Validate navigation bar order 4th class
    Then Admin should see class in the 4th place

  Scenario: validate navigation bar order 5 th logout
    Then Admin should see logout in the 5th place
 