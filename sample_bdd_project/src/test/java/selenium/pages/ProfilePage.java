package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the user profile page
 */
public class ProfilePage extends BasePage {
    // Page elements
    @FindBy(id = "profile-name")
    private WebElement profileNameField;
    
    @FindBy(id = "profile-email")
    private WebElement profileEmailField;
    
    @FindBy(id = "update-profile-button")
    private WebElement updateProfileButton;
    
    @FindBy(id = "change-password-link")
    private WebElement changePasswordLink;
    
    @FindBy(id = "account-settings-link")
    private WebElement accountSettingsLink;
    
    @FindBy(className = "profile-success-message")
    private WebElement successMessage;
    
    @FindBy(className = "profile-error-message")
    private WebElement errorMessage;
    
    /**
     * Constructor for ProfilePage
     * @param driver WebDriver instance
     */
    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get profile name
     * @return Profile name
     */
    public String getProfileName() {
        return waitForElementToBeVisible(profileNameField).getAttribute("value");
    }
    
    /**
     * Get profile email
     * @return Profile email
     */
    public String getProfileEmail() {
        return waitForElementToBeVisible(profileEmailField).getAttribute("value");
    }
    
    /**
     * Update profile name
     * @param name New name
     * @return ProfilePage instance for method chaining
     */
    public ProfilePage updateProfileName(String name) {
        clearAndType(profileNameField, name);
        return this;
    }
    
    /**
     * Click update profile button
     * @return ProfilePage instance for method chaining
     */
    public ProfilePage clickUpdateProfile() {
        waitForElementToBeClickable(updateProfileButton).click();
        return this;
    }
    
    /**
     * Click change password link
     * @return ChangePasswordPage instance
     */
    public ChangePasswordPage clickChangePassword() {
        waitForElementToBeClickable(changePasswordLink).click();
        return new ChangePasswordPage(driver);
    }
    
    /**
     * Click account settings link
     * @return AccountSettingsPage instance
     */
    public AccountSettingsPage clickAccountSettings() {
        waitForElementToBeClickable(accountSettingsLink).click();
        return new AccountSettingsPage(driver);
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
}