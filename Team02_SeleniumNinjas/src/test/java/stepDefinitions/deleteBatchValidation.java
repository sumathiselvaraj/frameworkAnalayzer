package stepDefinitions;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.batchModule;

public class deleteBatchValidation {
	private batchModule page;
	private SoftAssert S_Assert = new SoftAssert();
	private LoginPage lpage;

	public deleteBatchValidation() {
		page = new batchModule(DriverFactory.getDriver()); // Get the driver from driverManager
		lpage = new LoginPage(DriverFactory.getDriver());

	}

	@Given("Admin is on the LMS page ")
	public void admin_is_on_the_lms_pages() {
		lpage.getloginUrl();
		lpage.getPageTitle();
	}

	@Given("Admin is on the Batch page")
	public void admin_is_on_the_batch_page() {

		page.batchaddclick();

	}

	@When("Admin clicks the delete Icon on any row in batchpage")
	public void admin_clicks_the_delete_icon_on_any_row_in_batchpage() {

		page.deleteIconclicked();

	}

	@Then("Admin should see the confirm alert box with yes and no button")
	public void admin_should_see_the_confirm_alert_box_with_yes_and_no_button() {

		String bname = page.confirmDeletePopup();
		S_Assert.assertEquals(bname, "Confirm", "AddBatch header mismatch!");
	}

	@Given("Admin is on the batch confirm popup page")
	public void admin_is_on_the_batch_confirm_popup_page() {
		page.landbatchpage();
	}

	@When("Admin clicks on the delete icon and click yes button in batch popup")
	public void admin_clicks_on_the_delete_icon_and_click_yes_button_in_batch_popup() {
		page.deleteIconclicked();
		page.confirmDeletePopup();
		page.ConfirmDeleteYes();

	}

	@Then("Admin should see the successful message and the batch should be deleted")
	public void admin_should_see_the_successful_message_and_the_batch_should_be_deleted() {

		String Success_msg = page.Deletedhandlealert();
		S_Assert.assertEquals(Success_msg, "Successful", " header mismatch!");

	}

	@When("Admin clicks on the delete icon and click no button in batch popup")
	public void admin_clicks_on_the_delete_icon_and_click_no_button_in_batch_popup() {
		page.deleteIconclicked();
		page.confirmDeletePopup();
		page.ConfirmDeleteNo();

	}

	@Then("Admin should see the alert box closed and the batch is not deleted")
	public void admin_should_see_the_alert_box_closed_and_the_batch_is_not_deleted() {

		String pheading = page.batchheading();
		S_Assert.assertEquals(pheading, " Manage Batch", " header mismatch!");
	}

	@When("Admin clicks on the close icon in batch confirm popup")
	public void admin_clicks_on_the_close_icon_in_batch_confirm_popup() {

		page.deleteIconclicked();
		page.confirmDeletePopup();
		page.deleteClose();
	}

	@Then("Admin should see the alert box closed")
	public void admin_should_see_the_alert_box_closed() {
		String pheading = page.batchheading();
		S_Assert.assertEquals(pheading, " Manage Batch", " header mismatch!");

	}

	@When("Admin clicks on the delete icon under the Manage batch header")
	public void admin_clicks_on_the_delete_icon_under_the_manage_batch_header() {
		page.firstcheckboxdeleteIconclicked();
		page.deleteIconclicked();
		page.confirmDeletePopup();
		page.ConfirmDeleteYes();

	}

	@Then("The respective row in the table should be deleted")
	public void the_respective_row_in_the_table_should_be_deleted() {
		String Success_msg = page.Deletedhandlealert();
		S_Assert.assertEquals(Success_msg, "Successful", " header mismatch!");
	}

	@When("Admin clicks on the multipledelete icon under the Manage batch header")
	public void admin_clicks_on_the_multipledelete_icon_under_the_Manage_batch_header() {
    
		page.multiplecheckboxdeleteIconclicked();
		page.multideleteclick();
		page.multiConfirmDeleteYes();
		
	}

}
