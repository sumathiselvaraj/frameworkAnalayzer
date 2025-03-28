
package pageObjects;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import driverFactory.DriverFactory;
import utils.Constants;
import utils.CommonFunctions;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class LoginPage {

	private WebDriver driver;

	private By username = By.xpath("//input[@id='username']");
	private By password = By.xpath("//input[@id='password']");
	private By login = By.xpath("//button[@id='login']");
	private By roleselection = By.xpath("//div[@id=\"mat-select-value-1\"]");
    private By adminselectionfromrole = By.xpath("//*[@id=\"mat-option-0\"]/span") ;
    private By loginPageImage = By.xpath("//div[@class=\"image-container\"]/img");
    private By logo=By.xpath("//img[@class='images']");
    private By loginFieldsPosition = By.xpath("//div[@class=\"signin-content\"]/mat-card");
    private By inputfield=By.cssSelector("mat-card-content.mat-card-content");
    private By app_name=By.xpath("//p[text()='Please login to LMS application']");
    private By no_texts=By.cssSelector(".cdk-text-field-autofill-monitored");
    private By user=By.xpath("//label[@id=\"mat-form-field-label-1\"]");
    private By pwd=By.xpath("//label[@id=\"mat-form-field-label-3\"]");
    private By Login_btn=By.xpath("//span[text()='Login']");
    private By dropdwon_arrow =By.xpath("//div[@class=\"mat-select-arrow-wrapper ng-tns-c161-3\"]");
    
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void getloginUrl() {
		driver.get(Constants.baseUrl);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	
	public void enterCredentials(String usrname, String passwrd) {
		driver.findElement(username).sendKeys(usrname);
		driver.findElement(password).sendKeys(passwrd);
	}
	
	
   public void roleSelectionClick() {
	   driver.findElement(roleselection).click(); 
	   driver.findElement(adminselectionfromrole).click();
	   }
	 
	public void Submitforlogin() {
		driver.findElement(login).click();
	}
	
	public void getInvalidLoginUrl() {
		driver.get(Constants.invalidbaseUrl);
	}
	
	public String getloginimage() {
		WebElement e = driver.findElement(loginPageImage);
		String elementsource = e.getAttribute("src");
		return elementsource;
		
	}
	
	
	public boolean dis_logo() {
		WebElement Logo=driver.findElement(logo);
		System.out.println("This is innerhtml:"+Logo.getText());
		return Logo.isDisplayed();	
	}
	
	public String dis_appName() {
		WebElement appName=driver.findElement(app_name);
		
		return appName.getText();
	}
	public int num_List_Text() {
		List<WebElement>textboxes=driver.findElements(no_texts);
		System.out.println(textboxes.size());
		return textboxes.size();
	}
	
	public String getloginfieldposition() {
		WebElement e = driver.findElement(loginFieldsPosition);
		String elementLocation = e.getCssValue("text-allign");
		return elementLocation;
		
	}
	
	public String image_to_text_converter() throws TesseractException, IOException{

	       WebElement imageElement = driver.findElement(By.cssSelector("img.images"));

	       // Capture the image as a file
	       File imageFile = imageElement.getScreenshotAs(OutputType.FILE);
	       // Define the destination file path
	       File savedImage = new File("./src/test/resources/testData/image.png");
	       ImageIO.write(ImageIO.read(imageFile), "png", savedImage);
	       // Set up Tesseract OCR
	       Tesseract tesseract = new Tesseract();
	       tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");

	       // Extract text from the image
	       String extractedText = tesseract.doOCR(savedImage);

	       System.out.println("Extracted Text: " + extractedText);

	       // Close the browser
	       return extractedText;
	   }
	public String image_to_astrix_converter() throws IOException, TesseractException {

	      WebElement imageElement = driver.findElement(inputfield);

	      // Capture the image as a file
	      File imageFile = imageElement.getScreenshotAs(OutputType.FILE);
	      // Define the destination file path
	      File savedImage = new File("./src/test/resources/testData/image.png");
	      ImageIO.write(ImageIO.read(imageFile), "png", savedImage);
	      // Set up Tesseract OCR
	      Tesseract tesseract = new Tesseract();
	      tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");

	      // Extract text from the image
	      String extractedText = tesseract.doOCR(savedImage);

	      System.out.println("Extracted Text: " + extractedText);

	      // Close the browser
	      return extractedText;
	  }

	public boolean dis_user() {
		WebElement Userwithstar=driver.findElement(user);
		return Userwithstar.isDisplayed();
}
	
	public boolean dis_pwd() {
		WebElement pwdwithstar=driver.findElement(pwd);
		return pwdwithstar.isDisplayed();
		
	}
	
	//To align inputField---------------------------------
	public boolean align_inputfield() {
		WebElement inputField=driver.findElement(inputfield);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Long viewportWidth = (Long) js.executeScript("return window.innerWidth");
		Long viewportHeight=(Long) js.executeScript("return window.innerHeight");
		double viewportcentX=viewportWidth/2;
		double viewportcenterY=viewportHeight/2;
		int elementX = inputField.getLocation().getX();
		int elementY=inputField.getLocation().getY();
		int elementWidth = inputField.getSize().getWidth();
		int elementHeight = inputField.getSize().getHeight();

		// Calculate the center of the element
		double elementCenterX = elementX + (elementWidth / 2.0);
		double elementCenterY = elementY + (elementHeight / 2.0);

		// Check if the element is approximately centered (within a tolerance)
		double tolerance = 10; // Adjust tolerance as necessary
	    boolean isHorizontallyCentered = Math.abs(viewportcentX - elementCenterX) < tolerance;
	    boolean isVerticallyCentered = Math.abs(viewportcenterY - elementCenterY) < tolerance;
	    boolean isCentered = isHorizontallyCentered || isVerticallyCentered;

	    // Output the result
	    System.out.println("Is the input field centered? " + isCentered);

		return isCentered;
		
	}
	
	public boolean dis_login_btn() {
		WebElement Login_button=driver.findElement(Login_btn);
		return Login_button.isDisplayed();
		
	}
	
	public boolean dis_dropdownForRole(){
		WebElement Login_button=driver.findElement(dropdwon_arrow);
		return Login_button.isDisplayed();
	}
	
	public void text_alignment_Loginbtn() {
		WebElement element=driver.findElement(Login_btn);
		// Update with the correct selector
		textAlign_Loginbtn(element);}

	public void textAlign_Loginbtn(WebElement string) {
	    String script = "return window.getComputedStyle(arguments[0]).getPropertyValue('text-align');";

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    String textAlign = (String) js.executeScript(script, string);

	    System.out.println("Text Alignment: " + textAlign+"  Stringname:"+string.getText());
	}
	public String color_of_Admin() {
		
		WebElement element=driver.findElement(user);
		String script = "return window.getComputedStyle(arguments[0]).getPropertyValue('color');";

	    // Execute the JavaScript and get the 'color' property value
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    String color = (String) js.executeScript(script, element);

	    // Print the 'color' property value
	    System.out.println("Element Color: " + color);
	    String colorName = getColorNameFromRGBA(color);
	    System.out.println("Element Color Name: " + colorName);
	    return colorName;
	}
	
	public String color_of_password() {
		
		WebElement element=driver.findElement(pwd);
		String script = "return window.getComputedStyle(arguments[0]).getPropertyValue('color');";

	    // Execute the JavaScript and get the 'color' property value
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    String color = (String) js.executeScript(script, element);

	    // Print the 'color' property value
	    System.out.println("Element Color: " + color);
	    String colorName = getColorNameFromRGBA(color);
	    System.out.println("Element Color Name: " + colorName);
	    return colorName;
	}
	
	
	//To get color
	private static String getColorNameFromRGBA(String rgba) {
	    Map<String, String> colorMap = createColorMap();

	    // Look up the color name in the map
	    return colorMap.getOrDefault(rgba, "Unknown Color");
	}
	private static Map<String, String> createColorMap() {
	    Map<String, String> colorMap = new HashMap<>();
	    colorMap.put("rgba(0, 0, 0, 0.87)", "gray");
	    colorMap.put("#000000", "Black");
	    colorMap.put("#FFFFFF", "White");
	    colorMap.put("#FF0000", "Red");
	    colorMap.put("#00FF00", "Lime");
	    colorMap.put("#0000FF", "Blue");
	    colorMap.put("#FFFF00", "Yellow");
	    colorMap.put("#FFA500", "Orange");
	    colorMap.put("#A52A2A", "Brown");
	    colorMap.put("#800080", "Purple");
	    colorMap.put("#008080", "Teal");
	    // Adding gray color
	    
	    return colorMap;
	}

	public void EnterThroughKeyboardnLogin(String uname,String passwd) {
		Actions action=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement Username=driver.findElement(user);
		WebElement Password=driver.findElement(password);
		WebElement roleselection1=driver.findElement((By) roleselection);
		WebElement adminselectionfromrole1=driver.findElement((By) adminselectionfromrole);
		WebElement login_btn=driver.findElement(Login_btn);
		System.out.println(uname+"  "+passwd);
		action.moveToElement(Username).click();
		action.moveToElement(Username).sendKeys(uname).perform();
		action.moveToElement(Password).sendKeys(Keys.TAB);
		action.moveToElement(Password).sendKeys(passwd).perform();
		action.moveToElement(roleselection1).click();
		action.moveToElement(adminselectionfromrole1).click();
		action.moveToElement(login_btn).sendKeys(Keys.ENTER).perform();
	}
	public void ClickThroughMouse(String uname,String passwd) {
		Actions action=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement Username=driver.findElement(user);
		WebElement Password=driver.findElement(password);
		WebElement roleselection1=driver.findElement((By) roleselection);
		WebElement adminselectionfromrole1=driver.findElement((By) adminselectionfromrole);
		WebElement login_btn=driver.findElement(Login_btn);
		action.moveToElement(Username).click();
		action.moveToElement(Username).sendKeys(uname).perform();
		action.moveToElement(Password).click();
		action.moveToElement(Password).sendKeys(passwd).perform();
		System.out.println(uname+"  "+passwd);
		action.moveToElement(roleselection1).click();
		action.moveToElement(adminselectionfromrole1).click();
		action.moveToElement(login_btn).click().perform();
	}
	
}
