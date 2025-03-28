package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    // Locators
    private By welcomeMessage = By.id("welcomeMessage");
    private By dashboardHeader = By.className("dashboard-header");
    private By userMenu = By.id("userMenu");
    private By logoutButton = By.id("logoutBtn");
    
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }
    
    public boolean isCurrentPage() {
        return driver.getCurrentUrl().contains("/dashboard") && 
               isElementDisplayed(dashboardHeader);
    }
    
    public void openUserMenu() {
        click(userMenu);
    }
    
    public void logout() {
        openUserMenu();
        click(logoutButton);
    }
}