package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import selenium.pages.LoginPage;
import selenium.pages.DashboardPage;
import utils.DriverFactory;
import static org.junit.Assert.*;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    
    public LoginSteps() {
        driver = DriverFactory.getDriver();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }
    
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.navigateToLoginPage();
    }
    
    @When("I enter valid username {string}")
    public void iEnterValidUsername(String username) {
        loginPage.enterUsername(username);
    }
    
    @When("I enter valid password {string}")
    public void iEnterValidPassword(String password) {
        loginPage.enterPassword(password);
    }
    
    @When("I enter invalid password {string}")
    public void iEnterInvalidPassword(String password) {
        loginPage.enterPassword(password);
    }
    
    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }
    
    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }
    
    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }
    
    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToTheDashboard() {
        assertTrue("Failed to navigate to dashboard", dashboardPage.isCurrentPage());
    }
    
    @Then("I should see a welcome message with my username")
    public void iShouldSeeAWelcomeMessageWithMyUsername() {
        assertTrue("Welcome message not displayed correctly", 
                  dashboardPage.getWelcomeMessage().contains("Welcome"));
    }
    
    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String errorMessage) {
        assertEquals("Error message not displayed correctly", 
                   errorMessage, loginPage.getErrorMessage());
    }
    
    @Then("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        assertTrue("Not on login page", loginPage.isCurrentPage());
    }
    
    @Then("I should see message {string}")
    public void iShouldSeeMessage(String message) {
        if (loginPage.isCurrentPage()) {
            assertEquals("Message not displayed correctly on login page", 
                       message, loginPage.getErrorMessage());
        } else if (dashboardPage.isCurrentPage()) {
            assertEquals("Message not displayed correctly on dashboard", 
                       message, dashboardPage.getWelcomeMessage());
        } else {
            fail("Not on expected page to verify message");
        }
    }
    
    @And("I should be on page {string}")
    public void iShouldBeOnPage(String pageName) {
        switch (pageName.toLowerCase()) {
            case "login":
                assertTrue("Not on login page", loginPage.isCurrentPage());
                break;
            case "dashboard":
                assertTrue("Not on dashboard page", dashboardPage.isCurrentPage());
                break;
            default:
                fail("Unknown page: " + pageName);
        }
    }
}