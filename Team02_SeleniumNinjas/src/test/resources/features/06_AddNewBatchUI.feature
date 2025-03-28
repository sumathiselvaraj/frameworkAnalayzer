#Author: Anusuya Selvaraj

@Batch
Feature: Add Batch Popup Validation 
  UI validation of the  Add Batch popup


  Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
    When Admin enters valid user and password with select role as Admin.
  @AddBatchUI
  Scenario: Verify sub menu displayed in batch menu bar
  
    Given Admin in Batch Home page
    When  Admin clicks "Batch" on the navigation bar
    Then  Admin should see sub menu in menu bar as "Add New Batch"
    
    Given Admin in Batch Home page
    When  Admin clicks on "Add New batch" under the "batch" menu bar
    Then Admin should see the "Batch Details" pop up window
    
    
    