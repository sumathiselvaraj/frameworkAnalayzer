#Author: Anusuya Selvaraj


@Batch
Feature: Batch Page UI Validation 
 
 Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
    When Admin enters valid user and password with select role as Admin.


  @BatchPageValidation
    Scenario: Admin in Batch Page
  
    Given Admin in Batch Home Page
    When Admin in batch page   
    Then Validate Title in Batch Page
    
    
    Then Validate Heading in Batch Page   
    
   
    Then Validate disabled Delete Icon under the header in the Batch Page
    
    
  
    Then Validate pagination in the Batch Page
    

    Then Validate edit icon in each data rows
    
    
   
    Then validate delete icon in each data rows
    
    

    Then validate checkbox in each data rows
    
    
 
    Then Validate batch Datatable headers
    

    Then Validate Checkbox in the Datatable header row
    

    Then Validate sort icon next to all the datatable header
    
    
