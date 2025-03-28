package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the order confirmation page
 */
public class OrderConfirmationPage extends BasePage {
    // Page elements
    @FindBy(id = "order-confirmation-message")
    private WebElement confirmationMessage;
    
    @FindBy(id = "order-number")
    private WebElement orderNumberLabel;
    
    @FindBy(className = "order-item")
    private List<WebElement> orderItems;
    
    @FindBy(id = "order-total")
    private WebElement orderTotalLabel;
    
    @FindBy(id = "shipping-address")
    private WebElement shippingAddressLabel;
    
    @FindBy(id = "payment-method")
    private WebElement paymentMethodLabel;
    
    @FindBy(id = "continue-shopping-button")
    private WebElement continueShoppingButton;
    
    @FindBy(id = "view-order-details-button")
    private WebElement viewOrderDetailsButton;
    
    /**
     * Constructor for OrderConfirmationPage
     * @param driver WebDriver instance
     */
    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get confirmation message
     * @return Confirmation message text
     */
    public String getConfirmationMessage() {
        return waitForElementToBeVisible(confirmationMessage).getText();
    }
    
    /**
     * Get order number
     * @return Order number text
     */
    public String getOrderNumber() {
        return waitForElementToBeVisible(orderNumberLabel).getText();
    }
    
    /**
     * Get order item count
     * @return Number of order items
     */
    public int getOrderItemCount() {
        return waitForElementsToBeVisible(orderItems).size();
    }
    
    /**
     * Get order total
     * @return Order total text
     */
    public String getOrderTotal() {
        return waitForElementToBeVisible(orderTotalLabel).getText();
    }
    
    /**
     * Get shipping address
     * @return Shipping address text
     */
    public String getShippingAddress() {
        return waitForElementToBeVisible(shippingAddressLabel).getText();
    }
    
    /**
     * Get payment method
     * @return Payment method text
     */
    public String getPaymentMethod() {
        return waitForElementToBeVisible(paymentMethodLabel).getText();
    }
    
    /**
     * Click continue shopping button
     * @return HomePage instance
     */
    public HomePage clickContinueShopping() {
        waitForElementToBeClickable(continueShoppingButton).click();
        return new HomePage(driver);
    }
    
    /**
     * Click view order details button
     * @return OrderDetailsPage instance
     */
    public OrderDetailsPage clickViewOrderDetails() {
        waitForElementToBeClickable(viewOrderDetailsButton).click();
        return new OrderDetailsPage(driver);
    }
    
    /**
     * Check if order confirmation page is loaded
     * @return true if order confirmation page is loaded, false otherwise
     */
    public boolean isOrderConfirmationPageLoaded() {
        return isElementDisplayed(confirmationMessage) && isElementDisplayed(orderNumberLabel);
    }
}