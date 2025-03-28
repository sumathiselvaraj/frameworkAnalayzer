package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Constants;

public class ManageClassPage {

	private WebDriver driver;

	public ManageClassPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='username']")
	private WebElement username;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;

	@FindBy(xpath = "//button[@id='login']")
	private WebElement login;

	// @FindBy(xpath = "//div[@class='mat-select-arrow-wrapper ng-tns-c161-3']")
	@FindBy(xpath = "//div[@id='mat-select-value-1']")
	private WebElement role;

	@FindBy(xpath = "//span[normalize-space()='Admin']")
	private WebElement admin;

	@FindBy(xpath = "//span[normalize-space()='Class']")
	private WebElement classbtn;

	@FindBy(xpath = "//div[normalize-space()='Manage Class']")
	private WebElement ManageClass;

	@FindBy(xpath = "//thead[@class='p-datatable-thead']//tr[@class='ng-star-inserted']/th")
	private List<WebElement> headerColumns;

	@FindBy(xpath = "//input[@id='filterGlobal']")
	private WebElement searchBtn;

	@FindBy(xpath = "//th[normalize-space()='Batch Name']")
	private WebElement classBatchName;

	@FindBy(xpath = "//th[normalize-space()='Class Topic']")
	private WebElement classTopic;

	@FindBy(xpath = "//th[normalize-space()='Class Description']")
	private WebElement classDescription;

	@FindBy(xpath = "//th[normalize-space()='Status']")
	private WebElement status;

	@FindBy(xpath = "//th[normalize-space()='Class Date']")
	private WebElement classDate;

	@FindBy(xpath = "//th[normalize-space()='Staff Name']")
	private WebElement staffName;

	@FindBy(xpath = "//th[normalize-space()='Edit / Delete']")
	private WebElement editDeleteBtn;

	// pop up window
	@FindBy(xpath = "//div[@class='p-dialog-header ng-tns-c81-15 ng-star-inserted']")
	private WebElement classDetailsOfEditPopup;
	
	@FindBy(xpath = "//button[@class='p-button-rounded p-button-danger p-button p-component ng-star-inserted']")
	private WebElement cancelBtnOfEdit;
	
	@FindBy(xpath = "//button[@class='p-button-rounded p-button-success p-button p-component p-button-icon-only']")
	private WebElement classEditBtn;
	
	@FindBy(xpath = "//button[text()='2']")
	private WebElement nextPage;

	@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-double-right']")
	private WebElement DoubleArrowIcon;

	// div[@class='p-datatable-footer ng-star-inserted']
	// div[@class='p-d-flex p-ai-center p-jc-between ng-star-inserted']

	@FindBy(xpath = "//div[@class='p-d-flex p-ai-center p-jc-between ng-star-inserted']")
	private WebElement screenFooterText;

	// div[@class='p-paginator-bottom p-paginator p-component ng-star-inserted']

	@FindBy(xpath = "//span[@class='p-paginator-icon pi pi-angle-right']")
	private WebElement SingleRightArrowbutton;

	@FindBy(xpath = "//div[@class='p-d-flex p-ai-center p-jc-between ng-star-inserted']")
	private WebElement footerTxt;

	@FindBy(xpath = "//span[@class='p-paginator-current ng-star-inserted']")
	private WebElement Pgtntxt;

	// By nextPage = By.xpath("//button[text()='2']");
	// By DoubleArrowIcon = By.xpath("//span[@class='p-paginator-icon pi
	// pi-angle-double-right']") ;

	public void getloginUrl() throws InterruptedException {
		driver.get(Constants.baseUrl);
		username.sendKeys("sdetnumpyninja@gmail.com");
		password.sendKeys("Feb@2025");
		role.click();
		admin.click();
		login.click();

	}

	public String getManageClassPageTitle() {
		return ManageClass.getText();
	}

	public void Classbutton() {

		classbtn.click();
	}

	public boolean verifyGridHeaders(List<String> expectedHeaders) {
		for (int i = 1; i < expectedHeaders.size(); i++) {
			if (!headerColumns.get(i).getText().equals(expectedHeaders.get(i))) {
				return false;
			}
		}
		return true;
	}

	// Searchbox validation
	public boolean srchBox() throws InterruptedException {
		Thread.sleep(2000);
		boolean issearch = searchBtn.isDisplayed();
		return issearch;

	}

	public String srchtxt() {
		return searchBtn.getAttribute("placeholder");
	}

	public boolean getPaginationtxt() {
		return Pgtntxt.isDisplayed();
	}

	public boolean getFootertxt() {
		return footerTxt.isDisplayed();
	}

	// sorting validation methods
	public void clickColumnHeaderToSort(String columnName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement columnHeader = driver.findElement(By.xpath("//th[@psortablecolumn='" + columnName + "']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", columnHeader);
	}

	// Retrieves the data from the specified column
	public List<String> getColumnData(String column) {
		List<String> columnData = new ArrayList<>();
		String xpath = String.format("//tbody/tr/td[count(//th[@psortablecolumn='%s']/preceding-sibling::th) + 1]",
				column);
		List<WebElement> cells = driver.findElements(By.xpath(xpath));
		for (WebElement cell : cells) {
			columnData.add(cell.getText());
		}
		return columnData;
	}	
	

	public List<String> getAllDataFromAllPages(String column) {
	    List<String> allData = new ArrayList<>();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    while (true) {
	        // Collect data from the current page
	        List<String> currentPageData = getColumnData(column);
	        allData.addAll(currentPageData);

	        // Check if there is a next page
	        WebElement nextButton = driver.findElement(By.cssSelector("button.p-paginator-next"));
	        if (nextButton.getAttribute("class").contains("p-disabled")) {
	            // If the next button is disabled, we are on the last page
	            break;
	        }

	        //Handle the overlay (if present)
	        handleOverlay();

	        //Click the next page button using JavaScriptExecutor
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);

	        
	    }

	    goToFirstPage();
	    return allData;
	}
	
	private void goToFirstPage() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Locate the "First Page" button
	    WebElement firstPageButton = driver.findElement(By.cssSelector("button.p-paginator-first"));

	    // Check if the "First Page" button is disabled
	    if (!firstPageButton.getAttribute("class").contains("p-disabled")) {
	        // Handle the overlay (if present)
	        handleOverlay();

	        // Click the "First Page" button using JavaScriptExecutor
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstPageButton);
	        
	    }
	}

	private void handleOverlay() {
	    try {
	        // Wait for the overlay to disappear
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.cdk-overlay-backdrop")));
	    } catch (Exception e) {
	        // If the overlay is still present, log the issue and proceed
	        System.out.println("Overlay not found or already disappeared.");
	    }
	}


}
