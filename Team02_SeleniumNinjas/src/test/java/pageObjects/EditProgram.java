package pageObjects;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ConfigReader;

public class EditProgram {

	
//	private ConfigReader configReader;
//	Properties prop;
	private WebDriver driver;
	
	public EditProgram(WebDriver driver) {
		this.driver = driver;
		}
	{
	
//	configReader=new ConfigReader();
//	prop =configReader.init_prop();
		}
	private By Prog = By.xpath("//span[text()='Program']");
	private By search = By.xpath("//input[@placeholder='Search...']");
    private By edit = By.id("editProgram");
    private By cancelBtn =By.xpath("//span[normalize-space()='Cancel']");
    private By PrgmHeading =By.xpath("//div[normalize-space()='Manage Program']");
    private By editHeading = By.xpath("//span[text()='Program Details']");
    private By nameFieldAstrik = By.xpath("//label[text()='Name']//span[contains(@style, 'color: red !important;')]");
    private By name = By.id("programName");
    private By description = By.id("programDescription");
    private By save = By.xpath("//button[@id='saveProgram']");
    private By closeI = By.xpath("//div[@class='p-dialog-header-icons ng-tns-c81-4']");
    private By activeStatusRadio = By.xpath("//input[@type='radio' and @id='Active']");
   	private By inactiveStatusRadio = By.xpath("//input[@type='radio' and @id='Inactive']");
   	private By updatedName = By.xpath("//tbody/tr[1]/td[2]");
   	private By updateDescription = By.xpath("//tbody/tr[1]/td[3]");
	private By updatedStatus = By.xpath("//tbody/tr[1]/td[4]");

   	
	public void clickProgram() {
		
		driver.findElement(Prog).click();
	}
	
	// Search program by name
	public void searchProgramByName(String pgName) {
	    WebElement searchBox = driver.findElement(search);
	    searchBox.clear();
	    searchBox.sendKeys(pgName);
	}
	
	public void searchUpdatedProgram(String pname) {
	    WebElement searchBox = driver.findElement(search);
	    searchBox.clear();
	    searchBox.sendKeys(pname);
	}
	
	public Map<String, String> getUpdatedProgramDetails() {
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	    Map<String, String> programDetails = new HashMap<>();

	    // Wait for and scroll to the program Name element, then fetch its text
	    WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(updatedName));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nameElement);
	    programDetails.put("Name", nameElement.getText());

	    // Wait for and scroll to the program Description element, then fetch its text
	    WebElement descriptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(updateDescription));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", descriptionElement);
	    programDetails.put("Description", descriptionElement.getText());

	    // Wait for and scroll to the program Status element, then fetch its text
	    WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(updatedStatus));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", statusElement);
	    programDetails.put("Status", statusElement.getText());

	    return programDetails;
	}

	// Click edit for the given program
	public void clickEditByProgName(String PrgmName) {
		
	 WebElement programRow = driver.findElement(By.xpath("//tr[td[text()='" + PrgmName + "']]"));
		   
	 WebElement editButton = driver.findElement(edit); 
	 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
	  
	}
	
  public void getTextEditWindow() {
	String actualHeading = driver.findElement(editHeading).getText();
	Assert.assertEquals(actualHeading, "Program Details", "The heading does not match the expected 'Program Details'!");
	System.out.println(actualHeading);
}

    public void verifyMandatoryFieldAsterisk() {
        WebElement field_Name = driver.findElement(nameFieldAstrik);
        Assert.assertTrue(field_Name.isDisplayed(), "The red asterisk mark is not visible with 'Name' field.");
        System.out.println("Red asterisk is visible with 'Name' field.");
 
}

    public void clickSaveButton() {
        
            WebElement saveBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(save));
            
            // Scroll into view and click using JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
        }
         
    public void updateField(String field, String value) {
        WebElement fieldElement;
        
        switch (field) {
            case "Name":
                fieldElement = driver.findElement(name); 
                fieldElement.clear();
                fieldElement.sendKeys(value);
                break;
            case "Description":
                fieldElement = driver.findElement(description); 
                fieldElement.clear();
                fieldElement.sendKeys(value);
                break;
           
            default:
                throw new IllegalArgumentException("Invalid field: " + field);
        }
    }
    
  
public void selectStatus(String status) throws InterruptedException {
	WebElement radioButton = null;
	if (status.equalsIgnoreCase("Active")) {
		radioButton = driver.findElement(activeStatusRadio);
	} else if (status.equalsIgnoreCase("Inactive")) {
		radioButton = driver.findElement(inactiveStatusRadio);
	}
	// Scroll into view before clicking
	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", radioButton);
	Thread.sleep(500);
	// Click using JavaScriptExecutor
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
}

public void clickEdit() {
	   
	 WebElement editButton = driver.findElement(edit); 
	 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
	  
	}
public void cancelBtn() {
	
	 WebElement cancelButton = new WebDriverWait(driver, Duration.ofSeconds(10))
             .until(ExpectedConditions.elementToBeClickable(cancelBtn));
	
//    driver.findElement(cancelBtn).click();
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",cancelButton );
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cancelButton);
}

    public void clickCloseIcon() {
    
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    	WebElement closeIcon = wait.until(ExpectedConditions.elementToBeClickable(closeI));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeIcon);
    	closeIcon.click();

}

    public String getPrgmPageHeading() {

	return driver.findElement(PrgmHeading).getText();
}


}






