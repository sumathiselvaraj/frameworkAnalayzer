package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the LMS login page
 */
public class LoginPage extends BasePage {
    // Page elements
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(xpath = "//select[@name='role']")
    private WebElement roleDropdown;
    
    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;
    
    @FindBy(linkText = "Forgot Password")
    private WebElement forgotPasswordLink;
    
    @FindBy(className = "error-message")
    private WebElement errorMessage;
    
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
     * Select role
     * @param role Role to select (e.g., "Admin", "Student", "Teacher")
     * @return LoginPage instance for method chaining
     */
    public LoginPage selectRole(String role) {
        selectDropdownOptionByText(roleDropdown, role);
        return this;
    }
    
    /**
     * Select dropdown option by visible text
     * @param dropdown WebElement dropdown
     * @param text Text of option to select
     */
    private void selectDropdownOptionByText(WebElement dropdown, String text) {
        org.openqa.selenium.support.ui.Select select = 
            new org.openqa.selenium.support.ui.Select(waitForElementToBeVisible(dropdown));
        select.selectByVisibleText(text);
    }
    
    /**
     * Click login button
     * @return DashboardPage instance if login is successful
     */
    public DashboardPage clickLoginButton() {
        waitForElementToBeClickable(loginButton).click();
        if (!isErrorMessageDisplayed()) {
            return new DashboardPage(driver);
        }
        // If login failed, stay on login page
        return this;
    }
    
    /**
     * Click forgot password link
     * @return ForgotPasswordPage instance
     */
    public ForgotPasswordPage clickForgotPassword() {
        waitForElementToBeClickable(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }
    
    /**
     * Login with credentials
     * @param username Username to enter
     * @param password Password to enter
     * @param role Role to select
     * @return DashboardPage instance if login is successful
     */
    public DashboardPage login(String username, String password, String role) {
        enterUsername(username);
        enterPassword(password);
        selectRole(role);
        return clickLoginButton();
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