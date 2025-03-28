@delete

Feature: Delete Program

Background:
  Given Admin is in LoginPage
  When Admin enters valid user and password with select role as Admin.
  And Admin lands on dashboard Page and clicks Program from navigation bar

Scenario: Delete Feature
    Given  Admin is on the Program page
    When Admin clicks on delete button for a program
    Then Admin will get confirm deletion popup

  Scenario: Verify Admin is able to cancel the deletion of a program
    When Admin clicks on delete button for a program
    When Admin clicks on No button
    Then Admin can see Confirm Deletion form disappear

  Scenario: Verify Admin is able to close the confirmation window without deleting
    Given Admin clicks on delete button for a program
    When Admin clicks on X button
    Then Admin can see Confirm Deletion form disappear
    
    Scenario: Confirm Delete
    Given Admin clicks on delete button for a program
    When Admin clicks on "Yes" button on the Confirm deletion form
    Then Admin can see success message
    
    
    #Scenario Outline: Confirm Delete
     #Given Admin searches for "<ProgramName>"
     #When Admin clicks on "<action>" button on the Confirm deletion form
    #Then Admin can see success message
    #Examples:
    #|  ProgramName  |     action   |  
    #|   DanGG |       Yes    | 