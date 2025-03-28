package stepDefinitions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ManageClassPage;
import pageObjects.LoginPage;
import utils.ExcelReader;
import utils.LoggerLoad;

public class ManageClassSteps {

	private ManageClassPage MCPage = new ManageClassPage(DriverFactory.getDriver());
	List<String> expectedSortedList;

	@When("Admin clicks {string} button on the navigation bar")
	public void admin_clicks_button_on_the_navigation_bar(String string) throws InterruptedException {
		Thread.sleep(2000);
		MCPage.Classbutton();
	}


	@Then("The page title should be {string}")
	public void the_page_title_should_be(String expectedTitle) {
		LoggerLoad.info("Start : Validate the Class page Validate Page title");
		String clsTxt = MCPage.getManageClassPageTitle();
		Assert.assertTrue(clsTxt.equalsIgnoreCase(expectedTitle));
	}

	@Then("The class grid should display the following headers:")
	public void the_class_grid_should_display_the_following_headers(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedHeaders = dataTable.row(0);
		LoggerLoad.info("Start : Validate the Class page Validate Page title");
		Assert.assertTrue(MCPage.verifyGridHeaders(expectedHeaders), "Headers do not match");
	}

	@Then("Admin should see the Search Bar in Manage class page")
	public void admin_should_see_the_search_bar_in_manage_class_page() throws InterruptedException {
		boolean srchbox = MCPage.srchBox();
		System.out.println("Search box is displayed : " + srchbox);
		Assert.assertEquals(true, srchbox);
		String srchtxt = MCPage.srchtxt();
		System.out.println("Search box placeholder text is " + srchtxt);
		Assert.assertTrue(srchtxt.contains("Search"));
		LoggerLoad.info("End : Validate the Class page  Search box input is visible");
	}

	@Then("Admin should see the {string} and enabled pagination controls under the data table")
	public void admin_should_see_the_and_enabled_pagination_controls_under_the_data_table(String expectedText) {
		LoggerLoad.info("Start : Validate the Program page Validate Pagination text is displayed");
		boolean Pgtntxt = MCPage.getPaginationtxt();
		System.out.println("Pagination text is displayed : " + Pgtntxt);
		Assert.assertEquals(true, Pgtntxt);
		LoggerLoad.info("End : Validate the Program page Validate Pagination text is displayed");
	}

	@Then("Admin should see Total no of classes in below of the data table.")
	public void admin_should_see_total_no_of_classes_in_below_of_the_data_table() {
		LoggerLoad.info("Start : Validate the Program page Validate datatable footer text is displayed");
		boolean Fttxt = MCPage.getFootertxt();
		System.out.println("Footer text is displayed : " + Fttxt);
		Assert.assertEquals(true, Fttxt);
		LoggerLoad.info("End : Validate the Program page Validate datatable footer text is displayed");
	}
	@When("User clicks on the {string} header to sort")
	public void user_clicks_on_the_header_to_sort(String column)  throws InterruptedException {
		List<String> originalList = MCPage.getAllDataFromAllPages(column);
	    expectedSortedList = new ArrayList<>(originalList);	    
	    Collections.sort(expectedSortedList);
	    Thread.sleep(10000);
		MCPage.clickColumnHeaderToSort(column);
	}

	@Then("The grid should display sorted results based on {string}")
	public void the_grid_should_display_sorted_results_based_on(String column) throws InterruptedException {
		Thread.sleep(10000);
		 List<String> actualSortedList = MCPage.getAllDataFromAllPages(column);
	    //  Compare the actual sorted list with the expected sorted list
	    Assert.assertEquals(actualSortedList, expectedSortedList, "The column data is not sorted correctly: " + column);
	}




}
