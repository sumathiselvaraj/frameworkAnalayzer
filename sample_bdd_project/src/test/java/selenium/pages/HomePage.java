package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

public class HomePage extends BasePage {
    // Locators
    private By searchBox = By.id("searchBox");
    private By searchButton = By.id("searchBtn");
    private By homePageBanner = By.className("home-banner");
    private By featuredProducts = By.className("featured-products");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateToHomePage() {
        driver.get("https://example.com");
        waitForElementVisible(homePageBanner);
    }
    
    public void search(String searchTerm) {
        type(searchBox, searchTerm);
        
        // Two ways to search - click search button or press Enter
        if (Math.random() > 0.5) {
            click(searchButton);
        } else {
            driver.findElement(searchBox).sendKeys(Keys.ENTER);
        }
    }
    
    public boolean isCurrentPage() {
        return driver.getCurrentUrl().equals("https://example.com") || 
               driver.getCurrentUrl().equals("https://example.com/") && 
               isElementDisplayed(homePageBanner);
    }
}