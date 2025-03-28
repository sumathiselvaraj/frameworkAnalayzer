package stepDefinitions;

import org.testng.asserts.SoftAssert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.batchModule;
import utils.CommonFunctions;

public class searchBatch {

	private batchModule page;
	private SoftAssert S_Assert = new SoftAssert();
	private LoginPage lpage;
	public CommonFunctions comMethod;


	public searchBatch() {
		page = new batchModule(DriverFactory.getDriver()); // Get the driver from driverManager
		lpage = new LoginPage(DriverFactory.getDriver());
		this.comMethod = new CommonFunctions(DriverFactory.getDriver(), 20);

	}

	// ------------------------Search Batch-------------------------//
	// Search Text Box Validation

	@Given("Admin in search batch page")
	public void admin_in_search_batch_page() {
		lpage.getloginUrl();
		comMethod.loginDetails();
	}

	@When("Admin enters the batch name in the search text box")
	public void admin_enters_the_batch_name_in_the_search_text_box() {
		//page.batchaddclick();
		page.searchtext();
	}

	@Then("Admin should see the filtered batches in the data table")
	public void admin_should_see_the_filtered_batches_in_the_data_table() {
		Boolean result = page.searchtextvalidation();
		S_Assert.assertTrue(result);

	}
}
