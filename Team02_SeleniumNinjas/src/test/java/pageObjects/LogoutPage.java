package pageObjects;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogoutPage {
	WebDriver driver;
	private By Logout=By.xpath("//span[@class='mat-button-wrapper' and text()='Logout']");
	public LogoutPage(WebDriver driver) {
		this.driver=driver;
	}
	public void click_logout() {
		WebElement logout=driver.findElement(Logout);
		logout.click();
	}
	
	public void navigate_driver_back() {
		String currentUrl = driver.getCurrentUrl();
		driver.navigate().back();
		String endurl = driver.getCurrentUrl();
		System.out.println("beforeurl:"+currentUrl+"  Afterurl:"+endurl);
	}
	public void navigate_url(String string) {
		driver.navigate().to(string);
	}public String get_current_url() {
		String url=driver.getCurrentUrl();
		return url;
	}

}