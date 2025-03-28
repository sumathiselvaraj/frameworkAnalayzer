package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DeleteProgram {
	
    private WebDriver driver;
    private WebDriverWait wait;	
    
    By deleteicon = By.xpath("//*[@class='p-button-icon pi pi-trash']");
    By successMsg = By.xpath("//div[@role='alert']");
    By yesdeleteConfirm=By.xpath("//span[@class='p-button-icon p-button-icon-left pi pi-check']");
    By nodeleteConfirm=By.xpath("//span[@class='p-button-icon p-button-icon-left pi pi-times']");
    By deleteClose=By.xpath("//span[@class='pi pi-times ng-tns-c118-10']");
    By confirmPopUp=By.xpath("//span[contains(text(),'Confirm')]");
    By deletetSuccessMsg =By.xpath("//div[@role='alert']");

    public DeleteProgram(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait of 10 seconds
    }

 
    public void clickDeleteByProgName(String programName) {
    	  	try {
        // Locate the row containing the specified program name
        WebElement programRow = driver.findElement(By.xpath("//tr[td[text()='" + programName + "']]"));
       
       WebElement deleteButton = driver.findElement(By.xpath("//button[@id='deleteProgram']//span[@class='p-button-icon pi pi-trash']"));

        
        // Scroll to the delete button to ensure it's in view
       ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
        
        // Click the delete button
        deleteButton.click();
    } catch  (ElementClickInterceptedException e) {
        // Log the exception or handle it as needed
        System.out.println("ElementClickInterceptedException caught: " + e.getMessage());
    }
    }
    
    public void clickDeleteIcon() {
        try {
            // Wait for the delete button to be clickable
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[1]/td[5]/div[1]/span[1]/button[2]/span[1]")));
            
            // Click using JavaScript if normal click fails
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
            System.out.println("Delete button clicked successfully using JavaScript.");
        } catch (Exception e) {
            System.err.println("Error: Unable to click delete button using JavaScript. " + e.getMessage());
        }
    }

    public String confirmDeletePopup() {
    	
    	 // Locate the confirmation button dynamically using text
         return driver.findElement(confirmPopUp).getText();
        
     
    }

    public void confirmsDeleteProgram(String buttonText) {
    try {
        // Locate the confirmation button dynamically using text
        WebElement confirmButton = driver.findElement(By.xpath("//button[contains(text(), '" + buttonText + "')]"));
        
        // Click the button
        confirmButton.click();
        System.out.println("Clicked '" + buttonText + "' button on the Confirm deletion form.");
    } catch (Exception e) {
        System.err.println("Error: Unable to click '" + buttonText + "' button. " + e.getMessage());
    }
    }
   
	public void ConfirmDeleteNo()
	{
		WebElement actionNo = wait.until(ExpectedConditions.elementToBeClickable(nodeleteConfirm));
		
		// Scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionNo);
        
        // Perform the click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionNo);
        
			
	} 
	
	
	public void deleteClose()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		WebElement close = wait.until(ExpectedConditions.elementToBeClickable(deleteClose));
		// Scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",close);
        
        // Perform the click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", close);
        

		
	}
	
  
    
    public void clickConfirmDeletionButton(String action) {
            
        // Wait for the element to be clickable
        WebElement actionYes = wait.until(ExpectedConditions.elementToBeClickable(yesdeleteConfirm));
        
        // Scroll the element into view
       // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionYes);
        
        // Perform the click using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionYes);
     
    }

   public String deletehandlealert() {

	 	// Wait for the toast to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement successMsg = wait.until(ExpectedConditions.presenceOfElementLocated(deletetSuccessMsg));

		// Retrieve and print the toast message text
		String message = successMsg.getText();
		System.out.println("Toast Message: " + message);
		return message;
	}
}
 	   
	 
    
    
    







