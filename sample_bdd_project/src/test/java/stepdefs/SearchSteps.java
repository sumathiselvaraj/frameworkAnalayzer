package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import selenium.pages.HomePage;
import selenium.pages.SearchResultsPage;
import utils.DriverFactory;
import static org.junit.Assert.*;

public class SearchSteps {
    private WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    
    public SearchSteps() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }
    
    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.navigateToHomePage();
    }
    
    @When("I search for {string}")
    public void iSearchFor(String searchTerm) {
        homePage.search(searchTerm);
    }
    
    @Then("I should see search results")
    public void iShouldSeeSearchResults() {
        assertTrue("Search results not displayed", searchResultsPage.hasResults());
    }
    
    @And("the search results should contain {string} in the title")
    public void theSearchResultsShouldContainInTheTitle(String searchTerm) {
        assertTrue("Search term not found in results", 
                  searchResultsPage.resultsTitleContains(searchTerm));
    }
    
    @Then("I should see a {string} message")
    public void iShouldSeeAMessage(String message) {
        assertEquals("Expected message not displayed", 
                   message, searchResultsPage.getMessageText());
    }
    
    @When("I filter by category {string}")
    public void iFilterByCategory(String category) {
        searchResultsPage.filterByCategory(category);
    }
    
    @Then("I should see results filtered by {string}")
    public void iShouldSeeResultsFilteredBy(String category) {
        assertTrue("Results not filtered by category",
                  searchResultsPage.isFilteredByCategory(category));
    }
}