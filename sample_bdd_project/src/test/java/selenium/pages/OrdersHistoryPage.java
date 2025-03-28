package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the orders history page
 */
public class OrdersHistoryPage extends BasePage {
    // Page elements
    @FindBy(className = "order-item")
    private List<WebElement> orderItems;
    
    @FindBy(id = "filter-dropdown")
    private WebElement filterDropdown;
    
    @FindBy(id = "sort-dropdown")
    private WebElement sortDropdown;
    
    @FindBy(id = "search-orders")
    private WebElement searchOrdersField;
    
    @FindBy(id = "search-button")
    private WebElement searchButton;
    
    @FindBy(className = "pagination-link")
    private List<WebElement> paginationLinks;
    
    @FindBy(className = "no-orders-message")
    private WebElement noOrdersMessage;
    
    @FindBy(className = "view-details-button")
    private List<WebElement> viewDetailsButtons;
    
    /**
     * Constructor for OrdersHistoryPage
     * @param driver WebDriver instance
     */
    public OrdersHistoryPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get order count
     * @return Number of orders
     */
    public int getOrderCount() {
        return waitForElementsToBeVisible(orderItems).size();
    }
    
    /**
     * Click filter dropdown
     * @return OrdersHistoryPage instance for method chaining
     */
    public OrdersHistoryPage clickFilterDropdown() {
        waitForElementToBeClickable(filterDropdown).click();
        return this;
    }
    
    /**
     * Click sort dropdown
     * @return OrdersHistoryPage instance for method chaining
     */
    public OrdersHistoryPage clickSortDropdown() {
        waitForElementToBeClickable(sortDropdown).click();
        return this;
    }
    
    /**
     * Search orders
     * @param searchTerm Search term to enter
     * @return OrdersHistoryPage instance for method chaining
     */
    public OrdersHistoryPage searchOrders(String searchTerm) {
        clearAndType(searchOrdersField, searchTerm);
        waitForElementToBeClickable(searchButton).click();
        return this;
    }
    
    /**
     * Navigate to page by page number
     * @param pageNumber Page number to navigate to (1-based)
     * @return OrdersHistoryPage instance for method chaining
     */
    public OrdersHistoryPage navigateToPage(int pageNumber) {
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
     * Check if no orders message is displayed
     * @return true if no orders message is displayed, false otherwise
     */
    public boolean isNoOrdersMessageDisplayed() {
        return isElementDisplayed(noOrdersMessage);
    }
    
    /**
     * Get no orders message text
     * @return No orders message text
     */
    public String getNoOrdersMessage() {
        return waitForElementToBeVisible(noOrdersMessage).getText();
    }
    
    /**
     * View order details by index
     * @param index Index of the order to view details (0-based)
     * @return OrderDetailsPage instance
     */
    public OrderDetailsPage viewOrderDetails(int index) {
        waitForElementsToBeVisible(viewDetailsButtons);
        if (index < viewDetailsButtons.size()) {
            scrollToElement(viewDetailsButtons.get(index));
            waitForElementToBeClickable(viewDetailsButtons.get(index)).click();
            return new OrderDetailsPage(driver);
        }
        throw new IndexOutOfBoundsException("View details button index out of bounds");
    }
}