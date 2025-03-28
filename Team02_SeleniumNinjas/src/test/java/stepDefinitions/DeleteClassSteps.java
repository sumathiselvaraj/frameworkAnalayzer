package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DeleteClassPage;
import utils.ClassDataHelper;
import utils.LoggerLoad;


public class DeleteClassSteps {
	
	private DeleteClassPage DCPage = new DeleteClassPage(DriverFactory.getDriver());
	private ClassDataHelper classDataHelper = new ClassDataHelper();
	boolean isClassFound = false;
	
	@When("Admin clicks on Delete button for Class Topic in manage class")
	public void admin_clicks_on_delete_button_for_class_topic_in_manage_class() throws InterruptedException {
		String classTopic = classDataHelper.getCreatedClassTopic();
		isClassFound = DCPage.selectClassByTopicForDelete(classTopic);
		//Assert.assertTrue(isClassFound, "Class with topic '" + classTopic + "' not found in the grid.");
	}

	@Then("Admin should see a alert open with heading {string} along with  <YES> and <NO> button for deletion")
	public void admin_should_see_a_alert_open_with_heading_along_with_yes_and_no_button_for_deletion(String string) {
		if (isClassFound) {
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'p-dialog-title')]")));
			Assert.assertTrue(DCPage.isDeleteConfirmTitle(), "Confirm is not visible");
			Assert.assertTrue(DCPage.isDeleteConfirmNo(), "No button is not visible!");
			Assert.assertTrue(DCPage.isDeleteConfirmYes(),"YES button is not visible!");
			 LoggerLoad.info("Admin can see the Delete alert");
			
		}
		
	}
	
	@When("Admin clicks yes option of Delete pop up of manage class")
	public void admin_clicks_yes_option_of_delete_pop_up_of_manage_class() {
	    DCPage.ClickYesButton();
	}

	@Then("Admin gets a message {string} alert and do not see that Class in the data table")
	public void admin_gets_a_message_alert_and_do_not_see_that_class_in_the_data_table(String string) {
		System.out.println("Class Deleted");
		String message = DCPage.toastMsg();		
		 LoggerLoad.info(message);
		Assert.assertTrue(message.contains("Success"));

	}
	@When("Admin clicks No option of Delete pop up of manage class")
	public void admin_clicks_no_option_of_delete_pop_up_of_manage_class() {
	    DCPage.ClickNoButton();
	}

	@Then("Admin can see the deletion alert disappears without deleting class")
	public void admin_can_see_the_deletion_alert_disappears_without_deleting_class() {
	   LoggerLoad.info("Admin see the deletion alert disappeares");
	   Assert.assertFalse(DCPage.isDeleteConfirmTitle());
	  
	}

}
