@AddnewProgram


Feature: Program module
  I want to use this template for my feature file

  Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
    When Admin enters valid user and password with select role as Admin.
    Then Admin clicks Program on the navigation bar
    
      @AddnewProgram-1
  Scenario: Verify add new Program
    Given Admin is on ProgamPage
    When Admin clicks on Add New Program under the Program menu bar
    Then Admin should see pop up window for program details
    
    @AddnewPop-upWindowvalidation
  Scenario Outline: Verify the Menubar validation
    Given Admin is on ProgamPage
    When Admin clicks on Add New Program under the Program menu bar
    Then Admin validates the pop-upwindow  <titles>
    
    Examples: 
      | titles                                                  |
      |Admin should see window title as Program Details          |
      |Admin should see red  asterisk mark  beside mandatory field Name|
      
  @Cancelbutton
  Scenario: Verify the cancel button without entering any details
    Given Admin is on ProgamPage
    When Admin clicks on Add New Program under the Program menu bar
    And Clicks on Cancel button without entering any details
    Then Admin can see Program Details form disappears
    
    @AddNewProgramPopFieldsverification
     Scenario Outline: Verify Fields taking the arguments
    Given Admin is on ProgamPage
    When Admin clicks on Add New Program under the Program menu bar
    Then Admin validates the text entered in name,description,status <fields>
    
    Examples: 
      | fields                                              |
      |Admin can see the text entered in name field         |
      |Admin can see the text entered in description box|
      |Admin can see Active/Inactive status selected|
      
      
      
    @MissingMandatoryFields
    Scenario: Verify the error msg displayed
    Given Admin is on ProgamPage
    When Admin clicks on Add New Program under the Program menu bar
    And Clicks on Save button without entering any of the mandatory  details
    Then Admin gets error message 
    
    @SuccessfulAddnewProgram
    Scenario Outline: Verify Admin is able to save the program details
     Given Admin is on ProgamPage
    When Admin clicks on Add New Program under the Program menu bar
    Then  Enter the details to create a new program "<SheetName>"
    Then Admin gets message Successful Program created
    Examples: 
      |SheetName|
      |AddProgram| 
      
   @'X'button
  Scenario: Verify the cancel button without entering any details
    Given Admin is on ProgamPage
    When Admin clicks on Add New Program under the Program menu bar
    And Clicks on 'X' button without entering any details
  