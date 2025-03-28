package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the change password page
 */
public class ChangePasswordPage extends BasePage {
    // Page elements
    @FindBy(id = "current-password")
    private WebElement currentPasswordField;
    
    @FindBy(id = "new-password")
    private WebElement newPasswordField;
    
    @FindBy(id = "confirm-password")
    private WebElement confirmPasswordField;
    
    @FindBy(id = "change-password-button")
    private WebElement changePasswordButton;
    
    @FindBy(id = "cancel-button")
    private WebElement cancelButton;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    /**
     * Constructor for ChangePasswordPage
     * @param driver WebDriver instance
     */
    public ChangePasswordPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enter current password
     * @param currentPassword Current password to enter
     * @return ChangePasswordPage instance for method chaining
     */
    public ChangePasswordPage enterCurrentPassword(String currentPassword) {
        clearAndType(currentPasswordField, currentPassword);
        return this;
    }
    
    /**
     * Enter new password
     * @param newPassword New password to enter
     * @return ChangePasswordPage instance for method chaining
     */
    public ChangePasswordPage enterNewPassword(String newPassword) {
        clearAndType(newPasswordField, newPassword);
        return this;
    }
    
    /**
     * Enter confirm password
     * @param confirmPassword Confirm password to enter
     * @return ChangePasswordPage instance for method chaining
     */
    public ChangePasswordPage enterConfirmPassword(String confirmPassword) {
        clearAndType(confirmPasswordField, confirmPassword);
        return this;
    }
    
    /**
     * Click change password button
     * @return ChangePasswordPage instance for method chaining
     */
    public ChangePasswordPage clickChangePassword() {
        waitForElementToBeClickable(changePasswordButton).click();
        return this;
    }
    
    /**
     * Click cancel button
     * @return ProfilePage instance
     */
    public ProfilePage clickCancel() {
        waitForElementToBeClickable(cancelButton).click();
        return new ProfilePage(driver);
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
     * Change password
     * @param currentPassword Current password
     * @param newPassword New password
     * @return ChangePasswordPage instance with result of change operation
     */
    public ChangePasswordPage changePassword(String currentPassword, String newPassword) {
        enterCurrentPassword(currentPassword)
            .enterNewPassword(newPassword)
            .enterConfirmPassword(newPassword)
            .clickChangePassword();
        return this;
    }
}