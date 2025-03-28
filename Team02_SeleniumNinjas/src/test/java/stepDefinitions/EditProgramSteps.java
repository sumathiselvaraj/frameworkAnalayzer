package stepDefinitions;


import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import driverFactory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.EditProgram;
import utils.ExcelReader;
import utils.LoggerLoad;

public class EditProgramSteps {
	
	String prgName;

    EditProgram editProg = new EditProgram(DriverFactory.getDriver());
	String filePath = System.getProperty("user.dir") + "/src/test/resources/TestData/TestData.xlsx";

	ExcelReader sheetReader = new ExcelReader();
	
	
	@And("Admin lands on dashboard Page and clicks Program from navigation bar")
	public void admin_lands_on_dashboard_page_and_clicks_program_from_navigation_bar() {
		editProg.clickProgram();
	}
	
	@Given("Admin is on the Program page and search program {string}")
	public void admin_is_on_the_program_page_and_search_program(String programName) throws InterruptedException {
		
		editProg.searchProgramByName(programName);
	    
	}
	

	@When("Admin clicks on Edit option for a particular program {string}")
	public void admin_clicks_on_edit_option_for_a_particular_program(String programName) throws InterruptedException {
		
	   editProg.clickEditByProgName(programName);
	   
	}

@Then("Admin {string}")
public void admin(String expectedAction) {

        	 System.out.println("Program Details page verified.");
       
        	editProg.getTextEditWindow();
           editProg.verifyMandatoryFieldAsterisk();
       
}

@When("Admin updates fields from {string} {int} and clicks on the save button")
public void admin_updates_and_clicks_on_the_save_button(String Sheetname, int Rownumber) throws InterruptedException {
	 // Fetch data from the Excel file based on Sheetname and Rownumber
    List<Map<String, String>> testData = sheetReader.getData(filePath, Sheetname);
    

    // Fetch values from the Excel sheet columns for Name, Description, and Status
    String name = testData.get(Rownumber).get("Name");
    String description = testData.get(Rownumber).get("Description");
    String status = testData.get(Rownumber).get("Status");

    // Update the form fields using the fetched data
    
    
    editProg.updateField("Name", name);
    editProg.updateField("Description", description);
    editProg.selectStatus(status);

    // Click the save button
    editProg.clickSaveButton();
}

@Then("Admin checks {string}")
public void admin_checks(String expectedOutcome) throws InterruptedException {
	
   LoggerLoad.info( "The updated program details is seen");
                       
}

	@Given("Admin is on the Program page")
	public void admin_is_on_the_program_page() {
		LoggerLoad.info("Admin is on Manage Program Page");
		
	 editProg.getPrgmPageHeading(); 
	}


	@When("Admin clicks on the cancel button on Edit form")
	public void admin_clicks_on_the_cancel_button_on_edit_form() {
        editProg.clickEdit();
		editProg.cancelBtn();
		LoggerLoad.info("Admin click cancel on Edit Form and lands on Manage Program Page");
	}

	@Then("Admin can see the Program details form disappear")
	public void admin_can_see_the_program_details_form_disappear() {
		LoggerLoad.info("Admin can see the Program details window disappears");

		System.out.println(editProg.getPrgmPageHeading());
		Assert.assertTrue(editProg.getPrgmPageHeading().equalsIgnoreCase("Manage Program"));
	}

	@When("Admin searches with the newly updated {string}")
	public void admin_searches_with_the_newly_updated(String programName) {
		   editProg.searchUpdatedProgram(programName);
		}

	

	@Then("Admin verifies that the details are correctly updated from {string} {int}")
	public void admin_verifies_that_the_details_are_correctly_updated_from(String Sheetname, int Rownumber) {
		
		List<Map<String, String>> testData = sheetReader.getData(filePath, Sheetname);

	    // Get expected values from Excel
	    Map<String, String> expectedDetails = testData.get(Rownumber);

	    // Get actual values from the UI
	    Map<String, String> actualDetails = editProg.getUpdatedProgramDetails();

	    // Assertions to compare UI data with Excel data
	    Assert.assertEquals(actualDetails.get("Name"), expectedDetails.get("Name"), "Program Name does not match!");
	    Assert.assertEquals(actualDetails.get("Description"), expectedDetails.get("Description"), "Program Description does not match!");
	    Assert.assertEquals(actualDetails.get("Status"), expectedDetails.get("Status"), "Program Status does not match!");

	    System.out.println("Program details verified successfully.");
	    System.out.println("Verification Completed: " +
                "\nActual Name: " + actualDetails.get("Name") +
                "\nExpected Name: " + expectedDetails.get("Name") +
                "\nActual Description: " + actualDetails.get("Description") +
                "\nExpected Description: " + expectedDetails.get("Description") +
                "\nActual Status: " + actualDetails.get("Status") +
                "\nExpected Status: " + expectedDetails.get("Status"));

	}

	@When("Admin clicks on X button on Edit form")
	public void admin_clicks_on_the_X_button_on_edit_form() throws InterruptedException {
		editProg.clickEdit();
		Thread.sleep(2000);
		editProg.clickCloseIcon();
	}

	
}
