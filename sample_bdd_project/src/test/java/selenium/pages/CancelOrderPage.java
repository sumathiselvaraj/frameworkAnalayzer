package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the cancel order page
 */
public class CancelOrderPage extends BasePage {
    // Page elements
    @FindBy(id = "order-number")
    private WebElement orderNumberLabel;
    
    @FindBy(id = "cancel-reason-dropdown")
    private WebElement cancelReasonDropdown;
    
    @FindBy(className = "cancel-reason-option")
    private List<WebElement> cancelReasonOptions;
    
    @FindBy(id = "additional-comments")
    private WebElement additionalCommentsField;
    
    @FindBy(id = "confirm-cancel-button")
    private WebElement confirmCancelButton;
    
    @FindBy(id = "back-button")
    private WebElement backButton;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    /**
     * Constructor for CancelOrderPage
     * @param driver WebDriver instance
     */
    public CancelOrderPage(WebDriver driver) {
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
     * Click cancel reason dropdown
     * @return CancelOrderPage instance for method chaining
     */
    public CancelOrderPage clickCancelReasonDropdown() {
        waitForElementToBeClickable(cancelReasonDropdown).click();
        return this;
    }
    
    /**
     * Select cancel reason by index
     * @param index Index of the cancel reason to select (0-based)
     * @return CancelOrderPage instance for method chaining
     */
    public CancelOrderPage selectCancelReason(int index) {
        clickCancelReasonDropdown();
        waitForElementsToBeVisible(cancelReasonOptions);
        if (index < cancelReasonOptions.size()) {
            waitForElementToBeClickable(cancelReasonOptions.get(index)).click();
            return this;
        }
        throw new IndexOutOfBoundsException("Cancel reason index out of bounds");
    }
    
    /**
     * Enter additional comments
     * @param comments Comments to enter
     * @return CancelOrderPage instance for method chaining
     */
    public CancelOrderPage enterAdditionalComments(String comments) {
        clearAndType(additionalCommentsField, comments);
        return this;
    }
    
    /**
     * Click confirm cancel button
     * @return OrderCancellationConfirmationPage instance if cancellation successful
     */
    public OrderCancellationConfirmationPage clickConfirmCancel() {
        waitForElementToBeClickable(confirmCancelButton).click();
        if (!isErrorMessageDisplayed()) {
            return new OrderCancellationConfirmationPage(driver);
        }
        return null;
    }
    
    /**
     * Click back button
     * @return OrderDetailsPage instance
     */
    public OrderDetailsPage clickBack() {
        waitForElementToBeClickable(backButton).click();
        return new OrderDetailsPage(driver);
    }
    
    /**
     * Check if success message is displayed
     * @return true if success message is displayed, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }
    
    /**
     * Get success message text
     * @return Success message text
     */
    public String getSuccessMessage() {
        return waitForElementToBeVisible(successMessage).getText();
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
    
    /**
     * Get error message text
     * @return Error message text
     */
    public String getErrorMessage() {
        return waitForElementToBeVisible(errorMessage).getText();
    }
    
    /**
     * Complete order cancellation
     * @param reasonIndex Index of the cancel reason to select
     * @param comments Additional comments
     * @return OrderCancellationConfirmationPage instance if cancellation successful
     */
    public OrderCancellationConfirmationPage completeOrderCancellation(int reasonIndex, String comments) {
        selectCancelReason(reasonIndex);
        enterAdditionalComments(comments);
        return clickConfirmCancel();
    }
}

/**
 * Page object for the order cancellation confirmation page
 */
class OrderCancellationConfirmationPage extends BasePage {
    // Page elements
    @FindBy(id = "confirmation-message")
    private WebElement confirmationMessage;
    
    @FindBy(id = "order-number")
    private WebElement orderNumberLabel;
    
    @FindBy(id = "cancellation-number")
    private WebElement cancellationNumberLabel;
    
    @FindBy(id = "return-to-orders-button")
    private WebElement returnToOrdersButton;
    
    /**
     * Constructor for OrderCancellationConfirmationPage
     * @param driver WebDriver instance
     */
    public OrderCancellationConfirmationPage(WebDriver driver) {
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
     * Get cancellation number
     * @return Cancellation number text
     */
    public String getCancellationNumber() {
        return waitForElementToBeVisible(cancellationNumberLabel).getText();
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