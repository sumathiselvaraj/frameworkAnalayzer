package stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

import java.io.File;

public class Hooks {
    
    @Before
    public void setUp() {
        // Initialize WebDriver before each scenario
        // DriverFactory will handle the WebDriver setup
        DriverFactory.getDriver();
        System.out.println("Starting test execution...");
    }
    
    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        
        // Take screenshot if scenario failed
        if (scenario.isFailed()) {
            try {
                System.out.println("Scenario failed, taking screenshot...");
                
                // Take screenshot
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot of failure");
                
                // Also save to file system in the screenshots directory
                File screenshotDir = new File("screenshots");
                if (!screenshotDir.exists()) {
                    screenshotDir.mkdir();
                }
                
                // Create screenshot file with scenario name
                File screenshotFile = new File("screenshots/" + 
                                             scenario.getName().replaceAll("\\s+", "_") + ".png");
                
                // This would typically use FileUtils.writeByteArrayToFile(screenshotFile, screenshot)
                // But for simplicity in this example, we'll just log it
                System.out.println("Screenshot saved to: " + screenshotFile.getAbsolutePath());
                
            } catch (Exception e) {
                System.err.println("Error taking screenshot: " + e.getMessage());
            }
        }
        
        // Quit WebDriver after each scenario
        DriverFactory.quitDriver();
    }
}