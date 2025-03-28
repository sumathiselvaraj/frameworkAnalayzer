package stepDefinitions;

import org.testng.asserts.SoftAssert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.batchModule;

public class addNewBatchUI {

	private batchModule page;
	private SoftAssert S_Assert = new SoftAssert();
	private LoginPage lpage;

	public addNewBatchUI() {
		page = new batchModule(DriverFactory.getDriver()); // Get the driver from driverManager
		lpage = new LoginPage(DriverFactory.getDriver());

	}
	
	@Given ("Admin in Batch Home page")
	public void Admin_in_Batch_Home_page() {
		
	}

	@When("Admin clicks {string} on the navigation bar")
	public void admin_clicks_on_batchthe_navigation_bar(String name) {
		 page.addbatchUI();
		System.out.println(name);

	}

	@Then("Admin should see sub menu in menu bar as {string}")
	public void admin_should_see_sub_menu_in_menu_bar_as(String name) {
		S_Assert.assertEquals(name, "Add New Batch", "Add batch mismatch!");
	}

	@When("Admin clicks on {string} under the {string} menu bar")
	public void admin_clicks_on_under_the_menu_bar(String string, String string2) {

		page.addbatchpopup();
	}

	@Then("Admin should see the {string} pop up window")
	public void admin_should_see_the_pop_up_window(String bname) {
		S_Assert.assertEquals(bname, "Batch Details", "AddBatch header mismatch!");

	}
}
