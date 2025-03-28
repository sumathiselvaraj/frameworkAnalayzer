package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchResultsPage extends BasePage {
    // Locators
    private By searchResults = By.className("search-results");
    private By resultItems = By.className("product-item");
    private By noResultsMessage = By.className("no-results-message");
    private By resultTitle = By.className("result-title");
    private By categoryFilter = By.id("categoryFilter");
    private By activeFilter = By.className("active-filter");
    
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
    
    public boolean hasResults() {
        try {
            return !driver.findElements(resultItems).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getMessageText() {
        if (isElementDisplayed(noResultsMessage)) {
            return getText(noResultsMessage);
        }
        return "";
    }
    
    public boolean resultsTitleContains(String searchTerm) {
        List<WebElement> titles = driver.findElements(resultTitle);
        
        for (WebElement title : titles) {
            if (title.getText().toLowerCase().contains(searchTerm.toLowerCase())) {
                return true;
            }
        }
        
        return false;
    }
    
    public void filterByCategory(String category) {
        // Finds the category filter dropdown
        click(categoryFilter);
        
        // Find the category option by text
        By categoryOption = By.xpath("//option[contains(text(),'" + category + "')]");
        waitForElementVisible(categoryOption);
        click(categoryOption);
    }
    
    public boolean isFilteredByCategory(String category) {
        if (!isElementDisplayed(activeFilter)) {
            return false;
        }
        
        return getText(activeFilter).contains(category);
    }
}