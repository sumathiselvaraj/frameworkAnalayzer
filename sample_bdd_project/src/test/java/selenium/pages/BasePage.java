package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Base Page class that contains common methods for all page objects
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Wait for element to be clickable
     * @param element WebElement to wait for
     * @return The WebElement once it is clickable
     */
    protected WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Wait for element to be visible
     * @param element WebElement to wait for
     * @return The WebElement once it is visible
     */
    protected WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    /**
     * Wait for elements to be visible
     * @param elements List of WebElements to wait for
     * @return The List of WebElements once they are visible
     */
    protected List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
    
    /**
     * Wait for element with locator to be present
     * @param locator By locator to find element
     * @return The WebElement once it is present
     */
    protected WebElement waitForElementToBePresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    /**
     * Clear field and type text
     * @param element WebElement to type into
     * @param text Text to type
     */
    protected void clearAndType(WebElement element, String text) {
        waitForElementToBeClickable(element).clear();
        element.sendKeys(text);
    }
    
    /**
     * Click element with JavaScript (useful for hidden elements)
     * @param element WebElement to click
     */
    protected void clickWithJS(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }
    
    /**
     * Scroll to element
     * @param element WebElement to scroll to
     */
    protected void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Get page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Check if element is displayed
     * @param element WebElement to check
     * @return true if element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Create custom wait condition for text to be present in element
     * @param element WebElement to check
     * @param text Text to check for
     * @return true if text is present, false otherwise
     */
    protected boolean waitForTextToBePresent(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}