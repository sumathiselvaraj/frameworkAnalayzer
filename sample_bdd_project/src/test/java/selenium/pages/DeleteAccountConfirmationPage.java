package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the delete account confirmation page
 */
public class DeleteAccountConfirmationPage extends BasePage {
    // Page elements
    @FindBy(id = "confirmation-message")
    private WebElement confirmationMessage;
    
    @FindBy(id = "confirmation-password")
    private WebElement confirmationPasswordField;
    
    @FindBy(id = "confirm-delete-button")
    private WebElement confirmDeleteButton;
    
    @FindBy(id = "cancel-button")
    private WebElement cancelButton;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    /**
     * Constructor for DeleteAccountConfirmationPage
     * @param driver WebDriver instance
     */
    public DeleteAccountConfirmationPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get confirmation message text
     * @return Confirmation message text
     */
    public String getConfirmationMessage() {
        return waitForElementToBeVisible(confirmationMessage).getText();
    }
    
    /**
     * Enter confirmation password
     * @param password Password to enter
     * @return DeleteAccountConfirmationPage instance for method chaining
     */
    public DeleteAccountConfirmationPage enterConfirmationPassword(String password) {
        clearAndType(confirmationPasswordField, password);
        return this;
    }
    
    /**
     * Click confirm delete button
     * @return LoginPage instance if account deletion successful
     */
    public LoginPage clickConfirmDelete() {
        waitForElementToBeClickable(confirmDeleteButton).click();
        return new LoginPage(driver);
    }
    
    /**
     * Click cancel button
     * @return AccountSettingsPage instance
     */
    public AccountSettingsPage clickCancel() {
        waitForElementToBeClickable(cancelButton).click();
        return new AccountSettingsPage(driver);
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
     * Confirm account deletion
     * @param password Password to confirm deletion
     * @return LoginPage instance if deletion successful
     */
    public LoginPage confirmAccountDeletion(String password) {
        enterConfirmationPassword(password);
        return clickConfirmDelete();
    }
}