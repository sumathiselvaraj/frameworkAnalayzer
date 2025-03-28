package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the track order page
 */
public class TrackOrderPage extends BasePage {
    // Page elements
    @FindBy(id = "order-number")
    private WebElement orderNumberLabel;
    
    @FindBy(id = "order-status")
    private WebElement orderStatusLabel;
    
    @FindBy(id = "estimated-delivery")
    private WebElement estimatedDeliveryLabel;
    
    @FindBy(className = "tracking-milestone")
    private List<WebElement> trackingMilestones;
    
    @FindBy(id = "carrier-name")
    private WebElement carrierNameLabel;
    
    @FindBy(id = "tracking-number")
    private WebElement trackingNumberLabel;
    
    @FindBy(id = "view-carrier-details")
    private WebElement viewCarrierDetailsButton;
    
    @FindBy(id = "back-to-order")
    private WebElement backToOrderButton;
    
    /**
     * Constructor for TrackOrderPage
     * @param driver WebDriver instance
     */
    public TrackOrderPage(WebDriver driver) {
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
     * Get order status
     * @return Order status text
     */
    public String getOrderStatus() {
        return waitForElementToBeVisible(orderStatusLabel).getText();
    }
    
    /**
     * Get estimated delivery
     * @return Estimated delivery text
     */
    public String getEstimatedDelivery() {
        return waitForElementToBeVisible(estimatedDeliveryLabel).getText();
    }
    
    /**
     * Get tracking milestone count
     * @return Number of tracking milestones
     */
    public int getTrackingMilestoneCount() {
        return waitForElementsToBeVisible(trackingMilestones).size();
    }
    
    /**
     * Get tracking milestone texts
     * @return List of tracking milestone texts
     */
    public List<String> getTrackingMilestoneTexts() {
        return waitForElementsToBeVisible(trackingMilestones).stream()
                .map(WebElement::getText)
                .toList();
    }
    
    /**
     * Get carrier name
     * @return Carrier name text
     */
    public String getCarrierName() {
        return waitForElementToBeVisible(carrierNameLabel).getText();
    }
    
    /**
     * Get tracking number
     * @return Tracking number text
     */
    public String getTrackingNumber() {
        return waitForElementToBeVisible(trackingNumberLabel).getText();
    }
    
    /**
     * Click view carrier details button
     * Opens carrier tracking page in new window/tab
     * @return TrackOrderPage instance
     */
    public TrackOrderPage clickViewCarrierDetails() {
        waitForElementToBeClickable(viewCarrierDetailsButton).click();
        // This usually opens a new window/tab, so we stay on current page
        return this;
    }
    
    /**
     * Click back to order button
     * @return OrderDetailsPage instance
     */
    public OrderDetailsPage clickBackToOrder() {
        waitForElementToBeClickable(backToOrderButton).click();
        return new OrderDetailsPage(driver);
    }
}