#Author: Anusuya S


  @Batch
Feature: Delete Batch Validation
 Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
    When Admin enters valid user and password with select role as Admin.
 
    
  @DeleteBatchValidation
  
  Scenario: validate delete Icon on any row
  
  Given Admin in batch page
  When Admin clicks the delete Icon on any row in batchpage
  Then Admin should see the confirm alert box with yes and no button
  
  @DeleteBatchValidation
  
  Scenario: Validate yes button on the confirm alert box
  
  Given Admin is on the batch confirm popup page
  When Admin clicks on the delete icon and click yes button in batch popup
  Then Admin should see the successful message and the batch should be deleted
  
  @DeleteBatchValidation
  
  Scenario: validate no button on the confirm alert box
  
  Given Admin is on the batch confirm popup page
  When Admin clicks on the delete icon and click no button in batch popup
  Then Admin should see the alert box closed and the batch is not deleted
  
   @DeleteBatchValidation
    Scenario: validate close Icon on the alert box
  
	Given Admin is on the batch confirm popup page
	When Admin clicks on the close icon in batch confirm popup
	Then Admin should see the alert box closed
	
	 @DeleteBatchValidation
	
	Scenario: Validate single row delete with checkbox
	
	Given Admin in batch page
	When Admin clicks on the delete icon under the Manage batch header
	Then The respective row in the table should be deleted
	 
	 @DeleteBatchValidationn
	
	Scenario: Validate multiple row delete with checkbox
	Given Admin in batch page
	When Admin clicks on the multipledelete icon under the Manage batch header
	Then The respective row in the table should be deleted
	
 
   
   
   

  
  
  