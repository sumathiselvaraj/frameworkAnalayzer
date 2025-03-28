@EditClass
Feature: Edit Class 
 	 
 	 Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
    When Admin enters valid user and password with select role as Admin.
     When Admin clicks "Class" button on the navigation bar
     When Admin clicks on Edit button for Class Topic in manage class
 	 
  @EditPopup
  Scenario: Verify Edit pop up of class   
    Then A new Edit pop up with class details appears
    
  @BatchFieldEdit
  Scenario: Verify Batch Field Edit pop up of class   
    Then Admin should see batch name field is disabled in class edit pop up
    
  @ValidateEditClassValidMandatefields
  Scenario Outline: Validate admin able to Edit class with valid data in mandatory fields
    When Admin enters all mandatory field values with valid data in Edit class and clicks save button "<TestCase>" and "<Sheetname>" 
    Then Admin gets message class updated Successfully and see the updated values in data table

    Examples: 
      | TestCase                         | Sheetname        |
      | AllMandateFieldsValidDataForEdit | Class					  |
      
      
   @ValidateEditClassWithInvalidData 
   Scenario Outline: Validate admin able to Edit class with invalid data in  fields
    When Admin enters all  field values with invalid data in Edit class and clicks save button "<TestCase>" and "<Sheetname>" 
    Then Admin should get error message

    Examples: 
      | TestCase                                         | Sheetname        |
      |  InvalidDataForMandatoryFieldsForEdit            | Class					  |
      
   @ValidateEditClassWithSpecialChar 
   Scenario Outline: Validate admin able to Edit class with special characters and numbers in  fields
    When Admin enters field values with invalid data of special characters and numbers in Edit class and clicks save button "<TestCase>" and "<Sheetname>" 
    Then Admin should get error message for Edit Class

    Examples: 
      | TestCase                                         | Sheetname        |
      |  InvalidDataForMandatoryFieldsForEdit            | Class					  |   
   
   
 
  
    

    