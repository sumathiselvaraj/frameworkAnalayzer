package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the register page
 */
public class RegisterPage extends BasePage {
    // Page elements
    @FindBy(id = "register-email")
    private WebElement emailField;
    
    @FindBy(id = "register-username")
    private WebElement usernameField;
    
    @FindBy(id = "register-password")
    private WebElement passwordField;
    
    @FindBy(id = "register-confirm-password")
    private WebElement confirmPasswordField;
    
    @FindBy(id = "register-first-name")
    private WebElement firstNameField;
    
    @FindBy(id = "register-last-name")
    private WebElement lastNameField;
    
    @FindBy(id = "register-button")
    private WebElement registerButton;
    
    @FindBy(id = "login-link")
    private WebElement loginLink;
    
    @FindBy(className = "register-error")
    private List<WebElement> errorMessages;
    
    @FindBy(id = "terms-checkbox")
    private WebElement termsCheckbox;
    
    /**
     * Constructor for RegisterPage
     * @param driver WebDriver instance
     */
    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enter email
     * @param email Email to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterEmail(String email) {
        clearAndType(emailField, email);
        return this;
    }
    
    /**
     * Enter username
     * @param username Username to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterUsername(String username) {
        clearAndType(usernameField, username);
        return this;
    }
    
    /**
     * Enter password
     * @param password Password to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterPassword(String password) {
        clearAndType(passwordField, password);
        return this;
    }
    
    /**
     * Enter confirm password
     * @param confirmPassword Confirm password to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterConfirmPassword(String confirmPassword) {
        clearAndType(confirmPasswordField, confirmPassword);
        return this;
    }
    
    /**
     * Enter first name
     * @param firstName First name to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterFirstName(String firstName) {
        clearAndType(firstNameField, firstName);
        return this;
    }
    
    /**
     * Enter last name
     * @param lastName Last name to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterLastName(String lastName) {
        clearAndType(lastNameField, lastName);
        return this;
    }
    
    /**
     * Click terms checkbox
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage clickTermsCheckbox() {
        waitForElementToBeClickable(termsCheckbox).click();
        return this;
    }
    
    /**
     * Click register button
     * @return HomePage instance if registration successful
     */
    public HomePage clickRegisterButton() {
        waitForElementToBeClickable(registerButton).click();
        // If no errors, assume registration successful
        if (errorMessages.isEmpty()) {
            return new HomePage(driver);
        }
        return null;
    }
    
    /**
     * Click login link
     * @return LoginPage instance
     */
    public LoginPage clickLoginLink() {
        waitForElementToBeClickable(loginLink).click();
        return new LoginPage(driver);
    }
    
    /**
     * Get error messages
     * @return List of error message texts
     */
    public List<String> getErrorMessages() {
        return waitForElementsToBeVisible(errorMessages).stream()
                .map(WebElement::getText)
                .toList();
    }
    
    /**
     * Complete registration form
     * @param email Email to enter
     * @param username Username to enter
     * @param password Password to enter
     * @param firstName First name to enter
     * @param lastName Last name to enter
     * @return HomePage instance if registration successful
     */
    public HomePage completeRegistration(String email, String username, String password, 
                                         String firstName, String lastName) {
        enterEmail(email)
            .enterUsername(username)
            .enterPassword(password)
            .enterConfirmPassword(password)
            .enterFirstName(firstName)
            .enterLastName(lastName)
            .clickTermsCheckbox();
        
        return clickRegisterButton();
    }
}