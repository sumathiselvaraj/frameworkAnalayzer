package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the LMS forgot password page
 */
public class ForgotPasswordPage extends BasePage {
    // Page elements
    @FindBy(id = "email")
    private WebElement emailField;
    
    @FindBy(xpath = "//button[text()='Reset Password']")
    private WebElement resetPasswordButton;
    
    @FindBy(linkText = "Back to Login")
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
     * Enter email address
     * @param email Email to enter
     * @return ForgotPasswordPage instance for method chaining
     */
    public ForgotPasswordPage enterEmail(String email) {
        clearAndType(emailField, email);
        return this;
    }
    
    /**
     * Click reset password button
     * @return ForgotPasswordPage instance after clicking
     */
    public ForgotPasswordPage clickResetPassword() {
        waitForElementToBeClickable(resetPasswordButton).click();
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
     * Request password reset
     * @param email Email address for password reset
     * @return ForgotPasswordPage instance after password reset request
     */
    public ForgotPasswordPage requestPasswordReset(String email) {
        enterEmail(email);
        return clickResetPassword();
    }
}