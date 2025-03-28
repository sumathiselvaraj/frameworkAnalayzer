
@class
Feature: Manage Class Page Verification
  
	 #Background:
 	#Given Admin is on dashboard page after Login and Admin clicks "Class" button on the navigation bar
 	
 	 Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
    When Admin enters valid user and password with select role as Admin.
    When  Admin clicks "Class" button on the navigation bar
    
 
  		Scenario: Validate Class Page title
    	Then The page title should be "Manage Class"
    
     	Scenario: Verify Class grid headers
    	Then The class grid should display the following headers:
      | Check box | Batch Name | Class Topic | Class Description | Status | Class Date | Staff Name | Edit / Delete |
      
     	Scenario: Verify Class search bar is visible
     Then Admin should see the Search Bar in Manage class page
     
      Scenario: verify class pagination controls
      Then Admin should see the " Showing 1 to 10 of 12 entries" and enabled pagination controls under the data table
      
      Scenario: Verify Datatable footer text is displayed
      Then Admin should see Total no of classes in below of the data table.
      
     @Sorting
     Scenario Outline: Verify sorting functionality in Class grid
	    When User clicks on the "<column>" header to sort
	    Then The grid should display sorted results based on "<column>"
	
			Examples:
		    | column      			|
		    | batchName   		  |
		    | classTopic   		  |
		   
			      
      
	      
      
     
    
    