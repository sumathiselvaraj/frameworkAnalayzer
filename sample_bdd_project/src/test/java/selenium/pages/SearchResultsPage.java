package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the search results page
 */
public class SearchResultsPage extends BasePage {
    // Page elements
    @FindBy(className = "search-result-item")
    private List<WebElement> searchResultItems;
    
    @FindBy(id = "search-count")
    private WebElement searchCountLabel;
    
    @FindBy(id = "sort-dropdown")
    private WebElement sortDropdown;
    
    @FindBy(id = "filter-button")
    private WebElement filterButton;
    
    @FindBy(className = "pagination-link")
    private List<WebElement> paginationLinks;
    
    @FindBy(className = "no-results-message")
    private WebElement noResultsMessage;
    
    /**
     * Constructor for SearchResultsPage
     * @param driver WebDriver instance
     */
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get search result count
     * @return Number of search results
     */
    public int getSearchResultCount() {
        return waitForElementsToBeVisible(searchResultItems).size();
    }
    
    /**
     * Get search count text
     * @return Search count text
     */
    public String getSearchCountText() {
        return waitForElementToBeVisible(searchCountLabel).getText();
    }
    
    /**
     * Click on search result by index
     * @param index Index of the search result to click (0-based)
     * @return ItemDetailPage instance
     */
    public ItemDetailPage clickSearchResult(int index) {
        waitForElementsToBeVisible(searchResultItems);
        if (index < searchResultItems.size()) {
            scrollToElement(searchResultItems.get(index));
            waitForElementToBeClickable(searchResultItems.get(index)).click();
            return new ItemDetailPage(driver);
        }
        throw new IndexOutOfBoundsException("Search result index out of bounds");
    }
    
    /**
     * Click sort dropdown
     * @return SearchResultsPage instance for method chaining
     */
    public SearchResultsPage clickSortDropdown() {
        waitForElementToBeClickable(sortDropdown).click();
        return this;
    }
    
    /**
     * Click filter button
     * @return FilterPage instance
     */
    public FilterPage clickFilterButton() {
        waitForElementToBeClickable(filterButton).click();
        return new FilterPage(driver);
    }
    
    /**
     * Navigate to page by page number
     * @param pageNumber Page number to navigate to (1-based)
     * @return SearchResultsPage instance for method chaining
     */
    public SearchResultsPage navigateToPage(int pageNumber) {
        waitForElementsToBeVisible(paginationLinks);
        for (WebElement link : paginationLinks) {
            if (link.getText().trim().equals(String.valueOf(pageNumber))) {
                waitForElementToBeClickable(link).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Page number not found in pagination");
    }
    
    /**
     * Check if no results message is displayed
     * @return true if no results message is displayed, false otherwise
     */
    public boolean isNoResultsMessageDisplayed() {
        return isElementDisplayed(noResultsMessage);
    }
}