package pageObjects;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;




public class ProgramPage1 {
	
	
	private WebDriver driver;
	

	private By programbutton = By.xpath("//button[@id='program']");
	private By maintitlecheck= By.xpath("//span[contains(text(),' LMS - Learning Management System ')]");
	private By logoutcheck = By.xpath("//span[contains(text(),'Logout')]");
	private By navbarButtonsLocator = By.xpath("//mat-toolbar//button//span[@class='mat-button-wrapper']");
	private By manageProgramHeadingLocator = By.xpath("//mat-card-title//div[contains(text(), 'Manage Program')]");
	private By AddnewProgrambuttoncheck = By.xpath("//button[contains(text(),'Add New Program')]");
	private By programNameLocator =By.xpath("//th[normalize-space()='Program Name']");
	private By  programDescriptionLocator=By.xpath("//th[normalize-space()='Program Description']");
	private By programStatusLocator =By.xpath("//th[normalize-space()='Program Status']");
	private By edtdlt =By.xpath("//th[normalize-space()='Edit / Delete']");
	private By programRows = By.xpath("//table[@role='grid']//tbody//tr");
	private By maindeletetrash = By.xpath("//button[contains(@class, 'p-button-danger') and @disabled]");
	private By searchboxtextlocator = By.xpath("//input[@placeholder='Search...']");
	private By checkboxonHeaderlocator = By.xpath("//div[@class='p-checkbox-box']");
	private By checkboxlistlocator = By.xpath("//tr[@class='ng-star-inserted']//div[@role='checkbox' and @aria-checked='false']");
	private By sortArrowIconsLocator = By.xpath("//th[not(contains(text(), 'Edit / Delete'))]//p-sorticon//span[contains(@class, 'pi-sort-alt')]");
	private By editButtonsLocator = By.xpath("//button[@id='editProgram']");
	private By deleteButtonsLocator = By.xpath("//button[@id='deleteProgram']");
	private By paginationTextLocator = By.xpath("//span[contains(@class, 'p-paginator-current')]");
	private By firstPageIconLocator = By.xpath("//button[contains(@class, 'p-paginator-first')]");
	private By prevPageIconLocator = By.xpath("//button[contains(@class, 'p-paginator-prev')]");
	private By nextPageIconLocator = By.xpath("//button[contains(@class, 'p-paginator-next')]");
	private By lastPageIconLocator = By.xpath("//button[contains(@class, 'p-paginator-last')]");	
	private By footerlocator = By.xpath("//div[contains(@class, 'p-d-flex p-ai-center p-jc-between ng-star-inserted')]");
	//div[contains(@class, 'p-d-flex p-ai-center p-jc-between ng-star-inserted')]
	
	 
	
	
		
	public ProgramPage1(WebDriver driver) {
		this.driver = driver;
	}
	
	
	 public void Programbuttonclick() {
		   driver.findElement(programbutton).click(); 
		  
		   }
	 
	public String getProgramPageTitle() {
			return driver.getTitle();
		}

	public String getMaintitletext() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement headingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(maintitlecheck));

        
        return headingElement.getText();
		
	}
	
	 public String getLogoutText() {
	        // Find the element and get its text
	        return driver.findElement(logoutcheck).getText();
	    }
	 
	 public List<String> getNavbarPageNames() {
	        List<WebElement> buttonElements = driver.findElements(navbarButtonsLocator);
	        List<String> pageNames = new ArrayList<>();

	        for (WebElement element : buttonElements) {
	            pageNames.add(element.getText());
	        }

	        return pageNames;
	    }
	

	    // Method to get the text of the "Manage Program" heading
	    public String getManageProgramHeadingText() {
	    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	  WebElement heading = wait.until(ExpectedConditions.presenceOfElementLocated(manageProgramHeadingLocator));
//	        return driver.findElement(manageProgramHeadingLocator).getText();
	    	  return heading.getText();
	    }
	    
	    public String AddnewProgrambuttoncheck() {
	    	return driver.findElement(AddnewProgrambuttoncheck).getText();
	    }
	
	    
	    public String getProgramNameHeaderText() {
	        return driver.findElement(programNameLocator).getText();
	    }
	    
	    public String getProgramDescriptionText() {
	        return driver.findElement(programDescriptionLocator).getText();
	    }
	    public String getProgramStatusText() {
	        return driver.findElement(programStatusLocator).getText();
	    }
	    
	    public String getEditdeletetxt() {
	        return driver.findElement(edtdlt).getText();
	    }
	    public int getNumberOfPrograms() {
	        return driver.findElements(programRows).size();
	    }
	    
	    // Method to get the program name for a specific row
	    public String getProgramName(int index) {
	        return driver.findElements(programRows).get(index).findElement(By.xpath(".//td[2]")).getText();
	    }
	    public String getProgramDescription(int index) {
	        return driver.findElements(programRows).get(index).findElement(By.xpath(".//td[3]")).getText();
	    }
	    public String getProgramStatus(int index) {
	        return driver.findElements(programRows).get(index).findElement(By.xpath(".//td[4]")).getText();
	    }
	    
	    public boolean isMainDeleteButtonDisabled() {
	        WebElement deleteButton = driver.findElement(maindeletetrash);
	        return !deleteButton.isEnabled(); 
	    }
	    
	    public boolean getsearchboxtextlocator() {
	    	WebElement searchBar = driver.findElement(searchboxtextlocator);
            return searchBar.isDisplayed();
	    	
	    }
	    
	    public String getSearchBarPlaceholderText() {
	        WebElement searchBar = driver.findElement(searchboxtextlocator);
	        return searchBar.getAttribute("placeholder");
	    }
	    
	    public boolean getcheckboxonHeaderlocator() {
	    	WebElement searchBar = driver.findElement(checkboxonHeaderlocator);
            return searchBar.isDisplayed();
	    	
	    }
	    public boolean isCheckboxUnchecked() {
	        WebElement checkbox = driver.findElement(checkboxonHeaderlocator);
	        return !checkbox.getAttribute("class").contains("p-highlight"); // Assuming "p-highlight" indicates a checked state
	    }
	    
	    public int getcheckboxlistsize() {
	        return driver.findElements(checkboxlistlocator).size();
	    }
	    
	    public boolean areAllCheckboxesUnchecked() {
	        List<WebElement> checkboxes = driver.findElements(checkboxlistlocator);
	        for (WebElement checkbox : checkboxes) {
	            if (!checkbox.getAttribute("aria-checked").equals("false")) {
	                return false; 
	            }
	        }
	        return true; 
	    }
	    public boolean areAllSortArrowIconsDisplayed() {
	        List<WebElement> sortArrowIcons = driver.findElements(sortArrowIconsLocator);
	        for (WebElement icon : sortArrowIcons) {
	            if (!icon.isDisplayed()) {
	                return false; // If any icon is not displayed, return false
	            }
	        }
	        return true; // All icons are displayed
	    }
	    
	    public boolean areAllEditButtonsDisplayed() {
	        List<WebElement> editButtons = driver.findElements(editButtonsLocator);
	        for (WebElement button : editButtons) {
	            if (!button.isDisplayed()) {
	                return false; // If any Edit button is not displayed, return false
	            }
	        }
	        return true; // All Edit buttons are displayed
	    }
	    
	    public boolean areAllDeleteButtonsDisplayed() {
	        List<WebElement> deleteButtons = driver.findElements(deleteButtonsLocator);
	        for (WebElement button : deleteButtons) {
	            if (!button.isDisplayed()) {
	                return false; // If any Edit button is not displayed, return false
	            }
	        }
	        return true; // All Edit buttons are displayed
	    }
	    
	    public String getPaginationText() {
	        return driver.findElement(paginationTextLocator).getText();
	    }

	    
	    public boolean isFirstPageIconDisplayed() {
	        return driver.findElement(firstPageIconLocator).isDisplayed();
	    }

	  
	    public boolean isPrevPageIconDisplayed() {
	        return driver.findElement(prevPageIconLocator).isDisplayed();
	    }

	
	    public boolean isNextPageIconDisplayed() {
	        return driver.findElement(nextPageIconLocator).isDisplayed();
	    }

	   
	    public boolean isLastPageIconDisplayed() {
	        return driver.findElement(lastPageIconLocator).isDisplayed();
	    }
	    
	    //Footer text
	    public String getFooterText() {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
	        WebElement footerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(footerlocator));
	        
	        String footerText = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", footerElement);
	        System.out.println("Footer Text: " + footerText); // Debugging: Print the footer text
	        return footerText;	        
       
	    }
	    
	    public int extractNumberOfPrograms(String footerText) {	        
	        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("In total there are (\\d+) programs");
	        java.util.regex.Matcher matcher = pattern.matcher(footerText);

	        if (matcher.find()) {
	            // Extract the number (z) from the matched group
	            return Integer.parseInt(matcher.group(1));
	        } else {
	            throw new RuntimeException("Footer text does not match the expected format: " + footerText);
	        }
	    }
}
