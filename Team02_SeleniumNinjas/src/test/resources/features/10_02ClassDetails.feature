
@ClassDetails
Feature: Class details Popup window verification
 	 
 	  Background: The user able to land on Home page after entering valid Username and Password fields
    Given Admin is in LoginPage
     When Admin enters valid user and password with select role as Admin.
     When Admin clicks "Class" button on the navigation bar
     When Admin clicks on "Add New Class" under the class menu bar
 	
 	@VerifyClassDetails
			Scenario: Verify class details popup window
      Then Admin should see a popup  with  heading "Class Details"