#Author: Anusuya Selvaraj


@Batch
Feature: Batch Page Navigation
  
 Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
    When Admin enters valid user and password with select role as Admin.
 

  @BatchPageNavigation	


  Scenario: Admin in Batch Page
   Given Admin is on the home Page
    When  Admin clicks Batch tab on top right corner of the LMS page
    Then  Verify the Batch page is displayed
