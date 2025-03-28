package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClassDetailsPage {
	
private WebDriver driver;

	
	public ClassDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}
	
	@FindBy(xpath = "//span[starts-with(@id, 'pr_id')]")
	private WebElement classDetails;
	
	@FindBy(xpath="//button[normalize-space()='Add New Class']")
	private WebElement addNewCls;
	
	//private By classDetails = By.id("pr_id_38-label");
	
//	public void AddClassPage() {
//		Alert alert = driver.switchTo().alert();
//		String alertText = alert.getText();
//		alert.accept();
//	}
	
	public void getAddClasspopup() {
		addNewCls.click();	
		
	}
	
	public String getCD() throws InterruptedException
	{
		Thread.sleep(2000);
		System.out.println(classDetails.getText());
		return classDetails.getText();
		
		
	}


}
