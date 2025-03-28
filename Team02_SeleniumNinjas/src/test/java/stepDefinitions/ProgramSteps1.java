package stepDefinitions;

import java.util.Arrays;
import java.util.List;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pageObjects.ProgramPage1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;



import driverFactory.DriverFactory;
import utils.LoggerLoad;
public class ProgramSteps1 {
	

	private ProgramPage1 program = new ProgramPage1(DriverFactory.getDriver());
	
	
	//ProgramTestCase-1
	
	@Given("Admin is on home page after Login..")
	public void admin_is_on_home_page_after_login() {
				
		LoggerLoad.info("user is in home page");
		
	}

	@When("Admin clicks Program on the navigation bar")
	public void admin_clicks_program_on_the_navigation_bar() {
		
		program.Programbuttonclick();
		 
	}

	@Then("Admin navigated to Program page")
	public void admin_navigated_to_program_page() {
		
		 String expectedUrl = "https://feb-ui-hackathon-bbfd38d67ea9.herokuapp.com/program"; 
		    String actualUrl = DriverFactory.getDriver().getCurrentUrl();
		    assertEquals(actualUrl, expectedUrl, "URL does not match the expected Program page URL");
		    LoggerLoad.info("The user is on Program Page");

	

		}
	

	
	@Then("Admin validates the program page  Validate heading LMS - Learning Management System...")
	public void admin_validates_the_program_page_validate_heading_lms_learning_management_system() {
		LoggerLoad.info("user is in program page");
		String actualHeading = program.getMaintitletext();
		String expectedHeading = "LMS - Learning Management System";
		assertEquals(actualHeading,expectedHeading,"The title did not match");
		LoggerLoad.info("Verified heading: " + actualHeading);
		}

	@Then("Admin validates the program page  Admin should see Logout in menu bar")
	public void admin_validates_the_program_page_admin_should_see_logout_in_menu_bar() {
	 LoggerLoad.info("Need to check LOgout");
	 String actualText = program.getLogoutText();
	 String expectedLogoutheading = "Logout";
	 assertEquals(actualText,expectedLogoutheading,"The logout title did not match");
	LoggerLoad.info("Verified Logout: " + actualText);
	}

 
	 
	

	@Then("Admin validates the program page  the page names as in order Home Program Batch Class")
	public void admin_validates_the_program_page_the_page_names_as_in_order_home_program_batch_class() {

		
		List<String> actualPageNames = program.getNavbarPageNames();
		List<String> expectedPageNames = Arrays.asList("Home", "Program", "Batch", "Class", "Logout");
		assertEquals(actualPageNames,expectedPageNames,"Page names are not in the correct order");
		
		LoggerLoad.info("The order of the buttons displayed in the navbar" + actualPageNames);
	}

	
	@Then("Admin validates the program page  Admin should see the submenu as Add new Program")
	public void admin_validates_the_program_page_admin_should_see_the_submenu_as_add_new_program() {
		String actualsubmenu = program.AddnewProgrambuttoncheck();
        String expectedsubmenuHeading = "Add New Program";
        System.out.println("the actual message" + actualsubmenu);
        assertEquals( actualsubmenu,expectedsubmenuHeading,"Manage Program heading text does not match");
        LoggerLoad.info("The title of Manage Program check" + actualsubmenu ); 
	}
	
	
	//@ManageProgramValidation
	@Then("Admin check the Manage Program page Admin should see the heading Manage Program")
	public void admin_check_the_manage_program_page_admin_should_see_the_heading_manage_program() {
		LoggerLoad.info("Need to check Mange program");

	        String actualHeading = program.getManageProgramHeadingText();
	        String expectedHeading = "Manage Program";	       
	        assertEquals( actualHeading,expectedHeading,"Manage Program heading text does not match");
	        LoggerLoad.info("The title of Manage Program check" + actualHeading ); 
	}
	
	
	@Then("Admin check the Manage Program page Able to see Program name, description, and status for each program")
	public void admin_check_the_manage_program_page_able_to_see_program_name_description_and_status_for_each_program() {
		
		int noofprograms = program.getNumberOfPrograms();
		System.out.println("The total no of programs" + noofprograms);
		
		for (int i=0;i<noofprograms;i++) {
			   String programName = program.getProgramName(i);
	            String programDescription = program.getProgramDescription(i);
	            String programStatus = program.getProgramStatus(i);
	            
	            assertNotNull(programName, "Program name should not be null for program at index " + i);
	            assertNotNull(programDescription, "Program description should not be null for program at index " + i);
	           assertNotNull(programStatus, "Program status should not be null for program at index " + i);
			
			
		}
		
	 
	}
	@Then("Admin check the Manage Program page check column header on the Manage Program Page as  Program Name, Program Description, Program Status, Edit\\/Delete")
	public void admin_check_the_manage_program_page_check_column_header_on_the_manage_program_page_as_program_name_program_description_program_status_edit_delete() {
		 String programNameHeader = program.getProgramNameHeaderText();
	        String programDescriptionHeader = program.getProgramDescriptionText();
	        String programStatusHeader = program.getProgramStatusText();
	        String programEditdeleteheader = program.getEditdeletetxt();

	        // Assertions to verify the headers
	        assertEquals(programNameHeader, "Program Name", "Program Name header is incorrect");
	        assertEquals(programDescriptionHeader, "Program Description", "Program Description header is incorrect");
	        assertEquals(programStatusHeader, "Program Status", "Program Status header is incorrect");
	        assertEquals(programEditdeleteheader,"Edit / Delete","Program Edit/Delete is incorrect");
		
		}


	@Then("Admin check the Manage Program page Check the Delete button in left top is disabled")
	public void admin_check_the_manage_program_page_check_the_delete_button_in_left_top_is_disabled() {
		boolean isDeleteButtonDisabled = program.isMainDeleteButtonDisabled();
        assertTrue(isDeleteButtonDisabled, "Delete button should be disabled");
        LoggerLoad.info("The delete button is in disable state" + isDeleteButtonDisabled);
	}
	
	
	@Then("Admin check the Manage Program page check the Search bar with text as {string}")
	public void admin_check_the_manage_program_page_check_the_search_bar_with_text_as(String expectedPlaceholderText) {
		boolean isSearchBarDisplayed = program.getsearchboxtextlocator();
        assertTrue(isSearchBarDisplayed, "Search bar should be displayed");

        // Assert that the placeholder text matches the expected text
        String actualPlaceholderText = program.getSearchBarPlaceholderText();
        assertEquals(actualPlaceholderText, expectedPlaceholderText, "Search bar placeholder text is incorrect");
        LoggerLoad.info("The search bar place holder is " + actualPlaceholderText);
	}
	
	@Then("Admin check the Manage Program page see checkbox default state as unchecked beside Program Name column header")
	public void admin_check_the_manage_program_page_see_checkbox_default_state_as_unchecked_beside_program_name_column_header() {
		 boolean isCheckboxDisplayed = program.getcheckboxonHeaderlocator();
	        assertTrue(isCheckboxDisplayed, "Checkbox beside Program Name column header should be displayed");

	        // Assert that the checkbox is unchecked
	        boolean isCheckboxUnchecked = program.isCheckboxUnchecked();
	        assertTrue(isCheckboxUnchecked, "Checkbox beside Program Name column header should be unchecked");
	        
	        LoggerLoad.info("The checkbox is unchecked (true or false)" + isCheckboxUnchecked);
	}
	
	@Then("Admin check the Manage Program page see check box default state as unchecked on the left side in all rows against program name")
	public void admin_check_the_manage_program_page_see_check_box_default_state_as_unchecked_on_the_left_side_in_all_rows_against_program_name() {
		
		 int numberOfCheckboxes = program.getcheckboxlistsize();
		 LoggerLoad.info("THe list of checkbox size" + numberOfCheckboxes);
	     assertTrue(numberOfCheckboxes > 0, "No checkboxes found on the page");

	     boolean areAllCheckboxesUnchecked = program.areAllCheckboxesUnchecked();
	        assertTrue(areAllCheckboxesUnchecked, "Not all checkboxes are unchecked");
	}
	
	@Then("Admin check the Manage Program page see the Edit and Delete buttons on each row of the data table")
	public void admin_check_the_manage_program_page_see_the_edit_and_delete_buttons_on_each_row_of_the_data_table() {
		
		boolean areAllEditButtonsDisplayed = program.areAllEditButtonsDisplayed();
        assertTrue(areAllEditButtonsDisplayed, "Not all Edit buttons are displayed");
        LoggerLoad.info("Alledit buttons displayed(T/F) "+ areAllEditButtonsDisplayed);
        boolean areAllDeleteButtonsDisplayed = program.areAllDeleteButtonsDisplayed();
        assertTrue(areAllDeleteButtonsDisplayed, "Not all Delete buttons are displayed");
        LoggerLoad.info("All delete buttons displayed(T/F) "+ areAllDeleteButtonsDisplayed);

	}
	
	@Then("Admin check the Manage Program page Showing x to y of z entries along with Pagination icon below the table")
	public void admin_check_the_manage_program_page_showing_x_to_y_of_z_entries_along_with_pagination_icon_below_the_table() {
		 String actualPaginationText = program.getPaginationText();			 
	     LoggerLoad.info("The actual pagination text is " +actualPaginationText);
	    
	}
	
	@Then("Admin check the Manage Program page see the footer as In total there are z programs")
	public void admin_check_the_manage_program_page_see_the_footer_as_in_total_there_are_z_programs() {
		 String actualFooterText = program.getFooterText();
		 
		 int z = program.extractNumberOfPrograms(actualFooterText);		
		 String  ExpectedFooterText =  "In total there are "+z+" programs.";
		 assertTrue(actualFooterText.contains(ExpectedFooterText), "Footer text does not contain the expected substring: " + ExpectedFooterText);
		

	}
	
	@Then("Admin check the Manage Program page check the sort arrow icon beside to each column header except Edit and Delete")
	public void admin_check_the_manage_program_page_check_the_sort_arrow_icon_beside_to_each_column_header_except_edit_and_delete() {
		
		boolean areAllSortArrowIconsDisplayed = program.areAllSortArrowIconsDisplayed();
        assertTrue(areAllSortArrowIconsDisplayed, "Not all sort arrow icons are displayed");
	}

}
