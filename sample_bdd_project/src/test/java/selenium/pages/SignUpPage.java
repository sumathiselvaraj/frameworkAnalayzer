package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the sign up page
 */
public class SignUpPage extends BasePage {
    // Page elements
    @FindBy(id = "signup-email")
    private WebElement emailField;
    
    @FindBy(id = "signup-username")
    private WebElement usernameField;
    
    @FindBy(id = "signup-password")
    private WebElement passwordField;
    
    @FindBy(id = "signup-confirm-password")
    private WebElement confirmPasswordField;
    
    @FindBy(id = "signup-first-name")
    private WebElement firstNameField;
    
    @FindBy(id = "signup-last-name")
    private WebElement lastNameField;
    
    @FindBy(id = "signup-button")
    private WebElement signUpButton;
    
    @FindBy(id = "login-link")
    private WebElement loginLink;
    
    @FindBy(className = "signup-error")
    private List<WebElement> errorMessages;
    
    @FindBy(id = "terms-checkbox")
    private WebElement termsCheckbox;
    
    /**
     * Constructor for SignUpPage
     * @param driver WebDriver instance
     */
    public SignUpPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Enter email
     * @param email Email to enter
     * @return SignUpPage instance for method chaining
     */
    public SignUpPage enterEmail(String email) {
        clearAndType(emailField, email);
        return this;
    }
    
    /**
     * Enter username
     * @param username Username to enter
     * @return SignUpPage instance for method chaining
     */
    public SignUpPage enterUsername(String username) {
        clearAndType(usernameField, username);
        return this;
    }
    
    /**
     * Enter password
     * @param password Password to enter
     * @return SignUpPage instance for method chaining
     */
    public SignUpPage enterPassword(String password) {
        clearAndType(passwordField, password);
        return this;
    }
    
    /**
     * Enter confirm password
     * @param confirmPassword Confirm password to enter
     * @return SignUpPage instance for method chaining
     */
    public SignUpPage enterConfirmPassword(String confirmPassword) {
        clearAndType(confirmPasswordField, confirmPassword);
        return this;
    }
    
    /**
     * Enter first name
     * @param firstName First name to enter
     * @return SignUpPage instance for method chaining
     */
    public SignUpPage enterFirstName(String firstName) {
        clearAndType(firstNameField, firstName);
        return this;
    }
    
    /**
     * Enter last name
     * @param lastName Last name to enter
     * @return SignUpPage instance for method chaining
     */
    public SignUpPage enterLastName(String lastName) {
        clearAndType(lastNameField, lastName);
        return this;
    }
    
    /**
     * Click terms checkbox
     * @return SignUpPage instance for method chaining
     */
    public SignUpPage clickTermsCheckbox() {
        waitForElementToBeClickable(termsCheckbox).click();
        return this;
    }
    
    /**
     * Click sign up button
     * @return HomePage instance if sign up successful
     */
    public HomePage clickSignUpButton() {
        waitForElementToBeClickable(signUpButton).click();
        // If no errors, assume sign up successful
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
     * Complete sign up form
     * @param email Email to enter
     * @param username Username to enter
     * @param password Password to enter
     * @param firstName First name to enter
     * @param lastName Last name to enter
     * @return HomePage instance if sign up successful
     */
    public HomePage completeSignUp(String email, String username, String password, 
                                  String firstName, String lastName) {
        enterEmail(email)
            .enterUsername(username)
            .enterPassword(password)
            .enterConfirmPassword(password)
            .enterFirstName(firstName)
            .enterLastName(lastName)
            .clickTermsCheckbox();
        
        return clickSignUpButton();
    }
}