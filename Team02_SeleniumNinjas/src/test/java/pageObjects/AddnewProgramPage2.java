package pageObjects;

import org.openqa.selenium.WebDriver;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class AddnewProgramPage2 {
	
	private WebDriver driver;
	private WebDriverWait wait;

	
	private By AddnewProgrambutton = By.xpath("//button[contains(text(),'Add New Program')]");
	private By popupWindow = By.xpath("//p-dialog[@header='Program Details']");
	private By cancelButton = By.xpath("//span[contains(text(),'Cancel')]");
	private By SaveButton = By.xpath("//span[contains(text(),'Save')]");	
	private By popupWindowtitle = By.xpath("//span[@id='pr_id_3-label']");
	private By nameAsteriskMark = By.xpath("//label[contains(text(),'Name')]//span[contains(@style,'color: red')]");
	private By programDetailsForm = By.xpath("//p-dialog[@header='Program Details']");
    private By nameField = By.xpath("//input[@id='programName']");
    private By descriptionField = By.xpath("//input[@id='programDescription']");

    
    private By activeStatus = By.xpath("//input[@value='Active']");
    private By inactiveStatus = By.xpath("//input[@value='Inactive']");
    private By Programerrormsg = By.xpath("//small[contains(text(),'Program name is required.')]");
    private By successmessage = By.xpath("//div[contains(text(),'Program Created Successfully')]");
    private By xcrossbutton = By.xpath("/html/body/app-root/app-program/p-dialog/div/div/div[1]/div/button");
  

	
    public AddnewProgramPage2(WebDriver driver) {
	this.driver = driver;
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	
    }
     
     public void Programbuttonclick() {
		   driver.findElement(AddnewProgrambutton).click(); 
		  
		   }

     public boolean isPopupWindowDisplayed() {

         return wait.until(ExpectedConditions.visibilityOfElementLocated(popupWindow)).isDisplayed();
     }
     public String Popupwindowtitle() {

         WebElement headingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(popupWindowtitle));         
         return headingElement.getText();
		  
		   }
     
     public boolean isNameAsteriskMarkDisplayed() {
    	 return driver.findElement(nameAsteriskMark).isDisplayed();
     }

     public void clickCancelButton() {
         driver.findElement(cancelButton).click();
         wait.until(ExpectedConditions.invisibilityOfElementLocated(programDetailsForm));
     }
     
     
     public void clickSaveButton() {
         driver.findElement(SaveButton).click();
//         wait.until(ExpectedConditions.invisibilityOfElementLocated(programDetailsForm));
     }
     public boolean isProgramDetailsFormDisplayed() {
         try {
             return driver.findElement(popupWindow).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
     }
     
     public void enterName(String name) {
         driver.findElement(nameField).sendKeys(name);
     }
     
    
     public String getNameText() {
         return driver.findElement(nameField).getAttribute("value");
     }

    
     public void enterDescription(String description) {
         driver.findElement(descriptionField).sendKeys(description);
     }


     public String getDescriptionText() {
         return driver.findElement(descriptionField).getAttribute("value");
     }
     
     
     
     public void selectStatus(String status) {

         JavascriptExecutor js = (JavascriptExecutor) driver;

         if (status.equalsIgnoreCase("Active")) {
             WebElement activeElement = wait.until(ExpectedConditions.presenceOfElementLocated(activeStatus));             
             js.executeScript("arguments[0].click();", activeElement);            
         } else if (status.equalsIgnoreCase("Inactive")) {
             System.out.println("Waiting for Inactive status to be present...");
             WebElement inactiveElement = wait.until(ExpectedConditions.presenceOfElementLocated(inactiveStatus));
             System.out.println("Inactive status is present. Attempting to click...");
             js.executeScript("arguments[0].click();", inactiveElement);
             System.out.println("Inactive status clicked using JavaScript.");
         }

    }
     
     public String getProgramNameErrorMessage() {
         return driver.findElement(Programerrormsg).getText();
     }
 
     
     public String getSuccessMessage() {
         return driver.findElement(successmessage).getText();
     }
     
     
     public void Xbuttonclick() {

    	 WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(xcrossbutton));
    	 closeButton.click();

		  
		   }


     
}    




