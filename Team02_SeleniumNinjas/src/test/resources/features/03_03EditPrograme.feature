
Feature: Edit Program


Background:
  Given Admin is in LoginPage
  When Admin enters valid user and password with select role as Admin.
  And Admin lands on dashboard Page and clicks Program from navigation bar
  
  
  Scenario Outline: Verify Edit Program functionalities
    Given Admin is on the Program page and search program "<programName>"
    When Admin clicks on Edit option for a particular program "<programName>"
    Then Admin "<Action>"
    
  Examples:
    |   programName   |           Action                |
    |KarateCoding| lands on the Program details form |
    |KarateCoding| should see the window title as Program Details |
    |KarateCoding| should see a red asterisk mark beside the mandatory field Name |
    
   @update
    Scenario Outline: Edit Program details
    Given Admin is on the Program page and search program "<programName>"
    When Admin clicks on Edit option for a particular program "<programName>"
    When Admin updates fields from "<Sheetname>" <Rownumber> and clicks on the save button 
    Then Admin checks "<expectedOutcome>"
Examples:
   |   programName    |  Sheetname   | Rownumber |         expectedOutcome                |
   |FinalNinja |    Program    |     0     |   The updated program details is seen      |
     
    Scenario Outline: Verify edited Program details
    When Admin searches with the newly updated "<ProgramName>"
    Then Admin verifies that the details are correctly updated from "<Sheetname>" <Rownumber>
    Examples:
    |ProgramName  |  Sheetname |Rownumber|
    |KarateSDET|  Program   |   0     |
    
    
  Scenario: Verify Admin is able to click Cancel
    Given Admin is on the Program page 
    When Admin clicks on the cancel button on Edit form
    Then Admin can see the Program details form disappear

 
  Scenario: Verify closing the window with "X"
    Given Admin is on the Program page 
    When Admin clicks on X button on Edit form
    Then Admin can see the Program details form disappear
  
  
  

 
