package stepDefinitions;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import utils.CommonFunctions;

public class LogoutPageSteps {
	
	private LoginPage loginpg = new LoginPage(DriverFactory.getDriver());
	private LogoutPage logoutpg = new LogoutPage(DriverFactory.getDriver());
	private CommonFunctions commonfunc = new CommonFunctions(DriverFactory.getDriver(), 0);
	
	@Given("Admin is in dashboard page after login.")
	public void admin_is_in_dashboard_page_after_login() throws InterruptedException {
		loginpg.getloginUrl();
		commonfunc.loginDetails();
		Thread.sleep(3000);
	}

	@When("Admin clicks on the logout in the menu bar")
	public void admin_clicks_on_the_logout_in_the_menu_bar() {
		logoutpg.click_logout();
	}

	@Then("Admin should be redirected to login page")
	public void admin_should_be_redirected_to_login_page() {
		System.out.println("after logout:"+logoutpg.get_current_url());
	}

	@Given("Admin is in login page after logout")
	public void admin_is_in_login_page_after_logout() throws InterruptedException {
		loginpg.getloginUrl();
		commonfunc.loginDetails();
		Thread.sleep(3000);
		logoutpg.click_logout();
	}

	@When("Admin clicks  browser back button")
	public void admin_clicks_browser_back_button() throws InterruptedException {
		logoutpg.navigate_driver_back();
		Thread.sleep(3000);
		System.out.println(logoutpg.get_current_url());
	}

	@Then("Admin should receive error message")
	public void admin_should_receive_error_message() {
		try {
			DriverFactory.getDriver().navigate().back();
			throw new AssertionError("Test failed, Expected exception error");
	}catch (Exception e) {
		System.out.println("Test passed: Expected exception error");
	} 
	}


}
