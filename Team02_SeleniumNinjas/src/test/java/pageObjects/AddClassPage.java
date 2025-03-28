package pageObjects;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerLoad;

public class AddClassPage {

	private WebDriver driver;

	public AddClassPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	boolean status;

	@FindBy(xpath = "//button[normalize-space()='Add New Class']")
	private WebElement addNewCls;
	@FindBy(xpath = "//pre[@id='classdetail']")
	private WebElement OutputclassdetailChk;
	@FindBy(xpath = "/span[@class='p-dialog-header-close-icon ng-tns-c81-15 pi pi-times']")
	private WebElement closeButton;
	private By batchNameField = By.xpath("//input[@placeholder='Select a Batch Name']");
	private By classTopicField = By.xpath("//input[@id='classTopic']");
	private By classDescriptionField = By.xpath("//input[@id='classDescription']");
	private By datePicker = By.xpath("//span[@class='p-button-icon pi pi-calendar']");
	private By classDatesField = By.xpath("//input[@id='icon']");
	private By noOfClassesField = By.xpath("//input[@id='classNo']");
	private By staffNameField = By.xpath("//input[@placeholder='Select a Staff Name']");
	private By activeStatusRadio = By.xpath("//input[@type='radio' and @id='Active']");
	private By inactiveStatusRadio = By.xpath("//input[@type='radio' and @id='Inactive']");
	private By commentsField = By.xpath("//input[@id='classComments']");
	private By notesField = By.xpath("//input[@id='classNotes']");
	private By recordingField = By.xpath("//input[@id='classRecordingPath']");
	private By saveButton = By.xpath("//span[normalize-space()='Save']");
	private By cancelButton = By.xpath("//span[normalize-space()='Cancel']");
	// alert
	private By toastmsg = By.xpath("//div[@role='alert']");
	// private By toastmsgcls = By
	// .xpath("//button[@class='p-toast-icon-close p-link ng-tns-c90-20 p-ripple
	// ng-star-inserted']");

	// Error messages
	private By batchNameFieldReqMsg = By.xpath("//small[normalize-space()='Batch Name is required.']");
	private By classTopicFieldReqMsg = By.xpath("//small[normalize-space()='Class Topic is required.']");
	private By classDatesReqMsg = By.xpath("//small[normalize-space()='Class Date is required.']");
	private By noOfClassesReqMsg = By.xpath("//small[normalize-space()='No. of Classes is required.']");
	private By staffNameReqMsg = By.xpath("//small[normalize-space()='Staff Name is required.']");
	private By statusReqMsg = By.xpath("//small[normalize-space()='Status is required.']");

	public void enterBatchName(String batchName) {
		if (batchName != "") {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p-dropdown[@id='batchName']//div[@role='button']")));
		    dropdownButton.click();
		    
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='listbox']")));
		    List<WebElement> options = driver.findElements(By.xpath("//ul[@role='listbox']/p-dropdownitem//li"));
		    for (WebElement option : options) {
		        if (option.getText().trim().equalsIgnoreCase(batchName.trim())) {
		            option.click();
		            return;
		        }
		    }
		    
		    //throw new NoSuchElementException("Batch Name not found: " + batchName);
		}
	}

	public void enterClassTopic(String classTopic) {
		if (classTopic != "") {
			driver.findElement(classTopicField).clear();
			driver.findElement(classTopicField).sendKeys(classTopic);
		}
	}

	public void enterClassDescription(String classDescription) {
		if (classDescription != "") {
			driver.findElement(classDescriptionField).clear();
			driver.findElement(classDescriptionField).sendKeys(classDescription);
		}
	}

	public void selectClassDates(String classDates) throws InterruptedException {
		if (classDates != "") {
			WebElement dateField = driver.findElement(classDatesField);

			// Clear using JavaScript
			((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", dateField);

			// Send new date
			dateField.sendKeys(classDates);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//body")).click();
			Thread.sleep(1000); // Give UI time to update
		}
	}

	public void enterNoOfClasses(String noOfClasses) {
		if (noOfClasses != "") {
			driver.findElement(noOfClassesField).clear();
			driver.findElement(noOfClassesField).sendKeys(noOfClasses);
		}
	}

	public void enterStaffName(String staffName) {
		if (staffName != "") {
			driver.findElement(staffNameField).clear();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    WebElement dropdownButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p-dropdown[@id='staffId']//div[@role='button']")));		    
		    if (dropdownButton != null) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownButton);				
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownButton);
			}
		    //dropdownButton.click();
		  
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@role='listbox']")));		   
		    List<WebElement> options = driver.findElements(By.xpath("//ul[@role='listbox']/p-dropdownitem//li"));		  
		    for (WebElement option : options) {
		        if (option.getText().trim().equalsIgnoreCase(staffName.trim())) {
		            option.click(); 
		            return;
		        }
		    }
		   
		    //throw new NoSuchElementException("Staff Name not found: " + staffName);
		}
	}

	// Updated method to handle radio button for Status
	public void selectStatus(String status) throws InterruptedException {
		WebElement radioButton = null;
		if (status.equalsIgnoreCase("Active")) {
			radioButton = driver.findElement(activeStatusRadio);
		} else if (status.equalsIgnoreCase("Inactive")) {
			radioButton = driver.findElement(inactiveStatusRadio);
		}
		// else {
		// throw new IllegalArgumentException("Invalid status: " + status);
		// }

		if (radioButton != null) {
			// Scroll into view before clicking
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", radioButton);
			Thread.sleep(500);
			// Click using JavaScriptExecutor
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
		}
	}

	public void enterComments(String comments) {
		if (comments != "") {
			driver.findElement(commentsField).clear();
			driver.findElement(commentsField).sendKeys(comments);
		}
	}

	public void enterNotes(String notes) {
		if (notes != "") {
			driver.findElement(notesField).clear();
			driver.findElement(notesField).sendKeys(notes);
		}
	}

	public void selectRecordingOption(String recording) {
		if (recording != "") {
			driver.findElement(recordingField).clear();
			driver.findElement(recordingField).sendKeys(recording);
		}
	}

	public void clickSaveButton() {
		driver.findElement(saveButton).click();
	}

	// alert
	public String toastMsg() {
		return driver.findElement(toastmsg).getText();
	}

	public String getNumOfClasses() {
		WebElement inputField = driver.findElement(noOfClassesField);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String value = (String) js.executeScript("return arguments[0].value;", inputField);

		System.out.println("Value of the disabled input field: " + value);
		return value;
	}

	public void getDatePicker() {
		driver.findElement(datePicker).click();

	}

	public boolean isBatchNameFieldReq() {
		return driver.findElement(batchNameFieldReqMsg).isDisplayed();
	}

	public boolean isClassTopicFieldReqMsg() {
		return driver.findElement(classTopicFieldReqMsg).isDisplayed();
	}

	public boolean isClassDatesReqMsg() {
		return driver.findElement(classDatesReqMsg).isDisplayed();
	}

	public boolean isStaffNameReqMsg() {
		return driver.findElement(staffNameReqMsg).isDisplayed();
	}

	public boolean isStatusReqMsg() {
		return driver.findElement(statusReqMsg).isDisplayed();
	}

}
