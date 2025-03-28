#Author: Anusuya Selvaraj

@Batch
Feature: Add New Batch 

  @AddNewBatch
  Scenario Outline: Admin creates New Batch in LMS
  
   Given Admin in Home page
   When  Click Add New Batch
   Then  Create New Batches for "<TestCase>" in "<SheetName>"


    
    Examples: 
   |  TestCase             |SheetName|
   |MandatoryFields        |Batch| 
   |MandatoryFields        |Batch| 
   |WithoutBatchNameFields |Batch| 
   |WithoutDescripFields   |Batch| 
   |WithoutNumberClassFields |Batch| 
   |Alreadyexists          |Batch| 
   
 
 
 
  