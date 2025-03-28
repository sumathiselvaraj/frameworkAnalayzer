package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the filter page/dialog
 */
public class FilterPage extends BasePage {
    // Page elements
    @FindBy(className = "filter-category")
    private List<WebElement> filterCategories;
    
    @FindBy(className = "filter-option")
    private List<WebElement> filterOptions;
    
    @FindBy(id = "price-range-min")
    private WebElement priceRangeMin;
    
    @FindBy(id = "price-range-max")
    private WebElement priceRangeMax;
    
    @FindBy(id = "apply-filters-button")
    private WebElement applyFiltersButton;
    
    @FindBy(id = "clear-filters-button")
    private WebElement clearFiltersButton;
    
    @FindBy(id = "close-filter-button")
    private WebElement closeFilterButton;
    
    /**
     * Constructor for FilterPage
     * @param driver WebDriver instance
     */
    public FilterPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Select filter category by name
     * @param categoryName Name of the category to select
     * @return FilterPage instance for method chaining
     */
    public FilterPage selectFilterCategory(String categoryName) {
        waitForElementsToBeVisible(filterCategories);
        for (WebElement category : filterCategories) {
            if (category.getText().equalsIgnoreCase(categoryName)) {
                waitForElementToBeClickable(category).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Filter category not found: " + categoryName);
    }
    
    /**
     * Select filter option by name
     * @param optionName Name of the option to select
     * @return FilterPage instance for method chaining
     */
    public FilterPage selectFilterOption(String optionName) {
        waitForElementsToBeVisible(filterOptions);
        for (WebElement option : filterOptions) {
            if (option.getText().equalsIgnoreCase(optionName)) {
                waitForElementToBeClickable(option).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Filter option not found: " + optionName);
    }
    
    /**
     * Set price range
     * @param min Minimum price
     * @param max Maximum price
     * @return FilterPage instance for method chaining
     */
    public FilterPage setPriceRange(int min, int max) {
        clearAndType(priceRangeMin, String.valueOf(min));
        clearAndType(priceRangeMax, String.valueOf(max));
        return this;
    }
    
    /**
     * Click apply filters button
     * @return SearchResultsPage instance
     */
    public SearchResultsPage clickApplyFilters() {
        waitForElementToBeClickable(applyFiltersButton).click();
        return new SearchResultsPage(driver);
    }
    
    /**
     * Click clear filters button
     * @return FilterPage instance for method chaining
     */
    public FilterPage clickClearFilters() {
        waitForElementToBeClickable(clearFiltersButton).click();
        return this;
    }
    
    /**
     * Click close filter button
     * @return SearchResultsPage instance
     */
    public SearchResultsPage clickCloseFilter() {
        waitForElementToBeClickable(closeFilterButton).click();
        return new SearchResultsPage(driver);
    }
}