package stepDefinitions;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DeleteProgram;
import pageObjects.EditProgram;
import utils.CommonFunctions;

public class DeleteProgramSteps {

	DeleteProgram delPg = new DeleteProgram (DriverFactory.getDriver());
	 EditProgram editProg = new EditProgram(DriverFactory.getDriver());
	
    @When("Admin clicks on delete button for a program")
    public void admin_clicks_on_delete_button_for_a_program() {
        delPg.clickDeleteIcon();
        
    }

    @Then("Admin will get confirm deletion popup")
    public void admin_will_get_confirm_deletion_popup() {
    	String pageHeading= delPg.confirmDeletePopup();
    	   Assert.assertEquals(pageHeading, "Confirm", "Header mismatch!");

       
   
    }
    
  
@When("Admin Searches for {string} on the Program page")
public void admin_searches_for_on_the_program_page(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("Admin clicks on No button")
public void admin_clicks_on_button() {
    delPg.ConfirmDeleteNo();
}


@Then("Admin can see Confirm Deletion form disappear")
public void admin_can_see_confirm_deletion_form_disappear() {
   String pageHeading= editProg.getPrgmPageHeading();
   Assert.assertEquals(pageHeading, "Manage Program", "Header mismatch!");
   System.out.printf("User is at after closing Confirm Delete", pageHeading);

}

@When("Admin clicks on X button")
public void admin_clicks_on_X_button() {
    delPg.deleteClose();
}
@Given("Admin searches for {string}")
public void admin_searches_for(String ProgramName) throws InterruptedException {  
 	editProg.searchProgramByName(ProgramName);
 	//Thread.sleep(2000);
 	
 	  delPg.clickDeleteByProgName(ProgramName);
 }
   
 @When("Admin clicks on {string} button on the Confirm deletion form")
 public void admin_clicks_on_button_on_the_confirm_deletion_form(String action) throws InterruptedException {
     // Click the confirm deletion button based on the action
     delPg.clickConfirmDeletionButton(action);
     Thread.sleep(1000);
     System.out.println("Clicked '" + action + "' button on the Confirm deletion form.");
 }
//
// @Then("Admin can see {string}")
// public void admin_can_see(String expectedMessage) throws InterruptedException {
// 	 String actualMessage = delPg.Deletedhandlealert(expectedMessage);
// 	       	    System.out.println("Verified message: " + actualMessage);
// 	}

 @Then("Admin can see success message")
public void admin_can_see_success_message() throws InterruptedException {
	 String actualMessage = delPg.deletehandlealert();
	       	    System.out.println("Verified message: " + actualMessage);
	       	 Assert.assertEquals(actualMessage.trim().replaceAll("\\s+", " "), 
	                    "Successful Program Deleted", 
	                    " header mismatch!");

	}


      
       
    
	
	
	
}
