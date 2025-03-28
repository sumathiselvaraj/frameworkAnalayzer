@Program

Feature: Program module
  I want to use this template for my feature file

  Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
    When Admin enters valid user and password with select role as Admin.

  @ProgramTestCase-1
  Scenario: Verify that Admin is able to navigate to Program page
    Given Admin is on home page after Login..
    When Admin clicks Program on the navigation bar
    Then Admin navigated to Program page

  @MenubarValidation
  Scenario Outline: Verify the Menubar validation
    Given Admin is on home page after Login..
    When Admin clicks Program on the navigation bar
    Then Admin validates the program page  <elements>
    
    Examples: 
      | elements                                                  |
      |Validate heading LMS - Learning Management System...       |
      | Admin should see Logout in menu bar                       |
      | the page names as in order Home Program Batch Class       |    
      |Admin should see the submenu as Add new Program            |
      
  @ManageProgramValidation
  Scenario Outline: Verify the ManageProgram validation
    Given Admin is on home page after Login..
    When Admin clicks Program on the navigation bar
    Then Admin check the Manage Program page <validations>
    
    Examples:
    |validations                                 |
    |Admin should see the heading Manage Program               | 
    |Able to see Program name, description, and status for each program |
    |check column header on the Manage Program Page as  Program Name, Program Description, Program Status, Edit/Delete|
    |Check the Delete button in left top is disabled|     
    |check the Search bar with text as "Search..."| 
    |see checkbox default state as unchecked beside Program Name column header| 
    |see check box default state as unchecked on the left side in all rows against program name |
    |check the sort arrow icon beside to each column header except Edit and Delete |
    |see the Edit and Delete buttons on each row of the data table|
    |Showing x to y of z entries along with Pagination icon below the table| 
    |see the footer as In total there are z programs|
    
        
    
 
    
