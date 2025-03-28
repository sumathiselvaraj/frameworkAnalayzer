package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import pages.LoginPage;
import pages.DashboardPage;

public class LoginSteps {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage.navigateToLoginPage();
    }
    
    @When("I enter valid username {string} and password {string}")
    public void i_enter_valid_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    
    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }
    
    @And("I click the login button")
    public void i_click_the_login_button() {
        loginPage.clickLoginButton();
    }
    
    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        Assert.assertTrue("User is not on dashboard page", dashboardPage.isDashboardDisplayed());
    }
    
    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        Assert.assertEquals(errorMessage, loginPage.getErrorMessage());
    }
}