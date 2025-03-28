package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DeleteClassPage {
	private WebDriver driver;

	public DeleteClassPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Delete button
	private By classTableRows = By.xpath("//tbody[@class='p-datatable-tbody']/tr"); // Rows in table
	private By classTopicColumnXpath = By.xpath("./td[3]"); // Assuming Class Topic is in the 3rd column
	private By deleteButtonXpath = By.xpath("./td[last()]/div/span/button[contains(@class, 'p-button-danger')]");
	private By deleteConfirmTitle = By.xpath("//span[contains(@class,'p-dialog-title')]");
	private By yesButton = By.xpath("//button[contains(@class, 'p-confirm-dialog-accept')]");
	private By noButton = By.xpath("//button[contains(@class, 'p-confirm-dialog-reject')]");

	// alert
	private By toastmsg = By.xpath("//div[@role='alert']");

	// alert
	public String toastMsg() {
		return driver.findElement(toastmsg).getText();
	}

	public boolean selectClassByTopicForDelete(String classTopic) throws InterruptedException {
		List<WebElement> rows = driver.findElements(classTableRows);
		for (WebElement row : rows) {
			WebElement topicCell = row.findElement(classTopicColumnXpath);
			if (topicCell.getText().trim().equalsIgnoreCase(classTopic)) {
				WebElement editButton = row.findElement(deleteButtonXpath);
				if (editButton != null) {
					// Scroll into view before clicking
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editButton);
					Thread.sleep(500); // Small wait for UI stability

					// Click using JavaScriptExecutor
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
				}
				// editButton.click();
				return true;
			}
		}
		return false; // Class not found
	}

	public boolean isDeleteConfirmTitle() {
		try {
			return driver.findElement(deleteConfirmTitle).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDeleteConfirmNo() {
		return driver.findElement(yesButton).isDisplayed();
	}

	public boolean isDeleteConfirmYes() {
		return driver.findElement(noButton).isDisplayed();
	}

	public void ClickYesButton() {
		WebElement eleYes= driver.findElement(yesButton);
		if (eleYes != null) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", eleYes);
		}
	}

	public void ClickNoButton() {
		WebElement eleNo= driver.findElement(noButton);
		if (eleNo != null) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", eleNo);
		}
	}

}
