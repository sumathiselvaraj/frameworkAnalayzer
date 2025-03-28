package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the category page
 */
public class CategoryPage extends BasePage {
    // Page elements
    @FindBy(id = "category-title")
    private WebElement categoryTitle;
    
    @FindBy(id = "category-description")
    private WebElement categoryDescription;
    
    @FindBy(className = "product-item")
    private List<WebElement> productItems;
    
    @FindBy(id = "sort-dropdown")
    private WebElement sortDropdown;
    
    @FindBy(id = "filter-button")
    private WebElement filterButton;
    
    @FindBy(className = "pagination-link")
    private List<WebElement> paginationLinks;
    
    @FindBy(id = "search-in-category")
    private WebElement searchInCategoryInput;
    
    @FindBy(id = "search-in-category-button")
    private WebElement searchInCategoryButton;
    
    /**
     * Constructor for CategoryPage
     * @param driver WebDriver instance
     */
    public CategoryPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get category title
     * @return Category title text
     */
    public String getCategoryTitle() {
        return waitForElementToBeVisible(categoryTitle).getText();
    }
    
    /**
     * Get category description
     * @return Category description text
     */
    public String getCategoryDescription() {
        return waitForElementToBeVisible(categoryDescription).getText();
    }
    
    /**
     * Get product count
     * @return Number of products in the category
     */
    public int getProductCount() {
        return waitForElementsToBeVisible(productItems).size();
    }
    
    /**
     * Click on product by index
     * @param index Index of the product to click (0-based)
     * @return ItemDetailPage instance
     */
    public ItemDetailPage clickProduct(int index) {
        waitForElementsToBeVisible(productItems);
        if (index < productItems.size()) {
            scrollToElement(productItems.get(index));
            waitForElementToBeClickable(productItems.get(index)).click();
            return new ItemDetailPage(driver);
        }
        throw new IndexOutOfBoundsException("Product index out of bounds");
    }
    
    /**
     * Click sort dropdown
     * @return CategoryPage instance for method chaining
     */
    public CategoryPage clickSortDropdown() {
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
     * @return CategoryPage instance for method chaining
     */
    public CategoryPage navigateToPage(int pageNumber) {
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
     * Search within category
     * @param searchTerm Search term to enter
     * @return CategoryPage instance with filtered results
     */
    public CategoryPage searchInCategory(String searchTerm) {
        clearAndType(searchInCategoryInput, searchTerm);
        waitForElementToBeClickable(searchInCategoryButton).click();
        return this;
    }
}