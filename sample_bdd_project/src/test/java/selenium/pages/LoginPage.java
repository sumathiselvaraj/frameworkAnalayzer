package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the login page
 */
public class LoginPage extends BasePage {
    // Page elements
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "login-button")
    private WebElement loginButton;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
    @FindBy(linkText = "Forgot Password?")
    private WebElement forgotPasswordLink;
    
    @FindBy(linkText = "Sign Up")
    private WebElement signUpLink;
    
    /**
     * Constructor for LoginPage
     * @param driver WebDriver instance
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enter username
     * @param username Username to enter
     * @return LoginPage instance for method chaining
     */
    public LoginPage enterUsername(String username) {
        clearAndType(usernameField, username);
        return this;
    }
    
    /**
     * Enter password
     * @param password Password to enter
     * @return LoginPage instance for method chaining
     */
    public LoginPage enterPassword(String password) {
        clearAndType(passwordField, password);
        return this;
    }
    
    /**
     * Click login button
     * @return DashboardPage instance if login successful
     */
    public DashboardPage clickLoginButton() {
        waitForElementToBeClickable(loginButton).click();
        return new DashboardPage(driver);
    }
    
    /**
     * Perform login with credentials
     * @param username Username to enter
     * @param password Password to enter
     * @return DashboardPage instance if login successful
     */
    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLoginButton();
    }
    
    /**
     * Get error message text
     * @return Error message text
     */
    public String getErrorMessage() {
        return waitForElementToBeVisible(errorMessage).getText();
    }
    
    /**
     * Check if error message is displayed
     * @return true if error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
    
    /**
     * Click forgot password link
     * @return ForgotPasswordPage instance
     */
    public ForgotPasswordPage clickForgotPasswordLink() {
        waitForElementToBeClickable(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }
    
    /**
     * Click sign up link
     * @return SignUpPage instance
     */
    public SignUpPage clickSignUpLink() {
        waitForElementToBeClickable(signUpLink).click();
        return new SignUpPage(driver);
    }
}