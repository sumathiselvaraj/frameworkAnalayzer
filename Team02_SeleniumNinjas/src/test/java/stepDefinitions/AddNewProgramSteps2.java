package stepDefinitions;



import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddnewProgramPage2;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;



import driverFactory.DriverFactory;
import utils.ExcelReader;
import utils.LoggerLoad;
public class AddNewProgramSteps2 {
	
	private ExcelReader excelReader;

	
	private AddnewProgramPage2 Addnewprogram = new AddnewProgramPage2(DriverFactory.getDriver());
	
	@Given("Admin is on ProgamPage")
	public void admin_is_on_progam_page() {
		excelReader = new ExcelReader();
		LoggerLoad.info("Admin is on Program page");	
	 
	}

	@When("Admin clicks on Add New Program under the Program menu bar")
	public void admin_clicks_on_add_new_program_under_the_program_menu_bar() {
		
		Addnewprogram.Programbuttonclick();
	    
	}

	@Then("Admin should see pop up window for program details")
	public void admin_should_see_pop_up_window_for_program_details() {
		
		assertTrue(Addnewprogram.isPopupWindowDisplayed(), "Pop-up window for program details is not displayed");
		LoggerLoad.info("popup window Displayed");

	}
	
  //    @AddnewPop-upWindowvalidation
	
	@Then("Admin validates the pop-upwindow  Admin should see red  asterisk mark  beside mandatory field Name")
	public void admin_validates_the_pop_upwindow_admin_should_see_red_asterisk_mark_beside_mandatory_field_name() {
		  
		assertTrue(Addnewprogram.isNameAsteriskMarkDisplayed(), "Red asterisk mark is not displayed beside the Name field");      

	}
	
	
	@Then("Admin validates the pop-upwindow  Admin should see window title as Program Details")
	public void admin_validates_the_pop_upwindow_admin_should_see_window_title_as_program_details() {
		
		LoggerLoad.info("user is in program page");
		String actualHeading = Addnewprogram.Popupwindowtitle();
		String expectedHeading = "Program Details";
		assertEquals(actualHeading,expectedHeading,"The title did not match");
		LoggerLoad.info("Verified heading: " + actualHeading);

	}
	
//	@Cancelbutton
	
	@When("Clicks on Cancel button without entering any details")
	public void clicks_on_cancel_button_without_entering_any_details() {
	    LoggerLoad.info("Before clicking Cancel: Is form displayed? " +Addnewprogram.isProgramDetailsFormDisplayed());
		Addnewprogram.clickCancelButton();
	    LoggerLoad.info("After clicking Cancel: Is form displayed? " + Addnewprogram.isProgramDetailsFormDisplayed());
	}
	
	
	@Then("Admin can see Program Details form disappears")
	public void admin_can_see_program_details_form_disappears() {
		
		assertFalse(Addnewprogram.isProgramDetailsFormDisplayed(), "Program Details form is still displayed");

	}

	
	//@AddNewProgramPopFieldsverification
	@Then("Admin validates the text entered in name,description,status Admin can see the text entered in description box")
	public void admin_validates_the_text_entered_in_name_description_status_admin_can_see_the_text_entered_in_description_box() {
		Addnewprogram.enterDescription("Test Program Description");
        String actualName = Addnewprogram.getDescriptionText();
        assertEquals("Test Program Description", actualName, "Name field text is incorrect");
        LoggerLoad.info("Name field contains: " + actualName);
	}
	@Then("Admin validates the text entered in name,description,status Admin can see Active\\/Inactive status selected")
	public void admin_validates_the_text_entered_in_name_description_status_admin_can_see_active_inactive_status_selected() {
		
		Addnewprogram.selectStatus("Active");
	    LoggerLoad.info("Status selected successfully.");
		
	
	}
	
	@Then("Admin validates the text entered in name,description,status Admin can see the text entered in name field")
	public void admin_validates_the_text_entered_in_name_description_status_admin_can_see_the_text_entered_in_name_field() {
		
		 Addnewprogram.enterName("Test Program");
	        String actualName = Addnewprogram.getNameText();
	        assertEquals("Test Program", actualName, "Name field text is incorrect");
	        LoggerLoad.info("Name field contains: " + actualName);

	}

	
	@When("Clicks on Save button without entering any of the mandatory  details")
	public void clicks_on_cancel_button_without_entering_any_of_the_mandatory_details() {
		Addnewprogram.clickSaveButton();
	}
	
	
	
	@Then("Admin gets error message")
	public void admin_gets_error_message() {
       String actualMessage = Addnewprogram.getProgramNameErrorMessage();
		
       assertNotNull(actualMessage, "The error message should not be null");
       assertFalse(actualMessage.isEmpty(), "The error message should not be empty");
       
       // Assert that the error message contains specific keywords (e.g., "required")
       assertTrue(actualMessage.contains("required"), 
       "Expected error message to contain 'required', but found: '" + actualMessage + "'");
       
       LoggerLoad.info("Verified error message: " + actualMessage);
	}

	@Then("Enter the details to create a new program {string}")
	public void enter_the_details_to_create_a_new_program(String SheetName)  {
		 String excelFilePath = "src/test/resources/TestData/TestData.xlsx";
	        List<Map<String, String>> testData = excelReader.getData(excelFilePath, SheetName);

	        // Assuming the first row contains the data
	        Map<String, String> data = testData.get(0);
	        

	        String programName = data.get("ProgramName");
	        String programDescription = data.get("ProgramDescription");
	        String status = data.get("status");
	        
	        Addnewprogram.enterName(programName);
	        Addnewprogram.enterDescription(programDescription);
	        Addnewprogram.selectStatus(status);
	  
	}

	@Then("Admin gets message Successful Program created")
	public void admin_gets_message_successful_program_created() {
		Addnewprogram.clickSaveButton();
	        String actualMessage = Addnewprogram.getSuccessMessage();
	        String expectedSuccessMsgHeading = "Program Created Successfully";
	        assertEquals(actualMessage, expectedSuccessMsgHeading, "The success message did not match");
	        LoggerLoad.info("Verified success message: " + actualMessage);
	 
	}

	//@'X'button
	@When("Clicks on {string} button without entering any details")
	public void clicks_on_button_without_entering_any_details(String string) {
	  Addnewprogram.Xbuttonclick();
	}

}
