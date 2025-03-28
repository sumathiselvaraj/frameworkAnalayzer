package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the dashboard page
 */
public class DashboardPage extends BasePage {
    // Page elements
    @FindBy(id = "welcome-message")
    private WebElement welcomeMessage;
    
    @FindBy(id = "user-profile")
    private WebElement userProfileLink;
    
    @FindBy(id = "logout-button")
    private WebElement logoutButton;
    
    @FindBy(className = "notification-item")
    private List<WebElement> notificationItems;
    
    @FindBy(id = "search-input")
    private WebElement searchInput;
    
    @FindBy(id = "search-button")
    private WebElement searchButton;
    
    /**
     * Constructor for DashboardPage
     * @param driver WebDriver instance
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get welcome message text
     * @return Welcome message text
     */
    public String getWelcomeMessage() {
        return waitForElementToBeVisible(welcomeMessage).getText();
    }
    
    /**
     * Click user profile link
     * @return ProfilePage instance
     */
    public ProfilePage clickUserProfile() {
        waitForElementToBeClickable(userProfileLink).click();
        return new ProfilePage(driver);
    }
    
    /**
     * Click logout button
     * @return LoginPage instance
     */
    public LoginPage clickLogout() {
        waitForElementToBeClickable(logoutButton).click();
        return new LoginPage(driver);
    }
    
    /**
     * Get notification count
     * @return Number of notifications
     */
    public int getNotificationCount() {
        return waitForElementsToBeVisible(notificationItems).size();
    }
    
    /**
     * Perform search
     * @param searchTerm Search term to enter
     * @return SearchResultsPage instance
     */
    public SearchResultsPage performSearch(String searchTerm) {
        clearAndType(searchInput, searchTerm);
        waitForElementToBeClickable(searchButton).click();
        return new SearchResultsPage(driver);
    }
    
    /**
     * Check if dashboard is loaded
     * @return true if dashboard is loaded, false otherwise
     */
    public boolean isDashboardLoaded() {
        return waitForElementToBeVisible(welcomeMessage) != null;
    }
}