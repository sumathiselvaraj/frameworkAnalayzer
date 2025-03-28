package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("loginBtn");
    private By errorMessage = By.className("error-message");
    private By loginForm = By.id("loginForm");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToLoginPage() {
        driver.get("https://example.com/login");
        waitForElementVisible(loginForm);
    }
    
    public void enterUsername(String username) {
        type(usernameField, username);
    }
    
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    
    public void clickLoginButton() {
        click(loginButton);
    }
    
    public String getErrorMessage() {
        try {
            return getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isCurrentPage() {
        return driver.getCurrentUrl().contains("/login") && 
               isElementDisplayed(loginForm);
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
}