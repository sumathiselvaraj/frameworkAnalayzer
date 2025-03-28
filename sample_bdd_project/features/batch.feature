Feature: LMS Batch Management Functionality
  As an administrator of the Learning Management System
  I want to be able to manage batches
  So that I can organize students into groups for specific programs

  Background:
    Given I am logged in as an Admin
    And I am on the Batch page

  Scenario: View list of batches
    Then I should see a list of all batches
    And I should see columns for batch name, program, start date, end date, and status
    And I should see options to add, edit, and delete batches

  Scenario: Add a new batch
    When I click on Add New Batch button
    Then I should see the batch creation form
    When I enter batch name "DS-2023-Spring"
    And I enter batch description "Data Science Spring 2023 Cohort"
    And I select program "Data Science Fundamentals"
    And I select batch status "Active"
    And I enter start date "01/15/2023"
    And I enter end date "06/30/2023"
    And I click the Save button
    Then I should see a success message
    And the batch "DS-2023-Spring" should appear in the batch list

  Scenario: Edit an existing batch
    When I search for batch "JAVA-2023-Jan"
    And I click edit button for the batch
    Then I should see the batch edit form with current values
    When I change the end date to "08/15/2023"
    And I click the Save button
    Then I should see a success message
    And the batch "JAVA-2023-Jan" should have the updated end date

  Scenario: Delete a batch
    When I search for batch "WEB-2022-Dec"
    And I click delete button for the batch
    Then I should see a confirmation dialog
    When I confirm the deletion
    Then I should see a success message
    And the batch "WEB-2022-Dec" should no longer appear in the batch list

  Scenario: Search for a batch
    When I enter "Python" in the search field
    And I click the search button
    Then I should only see batches containing "Python" in their name or program
    
  Scenario: Filter batches by program
    When I select program filter "Java Programming"
    Then I should only see batches for program "Java Programming"
    When I select program filter "All Programs"
    Then I should see batches for all programs
    
  Scenario: Filter batches by status
    When I select status filter "Inactive"
    Then I should only see batches with status "Inactive"
    When I select status filter "All"
    Then I should see all batches regardless of status