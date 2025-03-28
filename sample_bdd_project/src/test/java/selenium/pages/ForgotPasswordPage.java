package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the forgot password page
 */
public class ForgotPasswordPage extends BasePage {
    // Page elements
    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(id = "submit-button")
    private WebElement submitButton;
    
    @FindBy(id = "back-to-login")
    private WebElement backToLoginLink;
    
    @FindBy(className = "success-message")
    private WebElement successMessage;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    /**
     * Constructor for ForgotPasswordPage
     * @param driver WebDriver instance
     */
    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enter email
     * @param email Email to enter
     * @return ForgotPasswordPage instance for method chaining
     */
    public ForgotPasswordPage enterEmail(String email) {
        clearAndType(emailField, email);
        return this;
    }
    
    /**
     * Click submit button
     * @return ForgotPasswordPage instance for method chaining
     */
    public ForgotPasswordPage clickSubmit() {
        waitForElementToBeClickable(submitButton).click();
        return this;
    }
    
    /**
     * Click back to login link
     * @return LoginPage instance
     */
    public LoginPage clickBackToLogin() {
        waitForElementToBeClickable(backToLoginLink).click();
        return new LoginPage(driver);
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
     * Submit email for password reset
     * @param email Email to submit
     * @return ForgotPasswordPage instance
     */
    public ForgotPasswordPage submitPasswordReset(String email) {
        enterEmail(email);
        clickSubmit();
        return this;
    }
}