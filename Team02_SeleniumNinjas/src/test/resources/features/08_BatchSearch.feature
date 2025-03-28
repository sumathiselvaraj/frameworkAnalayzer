
#Author: Anusuya S
Feature: Search New Batch created

@Batch
 @SearchAddedBatch
  Scenario: validate search box functionality
   Given Admin in search batch page
   When Admin enters the batch name in the search text box
   Then Admin should see the filtered batches in the data table
 