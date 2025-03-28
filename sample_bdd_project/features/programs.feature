Feature: LMS Program Management Functionality
  As an administrator of the Learning Management System
  I want to be able to manage programs
  So that I can organize courses for students

  Background:
    Given I am logged in as an Admin
    And I am on the Programs page

  Scenario: View list of programs
    Then I should see a list of all programs
    And I should see columns for program name, description, and status
    And I should see options to add, edit, and delete programs

  Scenario: Add a new program
    When I click on Add New Program button
    Then I should see the program creation form
    When I enter program name "Data Science Fundamentals"
    And I enter program description "Comprehensive program covering data analysis, visualization, and machine learning basics"
    And I select program status "Active"
    And I click the Save button
    Then I should see a success message
    And the program "Data Science Fundamentals" should appear in the program list

  Scenario: Edit an existing program
    When I search for program "Python Programming"
    And I click edit button for the program
    Then I should see the program edit form with current values
    When I change the program description to "Updated program covering Python fundamentals and advanced topics"
    And I click the Save button
    Then I should see a success message
    And the program "Python Programming" should have the updated description

  Scenario: Delete a program
    When I search for program "HTML Basics"
    And I click delete button for the program
    Then I should see a confirmation dialog
    When I confirm the deletion
    Then I should see a success message
    And the program "HTML Basics" should no longer appear in the program list

  Scenario: Search for a program
    When I enter "Java" in the search field
    And I click the search button
    Then I should only see programs containing "Java" in their name or description
    
  Scenario: Filter programs by status
    When I select status filter "Inactive"
    Then I should only see programs with status "Inactive"
    When I select status filter "All"
    Then I should see all programs regardless of status