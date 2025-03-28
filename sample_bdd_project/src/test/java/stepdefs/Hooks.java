package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hooks for Cucumber tests
 * Contains setup and teardown methods
 */
public class Hooks {
    public static WebDriver driver;
    private static boolean isDriverInitialized = false;
    private static Properties config = new Properties();
    
    /**
     * Setup method that runs before any test
     * Initializes WebDriver and loads configuration
     */
    @Before
    public void setup() {
        if (!isDriverInitialized) {
            try {
                // Load configuration from properties file
                config.load(new FileInputStream("src/test/resources/config/config.properties"));
            } catch (IOException e) {
                System.out.println("Configuration file not found. Using default settings.");
            }
            
            String browser = config.getProperty("browser", "chrome");
            String baseUrl = config.getProperty("base.url", "http://localhost:8080/lms");
            int implicitWait = Integer.parseInt(config.getProperty("implicit.wait", "10"));
            
            // Initialize driver based on browser configuration
            switch (browser.toLowerCase()) {
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "chrome":
                default:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-infobars");
                    chromeOptions.addArguments("--disable-notifications");
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }
            
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.manage().window().maximize();
            isDriverInitialized = true;
        }
        
        // Navigate to base URL for each test if needed
        driver.get(config.getProperty("base.url", "http://localhost:8080/lms"));
    }
    
    /**
     * Take screenshot after each failed step
     * @param scenario Current scenario
     */
    @AfterStep
    public void takeScreenshotAfterFailedStep(Scenario scenario) {
        if (scenario.isFailed() && driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of failed step");
        }
    }
    
    /**
     * Cleanup method that runs after all tests are complete
     * Closes the WebDriver
     */
    @After
    public void teardown() {
        // Don't close the driver after each scenario to reuse it
        // We'll only close it when all tests are done
    }
    
    /**
     * Final cleanup method with highest order to ensure it runs last
     * @param scenario Current scenario
     */
    @After(order = 99)
    public void finalTeardown(Scenario scenario) {
        // Take screenshot after failed scenario
        if (scenario.isFailed() && driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot of failed scenario");
        }
        
        // Close driver when all tests are done
        if (isDriverInitialized && System.getProperty("cucumber.keepOpen") == null) {
            driver.quit();
            isDriverInitialized = false;
        }
    }
}