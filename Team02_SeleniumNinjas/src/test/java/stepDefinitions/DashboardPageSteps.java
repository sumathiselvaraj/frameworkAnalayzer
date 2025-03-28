package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utils.CommonFunctions;
import utils.ExcelReader;

public class DashboardPageSteps {
	
	private static WebDriver driver;
	private LoginPage loginpg = new LoginPage(DriverFactory.getDriver());
	private CommonFunctions commonfunc = new CommonFunctions(DriverFactory.getDriver(), 0);
	private DashboardPage dashboardpg = new DashboardPage(DriverFactory.getDriver());
	@Given("Admin is in landing page")
	public void admin_is_in_landing_page() {
		loginpg.getloginUrl();
	}

	@When("Admin logged into the LMS portal")
	public void admin_logged_into_the_lms_portal() {
		loginpg.getloginUrl();
		commonfunc.loginDetails();
	}

	@Then("Admin should be navigating to home page")
	public void admin_should_be_navigating_to_home_page() {
	    
	}

	@Then("Admin should see LMS-Learning Management Systemm as header")
	public void admin_should_see_lms_learning_management_systemm_as_header() {
		Assert.assertTrue(dashboardpg.getManageprogrameBtnLabelText().trim().contains("LMS - Learning Management System"),
				"LMS - Learning Management System' page is  displayed");
		System.out.println("'Dashboard - Manage Program' page is displayed");
	}

	@Then("Admin should see {string}  as title")
	public void admin_should_see_as_title(String expectedtitle) {
		String actualTitle = loginpg.getPageTitle();
		Assert.assertEquals(expectedtitle, actualTitle.trim());
	}

// Validation 
	
	@Then("LMS title should be on the top left corner of page")
	public void lms_title_should_be_on_the_top_left_corner_of_page() {
		
		dashboardpg.text_alignment_lms();
	}

	@Then("Admin should see correct spelling in navigation bar text")
	public void admin_should_see_correct_spelling_in_navigation_bar_text() {
		Assert.assertTrue(dashboardpg.lms_spelling());
		Assert.assertTrue(dashboardpg.pro_spelling());
		Assert.assertTrue(dashboardpg.batch_spelling());
		Assert.assertTrue(dashboardpg.clas_spelling());
		
	}

	@Then("Admin should see correct spelling and space in LMS title")
	public void admin_should_see_correct_spelling_and_space_in_lms_title() {
		Assert.assertTrue(dashboardpg.get_title());
	}

	@Then("Admin should see the navigation bar text on the top right side")
	public void admin_should_see_the_navigation_bar_text_on_the_top_right_side() {
		dashboardpg.text_alignment_logout();
	}

	@Then("Admin should see Home in the 1st place")
	public void admin_should_see_home_in_the_1st_place() throws InterruptedException {
		Thread.sleep(2000);
		System.out.println(dashboardpg.ListToFind_place());
		System.out.println(dashboardpg.ListToFind_place().get(0));
		Assert.assertEquals(dashboardpg.ListToFind_place().get(0),"Home");
	}

	@Then("Admin should see program in the 2nd place")
	public void admin_should_see_program_in_the_2nd_place(){
		System.out.println(dashboardpg.ListToFind_place());
		System.out.println(dashboardpg.ListToFind_place().get(0));
		Assert.assertEquals(dashboardpg.ListToFind_place().get(1),"Program");
	}

	@Then("Admin should see batch in the 3rd place")
	public void admin_should_see_batch_in_the_3rd_place() {
		System.out.println(dashboardpg.ListToFind_place());
		System.out.println(dashboardpg.ListToFind_place().get(0));
		Assert.assertEquals(dashboardpg.ListToFind_place().get(2),"Batch");
	}

	@Then("Admin should see class in the 4th place")
	public void admin_should_see_class_in_the_4th_place() {
		System.out.println(dashboardpg.ListToFind_place());
		//System.out.println(dashboardpg.ListToFind_place().get(2));
		Assert.assertEquals(dashboardpg.ListToFind_place().get(3),"Class");
	}

	@Then("Admin should see logout in the 5th place")
	public void admin_should_see_logout_in_the_5th_place() {
		System.out.println(dashboardpg.ListToFind_place());
		//System.out.println(dashboardpg.ListToFind_place().get(3));
		Assert.assertEquals(dashboardpg.ListToFind_place().get(4),"Logout");
	}



}
