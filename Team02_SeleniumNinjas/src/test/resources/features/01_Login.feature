
Feature: LMS portal-Login Page Verification.

 Background: Verify if admin is able to land on home page
    When Admin gives the correct LMS portal URL
    Then Admin should land on the login page
#TC2
 Scenario: Verify if admin is able to land on home page with invalid URL.
    When Admin gives the invalid LMS portal URL
    Then Admin should recieve 404 page not found error.

  Scenario: Verify if there should be any broken links on the website
    Given Admin launch the browser
    When Admin gives the correct LMS portal URL to check for broken links.
    Then HTTP response >= 400,Then the link is broken
  
  
  Scenario: Verify the text spelling in the page  
    Then Admin should see correct spellings in all fields 
    
   
  Scenario: Verify if admin is sucssessfuly logged into LMS portal.
    Given Admin is in LoginPage
    When  Admin enters valid user and password with select role as Admin.
    Then  Admin should be landing to home page
    
   
  Scenario: Verify if logo is displayed in loginPage
     Then Admin should see expected logo image in login page
 
  Scenario: Validate field allignments in loginPage
    Then Admin should see input and loginbutton are in center of login page
    
   	Scenario Outline: Admin is on login page and tried to login with invalid inputs from Excel with "<Sheetname>" and <RowNumber>  #TC8,9,10,11
    Given Admin is on signin page
    When Admin enters sheet "<Sheetname>" and <RowNumber> from excel file,selects role from the dropdown and clicks login button .
    Then Admin should see the Error message.
    
     Examples: 
        | Sheetname | RowNumber |
        | Login    |         0 |
        | Login    |         1 |
        | Login    |         2 |
        | Login    |         3 |
        
   ###################################################################################
   
   
   
    Scenario: Verify application name # TC#12
    Then Admin should see  LMS - Learning Management System

  
   Scenario: Verify company name # TC#13

    Then Admin should see company name below the app name

  Scenario: Validate sign in content # TC#14
    Then Admin should see "Please login to LMS application"

  Scenario: Verify text field is present # TC#15
    Then Admin should see 2 text field

  Scenario: Verify text on the first text field # TC#16
    Then Admin should  see "User" in the first text field

  Scenario: Verify asterik next to Admin text # TC#17
    Then Admin should see field mandatory asterik symbol next to Admin text

  Scenario: Verify text on the second text field # TC#18
    Then Admin should "Password" in the second text field
    
  Scenario: Verify asterik next to password text # TC#19
    Then Admin should see * symbol next to password text

  Scenario: Verify the alignment input field for the login  # TC#20
    Then Admin should see input field on the centre of the page
   
   Scenario: verify drop down is present for the selected role # TC#21
    Then Admin should see drop down to select the particular role
   
   
  Scenario: verify Login button is present # TC#22
    Then Admin should see login button

  
  Scenario: Verify the alignment of the login button # TC#23

    Then Admin should see login button on the centre of the page

 
  Scenario: Verify input descriptive test in user field # TC#24
    Then Admin should see Admin in gray color


  Scenario: Verify input descriptive test in password field # TC#25
  Then Admin should see password in gray color     
        #
  ###########################
  #
  Scenario: verify login button action through keyboard # TC#226
    When Admin enter valid credentials  and clicks login button through keyboard
    Then Admin should land on dashboard page with successful keyboard action.

  Scenario: verify login button action through mouse # TC#227
  
    When Admin enter valid credentials  and clicks login button through mouse
    Then Admin should land on dashboard page with successful mouse action.

    