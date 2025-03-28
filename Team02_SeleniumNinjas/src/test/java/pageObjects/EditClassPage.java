package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditClassPage {
	private WebDriver driver;

	public EditClassPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// button[@icon='pi pi-pencil']
	// button[contains(@class, 'p-button-rounded') and contains(@class,
	// 'p-button-success')]
	private By editBtn = By.xpath("//button[@icon='pi pi-pencil']");
	private By classDetails = By.xpath("//span[starts-with(@id, 'pr_id')]");
	private By batchNameField = By.xpath("//input[@placeholder='Select a Batch Name']");
	private By classTopicField = By.xpath("//input[@id='classTopic']");
	private By classDescriptionField = By.xpath("//input[@id='classDescription']");
	private By classDatesField = By.xpath("//input[@id='icon']");
	private By noOfClassesField = By.xpath("//input[@id='classNo']");
	private By staffNameField = By.xpath("//input[@placeholder='Select a Staff Name']");
	private By activeStatusRadio = By.xpath("//input[@type='radio' and @id='Active']");
	private By inactiveStatusRadio = By.xpath("//input[@type='radio' and @id='Inactive']");
	private By notesField = By.xpath("//input[@id='classNotes']");
	private By recordingField = By.xpath("//input[@id='classRecordingPath']");
	// Edit button
	private By classTableRows = By.xpath("//tbody[@class='p-datatable-tbody']/tr"); // Rows in table
	private String classTopicColumnXpath = "./td[3]"; // Assuming Class Topic is in the 3rd column
	private String editButtonXpath = "./td[last()]/div/span/button[contains(@class, 'p-button-success')]"; // Edit
																											// button

//	public void getEditClass() {
//		driver.findElement(editBtn).click();
//	}

	public String getClassDetailsOfEditClass() throws InterruptedException {
		Thread.sleep(2000);

		String classDetailsOfEdit = driver.findElement(classDetails).getText();
		System.out.println(classDetailsOfEdit);
		return classDetailsOfEdit;

	}
	
	// Method to search for the class by topic and click edit
    public boolean selectClassByTopicAndEdit(String classTopic) throws InterruptedException {
        List<WebElement> rows = driver.findElements(classTableRows);
        for (WebElement row : rows) {
            WebElement topicCell = row.findElement(By.xpath(classTopicColumnXpath));
            if (topicCell.getText().trim().equalsIgnoreCase(classTopic)) {
                WebElement editButton = row.findElement(By.xpath(editButtonXpath));
                if (editButton != null) {
        			// Scroll into view before clicking
        			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
        			Thread.sleep(500); // Small wait for UI stability

        			// Click using JavaScriptExecutor
        			((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
        		}
                //editButton.click();
                return true;
            }
        }
        return false; // Class not found
    }
    
    public boolean isBatchNameFieldDisabled() {
        WebElement batchName = driver.findElement(batchNameField); // Update locator as needed
        return !batchName.isEnabled();
    }

}
