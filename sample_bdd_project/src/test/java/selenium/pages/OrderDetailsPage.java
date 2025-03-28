package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the order details page
 */
public class OrderDetailsPage extends BasePage {
    // Page elements
    @FindBy(id = "order-number")
    private WebElement orderNumberLabel;
    
    @FindBy(id = "order-date")
    private WebElement orderDateLabel;
    
    @FindBy(id = "order-status")
    private WebElement orderStatusLabel;
    
    @FindBy(className = "order-item")
    private List<WebElement> orderItems;
    
    @FindBy(id = "subtotal")
    private WebElement subtotalLabel;
    
    @FindBy(id = "shipping-cost")
    private WebElement shippingCostLabel;
    
    @FindBy(id = "tax")
    private WebElement taxLabel;
    
    @FindBy(id = "order-total")
    private WebElement orderTotalLabel;
    
    @FindBy(id = "shipping-address")
    private WebElement shippingAddressLabel;
    
    @FindBy(id = "payment-method")
    private WebElement paymentMethodLabel;
    
    @FindBy(id = "cancel-order-button")
    private WebElement cancelOrderButton;
    
    @FindBy(id = "track-order-button")
    private WebElement trackOrderButton;
    
    @FindBy(id = "return-to-orders-button")
    private WebElement returnToOrdersButton;
    
    /**
     * Constructor for OrderDetailsPage
     * @param driver WebDriver instance
     */
    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get order number
     * @return Order number text
     */
    public String getOrderNumber() {
        return waitForElementToBeVisible(orderNumberLabel).getText();
    }
    
    /**
     * Get order date
     * @return Order date text
     */
    public String getOrderDate() {
        return waitForElementToBeVisible(orderDateLabel).getText();
    }
    
    /**
     * Get order status
     * @return Order status text
     */
    public String getOrderStatus() {
        return waitForElementToBeVisible(orderStatusLabel).getText();
    }
    
    /**
     * Get order item count
     * @return Number of order items
     */
    public int getOrderItemCount() {
        return waitForElementsToBeVisible(orderItems).size();
    }
    
    /**
     * Get subtotal
     * @return Subtotal text
     */
    public String getSubtotal() {
        return waitForElementToBeVisible(subtotalLabel).getText();
    }
    
    /**
     * Get shipping cost
     * @return Shipping cost text
     */
    public String getShippingCost() {
        return waitForElementToBeVisible(shippingCostLabel).getText();
    }
    
    /**
     * Get tax
     * @return Tax text
     */
    public String getTax() {
        return waitForElementToBeVisible(taxLabel).getText();
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
     * Click cancel order button
     * @return CancelOrderPage instance
     */
    public CancelOrderPage clickCancelOrder() {
        waitForElementToBeClickable(cancelOrderButton).click();
        return new CancelOrderPage(driver);
    }
    
    /**
     * Click track order button
     * @return TrackOrderPage instance
     */
    public TrackOrderPage clickTrackOrder() {
        waitForElementToBeClickable(trackOrderButton).click();
        return new TrackOrderPage(driver);
    }
    
    /**
     * Click return to orders button
     * @return OrdersHistoryPage instance
     */
    public OrdersHistoryPage clickReturnToOrders() {
        waitForElementToBeClickable(returnToOrdersButton).click();
        return new OrdersHistoryPage(driver);
    }
}