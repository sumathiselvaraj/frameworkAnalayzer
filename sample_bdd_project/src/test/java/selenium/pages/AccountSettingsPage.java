package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the account settings page
 */
public class AccountSettingsPage extends BasePage {
    // Page elements
    @FindBy(id = "notification-preferences")
    private WebElement notificationPreferencesSection;
    
    @FindBy(className = "notification-option")
    private List<WebElement> notificationOptions;
    
    @FindBy(id = "privacy-settings")
    private WebElement privacySettingsSection;
    
    @FindBy(className = "privacy-option")
    private List<WebElement> privacyOptions;
    
    @FindBy(id = "language-selector")
    private WebElement languageSelector;
    
    @FindBy(id = "timezone-selector")
    private WebElement timezoneSelector;
    
    @FindBy(id = "save-settings-button")
    private WebElement saveSettingsButton;
    
    @FindBy(id = "cancel-button")
    private WebElement cancelButton;
    
    @FindBy(id = "delete-account-button")
    private WebElement deleteAccountButton;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    /**
     * Constructor for AccountSettingsPage
     * @param driver WebDriver instance
     */
    public AccountSettingsPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Toggle notification option by index
     * @param index Index of the notification option to toggle (0-based)
     * @return AccountSettingsPage instance for method chaining
     */
    public AccountSettingsPage toggleNotificationOption(int index) {
        waitForElementsToBeVisible(notificationOptions);
        if (index < notificationOptions.size()) {
            waitForElementToBeClickable(notificationOptions.get(index)).click();
            return this;
        }
        throw new IndexOutOfBoundsException("Notification option index out of bounds");
    }
    
    /**
     * Toggle privacy option by index
     * @param index Index of the privacy option to toggle (0-based)
     * @return AccountSettingsPage instance for method chaining
     */
    public AccountSettingsPage togglePrivacyOption(int index) {
        waitForElementsToBeVisible(privacyOptions);
        if (index < privacyOptions.size()) {
            waitForElementToBeClickable(privacyOptions.get(index)).click();
            return this;
        }
        throw new IndexOutOfBoundsException("Privacy option index out of bounds");
    }
    
    /**
     * Select language
     * @param language Language to select
     * @return AccountSettingsPage instance for method chaining
     */
    public AccountSettingsPage selectLanguage(String language) {
        // Implementation depends on the actual dropdown implementation
        // This is a simplified version
        waitForElementToBeClickable(languageSelector).click();
        // Find and click the option with the given language
        // ...
        return this;
    }
    
    /**
     * Select timezone
     * @param timezone Timezone to select
     * @return AccountSettingsPage instance for method chaining
     */
    public AccountSettingsPage selectTimezone(String timezone) {
        // Implementation depends on the actual dropdown implementation
        // This is a simplified version
        waitForElementToBeClickable(timezoneSelector).click();
        // Find and click the option with the given timezone
        // ...
        return this;
    }
    
    /**
     * Click save settings button
     * @return AccountSettingsPage instance for method chaining
     */
    public AccountSettingsPage clickSaveSettings() {
        waitForElementToBeClickable(saveSettingsButton).click();
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
     * Click delete account button
     * @return DeleteAccountConfirmationPage instance
     */
    public DeleteAccountConfirmationPage clickDeleteAccount() {
        waitForElementToBeClickable(deleteAccountButton).click();
        return new DeleteAccountConfirmationPage(driver);
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
}