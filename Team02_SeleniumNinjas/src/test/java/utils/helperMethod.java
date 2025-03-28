package utils;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import driverFactory.DriverFactory;
import pageObjects.LoginPage;

public class helperMethod {
	
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	private LoginPage login = new LoginPage(DriverFactory.getDriver()) ;
	
	public helperMethod(WebDriver driver ) {
		this.driver = driver;
		}

	public void loginDetails() {	
		configReader=new ConfigReader();
		prop =configReader.init_prop();
		String username1=prop.getProperty("user");
	    String password1=prop.getProperty("password");
	    login.enterCredentials(username1, password1);
		login.roleSelectionClick();
		login.Submitforlogin();
		
		}
}
