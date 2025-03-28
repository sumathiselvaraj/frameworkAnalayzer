package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driverFactory.DriverFactory;
import utils.ExcelReader.BatchRecordsStatus;
import utils.CommonFunctions;
import utils.ExcelReader;
import utils.ExcelReader.batchRecords;


public class batchModule {
	public WebDriver driver;
	public CommonFunctions comMethod;
	public WebDriverWait wait;
	public ExcelReader readbatch = new ExcelReader();
	 public static String Firstbatchname;
	 public static String Secondbatchname;
	 public static String AddedBatchname;
	 

	// Locators
	By batchButton = By.xpath("//*[@class='ng-star-inserted']/button[3]");
	By pageName = By.xpath("//*[@class='ng-star-inserted']/button[3]/span[1]");
	By batchLabel = By.xpath("//*[@class='mat-card mat-focus-indicator']/mat-card-title/div[1]");
	By B_Delete = By.xpath("//*[@class='mat-card-title']/div[2]/div/button");
	By navigateStatus = By.xpath("//*[@class='p-paginator-pages ng-star-inserted']");
	By editiconui = By.xpath("//*[@class='p-button-icon pi pi-pencil']");
	By deleteiconui = By.xpath("//*[@class='p-button-icon pi pi-trash']");
	By sorticon = By.xpath("//*[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']");
	By checkboxui = By.xpath("//*[@class='p-datatable-tbody']/tr/td[1]/p-tablecheckbox");
	By headerpath = By.xpath("//*[@class='p-datatable-thead']/tr/th");
	By checkboxvalidate = By.xpath("//*[@class='p-datatable-thead']/tr/th/p-tableheadercheckbox");
	// By Addbatch = By.xpath("//*[@class='mat-menu-content
	// ng-tns-c225-3']");c230-53'
	By Addbatch = By.xpath("//button[@role='menuitem']");
	//// button[normalize-space()='Add New Batch']
	By closebatch = By.xpath("//*[@class='p-dialog-header-close-icon ng-tns-c81-9 pi pi-times']");
	By Addbatchheader = By.xpath("//*[@id='pr_id_3-label']");

	// Add batch locators
	By batchprogramName = By.xpath("//*[@role='button']");
	By batchprogmatch = By.xpath("//*[@id='programName']/div/input");
	By batchprogdropdown = By.xpath("//*[@id='programName']/div/div[2]/span");
	By batchproglist = By.xpath("//*[contains(@class,'p-dropdown-items ng-tns-c88')]/p-dropdownitem/li/span");

	// p-dropdown-items-wrapper ng-tns-c88-66
	// *[contains(@class,'p-toast-summary ng-tns-c91')]
	By batchBName1 = By.xpath("//*[@id='batchProg']");
	By batchBName2 = By.xpath("//*[@pattern='^[0-9]{0,5}$']");
	By batchDesc = By.xpath("//*[@id='batchDescription']");
	// By batchstatusActive= By.xpath("//*[@ng-reflect-input-id='ACTIVE']");
	By batchstatusActive = By.xpath("(//p-radiobutton[@name='category']//div[@class='p-radiobutton-box'])[1]");
	By batchstatusInactive = By.xpath("(//p-radiobutton[@name='category']//div[@class='p-radiobutton-box'])[2]");
	By batchNoOfClass = By.xpath("//*[@id='batchNoOfClasses']");
	By batchCancelbutton = By.xpath("//*[@label='Cancel']']");
	By batchSavebutton = By.xpath("//*[@label='Save']");

	By BatchTxt = By.xpath("//*[text()='Batch']");
	By Searchbatch = By.xpath("//*[@id='filterGlobal']");
	By reacordtxt = By.xpath("//*[@class='p-datatable-tbody']/tr[1]/td[2]");

	By SuccessMSg = By.xpath("//*[contains(@class,'p-toast-message-content ng-tns-c91')]");
	By SuccessMSgclose = By.xpath("//*[contains(@class,'p-toast-icon-close-icon pi pi-times na-†ns-c91')]");
	By Session = By
			.xpath("//*[@class='cdk-overlay-backdrop cdk-overlay-transparent-backdrop cdk-overlay-backdrop-showing']");

	public batchModule(WebDriver driver) {
		this.driver = driver;
		this.comMethod = new CommonFunctions(DriverFactory.getDriver(), 20);

	}

	public void landbatchpage() {
		WebElement batchbutton = comMethod.elementToBeClickable(batchButton);
		batchbutton.click();
		Actions actions = new Actions(driver);
		actions.moveByOffset(200, 300).click().perform();

	}

	public String checkBatchpage() {
		// landbatchpage();
		String label = driver.findElement(pageName).getText();
		return label;

	}

	public String currentUrl() {
		return driver.getCurrentUrl();
	}

	By ttitle = By.xpath("//span[text()=' LMS - Learning Management System ']");

	public String title() {
		
		return driver.findElement(ttitle).getText();
	}

	public String batchheading() {

		WebElement batchlabel = comMethod.visibilityOfElementLocated(batchLabel);
		String label = batchlabel.getText();
		return label;

	}

	public boolean H_deleteIcon() {
		
	       WebElement deleteicon = comMethod.visibilityOfElementLocated(B_Delete);
			return deleteicon.isDisplayed();
		
		
		

	}

	public boolean pageNavigate() {
		WebElement navigateState = comMethod.visibilityOfElementLocated(navigateStatus);
		return navigateState.isEnabled();
	}

	public boolean editIcon() {
		List<WebElement> editiconUI = comMethod.visibilityOfAllElementsLocated(editiconui);
		System.out.println("Size edit : " + editiconUI.size());
		boolean editiconstatus = comMethod.tableRowUIElements(editiconUI);
		return editiconstatus;
	}

	public boolean deleteIcon()

	{
		List<WebElement> deleteiconUI = comMethod.visibilityOfAllElementsLocated(deleteiconui);
		boolean deleteiconstatus = comMethod.tableRowUIElements(deleteiconUI);
		return deleteiconstatus;
	}

	public boolean checkBox()

	{
		List<WebElement> checkboxUI = comMethod.visibilityOfAllElementsLocated(checkboxui);
		boolean checkboxstatus = comMethod.tableRowUIElements(checkboxUI);
		return checkboxstatus;
	}

	public boolean tableHeaders()

	{

		List<WebElement> headerelements = comMethod.visibilityOfAllElementsLocated(headerpath);
		List<String> Headers = Arrays.asList("Batch Name", "Batch Description", "Batch Status", "No Of Classes",
				"Program Name", "Edit / Delete");
		boolean headerresult = comMethod.tableHeaders(headerelements, Headers);
		return headerresult;
	}

	// method for AddBatchclick
	public void batchaddclick() {
		WebElement batchh = comMethod.presenceOfElementLocated(batchButton);
		batchh.click();
		WebElement Add = comMethod.presenceOfElementLocated(Addbatch);

		Add.click();

	}
	// method for closeaddBatchclick

	public void batchaddclose() {

		WebElement Addclose = comMethod.presenceOfElementLocated(closebatch);

		Addclose.click();

	}

	public boolean checkboxvalidate() {

		// driver.findElement(batchButton).click();

		// batchaddclick();
		// batchaddclose();

		WebElement checkAll = comMethod.elementToBeClickable(checkboxvalidate);

		checkAll.click();
		List<WebElement> checkboxUI = comMethod.visibilityOfAllElementsLocated(checkboxui);
		for (WebElement e : checkboxUI) {
			comMethod.visibilityOfAllElementsLocated(checkboxui);

			if (!e.isSelected()) {
				System.out.println("checkbox  is not checked in row No: ");
				return false;
			}
		}
		return true;
	}

	public boolean sortIcon()

	{

		List<WebElement> sorticonUI = comMethod.visibilityOfAllElementsLocated(sorticon);
		boolean sorticonstatus = comMethod.tableRowUIElements(sorticonUI);
		return sorticonstatus;
	}

	// ----------------Add New BatchUI--------------//

	public String addbatchUI() {

		landbatchpage();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement Add = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@role='menuitem']")));
//		WebElement Add = comMethod.presenceOfElementLocated(Addbatch);

		String name = Add.getText();

		return name;

	}

	public String addbatchpopup() {
		batchaddclick();
		WebElement batchheader = comMethod.presenceOfElementLocated(Addbatchheader);
		String bname = batchheader.getText();
		System.out.println(bname);
		batchaddclose();
		return bname;

	}

	// -----------------------------Add New Batch----------------------//

	public BatchRecordsStatus addbatch(String Pname, String testCase) throws IOException {
		
		List<batchRecords> batchRecordlist = new ArrayList<>();
		batchRecordlist = readbatch.readExcel_LMSPrograms(Pname);
		
		try {
			if (batchRecordlist != null) {

				for (batchRecords record : batchRecordlist) {
					if(record.getTestCase().equalsIgnoreCase(testCase)) {
						landbatchpage();
						batchaddclick();
						BatchRecordsStatus batchStatus = new BatchRecordsStatus();
						
						WebElement batch_progmatch = comMethod.presenceOfElementLocated(batchprogmatch);
						batch_progmatch.clear();
						WebElement batchprog_dropdown = comMethod.presenceOfElementLocated(batchprogdropdown);
						batchprog_dropdown.click();
						List<WebElement> BatchpageProgList = comMethod.presenceOfElementsLocated(batchproglist);
						System.out.println("BatchpageProgList Size: " + BatchpageProgList.size());
						// Iterate through options and add text to the list
						for (int i = 0; i <= BatchpageProgList.size() - 1; i++) {
							String B_ProgName = BatchpageProgList.get(i).getText();
							// System.out.println("BatchpageProgList: "+B_ProgName);
							if (B_ProgName.equalsIgnoreCase(record.getProgramName()))
							{
								 Firstbatchname=record.getProgramName();
								BatchpageProgList.get(i).click();
								break;
							}

						}

						// Log.info("Batch Extracted from the excel");
						WebElement batchB_Name2 = comMethod.presenceOfElementLocated(batchBName2);
						// batchB_Name2.click();
						batchB_Name2.sendKeys(record.getBatchName());
						 Secondbatchname=record.getBatchName();
						// Log.info("batch no is entering");
						WebElement batch_Desc = comMethod.visibilityOfElementLocated(batchDesc);
						batch_Desc.sendKeys(record.getDescription());
						WebElement batchstatus_Active = comMethod.elementToBeClickable(batchstatusActive);
						batchstatus_Active.click();
						WebElement batch_NoOfClass = comMethod.presenceOfElementLocated(batchNoOfClass);
						batch_NoOfClass.sendKeys(record.getNoOfClass());
						// batchprogramName.sendKeys("ChatBotTest");
						WebElement batch_Savebutton = comMethod.elementToBeClickable(batchSavebutton);
						batch_Savebutton.click();
						
						
						// Handle alerts 
						
						String Succes_msg = handlealert();
						String finalbname = Firstbatchname + Secondbatchname;
						batchStatus.setBatchName(finalbname);
						setAddedBatchname(finalbname);
						batchStatus.setMessage(Succes_msg);
						return batchStatus;
					}
				}
			}
		} 
		
		catch (Exception e) {
			System.out.println("Error processing batch records");
		}
		
		return null;	
	}
	
	By missingMandatory=By.xpath("//*[@class='p-invalid ng-star-inserted']");
	public boolean formFieldInlineError() {
		List<WebElement> missingMandatoryFields = comMethod.visibilityOfAllElementsLocated(missingMandatory);
		
	    if(!missingMandatoryFields.isEmpty()) {
	    	return true;
	    } else {
	    	return false;
	    }
	   
	}
	By BatchNamereq=By.xpath("//small[text()='Batch Name is required.']");
	public String getInlineErrorMessageBatchName()
	{	
		String bnamereq=driver.findElement(BatchNamereq).getText() ;
		
		return bnamereq;
			
	}
	By ProgramNamereq=By.xpath("//small[text()='Program Name is required.']");

	public String getInlineErrorMessageProgramName()
	{	
		String pnamereq=driver.findElement(ProgramNamereq).getText() ;
		
		return pnamereq;
			
	}
	By Descreq=By.xpath("//small[text()='Batch Description is required.']");

	public String getInlineErrorMessageDesc()
	{	
		String pnamereq=driver.findElement(Descreq).getText() ;
		
		return pnamereq;
			
	}
	
	By NumberClassreq=By.xpath("//small[text()='Number of classes is required.']");

	public String getInlineErrorMessageClassNum()
	{	
		String pnamereq = driver.findElement(NumberClassreq).getText() ;
		
		return pnamereq;
			
	}
	

	public static String getAddedBatchname() {
		return AddedBatchname;
	}

	public static void setAddedBatchname(String addedBatchname) {
		AddedBatchname = addedBatchname;
	}
	
	
	public String handlealert() {
		By toastLocator = By.cssSelector("div.p-toast-message-content[role='alert']");

		// Wait for the toast to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));

		// Retrieve and print the toast message text
		String toastMessage = toastElement.getText();
		System.out.println("Toast Message: " + toastMessage);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
		return toastMessage;
		
		
	}
	
	
	
	
	//-----------------------------Delete Batch Validation---------------------//

	
	By deletelist=By.xpath("//*[@class='p-button-rounded p-button-danger p-button p-component p-button-icon-only']");
	public boolean deleteIconclicked()

	{
		List<WebElement> deleteiconUI = comMethod.visibilityOfAllElementsLocated(deletelist);

		// Check if the list is not empty before interacting
		if (!deleteiconUI.isEmpty()) {
		    deleteiconUI.get(0).click(); // Click the first delete icon in the row
		    return true; // Return true if clicked successfully
		} else {
		    System.out.println("No delete icons found.");
		    return false; // Return false if no delete icons are found
		}
	}
	
	By checkdeletelist=By.xpath("//div[@class='p-checkbox p-component']");
	public boolean firstcheckboxdeleteIconclicked()

	{
		List<WebElement> checkdeleteiconUI = comMethod.visibilityOfAllElementsLocated(checkdeletelist);

		// Check if the list is not empty before interacting
		if (!checkdeleteiconUI.isEmpty()) {
			checkdeleteiconUI.get(1).click(); // Click the first checkox delete icon in the row
		    return true; // Return true if clicked successfully
		} else {
		    System.out.println("No delete icons found.");
		    return false; // Return false if no delete icons are found
		}
	}
	
	By multiplecheckdeletelist=By.xpath("//*[@class='p-datatable-thead']/tr/th/p-tableheadercheckbox");

	public boolean multiplecheckboxdeleteIconclicked()

	{
		List<WebElement> multicheckdeleteiconUI = comMethod.visibilityOfAllElementsLocated(multiplecheckdeletelist);
		// Check if the list is not empty before interacting
		if (!multicheckdeleteiconUI.isEmpty()) {

			multicheckdeleteiconUI.get(0).click(); 
		    return true; // Return true if clicked successfully
		} else {
		    System.out.println("No delete icons found.");
		    return false; // Return false if no delete icons are found
		}
	}
	
	By multidelete=By.xpath("//button[@class='p-button-danger p-button p-component p-button-icon-only']");
	public boolean multideleteclick() {
		
		WebElement mdelete=comMethod.presenceOfElementLocated(multidelete);
		mdelete.click();
		return true;
		
	}
	By yesmultideleteConfirm=By.xpath("//span[text()='Yes']");

	public void multiConfirmDeleteYes()
	{
		WebElement yesmulconfirm = comMethod.presenceOfElementLocated(yesmultideleteConfirm);
		yesmulconfirm.click();
		
	}
	
	
	By deleteConfirm=By.xpath("//*[@class='p-dialog-header-icons ng-tns-c118-11']//span");
	
	public String confirmDeletePopup() {
		
		
		WebElement popheader = comMethod.presenceOfElementLocated(deleteConfirm);
		String bname = popheader.getText();
		System.out.println(bname);
		return bname;

	}
	By yesdeleteConfirm=By.xpath("//span[@class='p-button-icon p-button-icon-left pi pi-check']");

	public void ConfirmDeleteYes()
	{
		WebElement yesconfirm = comMethod.presenceOfElementLocated(yesdeleteConfirm);
		yesconfirm.click();
		
	}
	By nodeleteConfirm=By.xpath("//span[@class='p-button-icon p-button-icon-left pi pi-times']");

	public void ConfirmDeleteNo()
	{
		WebElement noconfirm = comMethod.presenceOfElementLocated(nodeleteConfirm);
		noconfirm.click();
			
	}
	By deleteClose=By.xpath("//*[@class='p-dialog-header-icons ng-tns-c118-11']//span");

	public void deleteClose()
	{
		WebElement dclose = comMethod.presenceOfElementLocated(deleteClose);
		dclose.click();
		
	}
	
	
	public String Deletedhandlealert() {
		By deletetoastLocator = By.xpath("//div[text()='Successful']");
				//div[@class='p-toast-summary ng-tns-c20-39']");
	//p-toast-message-text ng-tns-c20-25 ng-star-inserted
		// Wait for the toast to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(deletetoastLocator));

		// Retrieve and print the toast message text
		String toastMessage1 = toastElement.getText();
		System.out.println("Toast Message: " + toastMessage1);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(deletetoastLocator));
		return toastMessage1;
	}
	
	//----------------------------Search Text Box-------------->
	
	By SearchTxt=By.xpath("//*[@id='filterGlobal']");
	By SearchtxtinGrid=By.xpath("//*[@class='p-datatable-tbody']/tr/td[2]");
	
	public void searchtext()
   	{

   	   JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("document.elementFromPoint(0, 0).click();");
   		WebElement Searchtext=comMethod.presenceOfElementLocated(SearchTxt);
   		Searchtext.click();
   		Searchtext.sendKeys(getAddedBatchname());

   	}
   	
   		public boolean searchtextvalidation() {	
   		    try {
   		        // Retry mechanism to handle StaleElementReferenceException
   		        for (int attempt = 0; attempt < 3; attempt++) {  // Retry up to 3 times
   		            try {
   		                List<WebElement> searchingrid = comMethod.visibilityOfAllElementsLocated(SearchtxtinGrid);
   		                
   		                if (searchingrid == null || searchingrid.isEmpty()) {
   		                    System.out.println("Grid is null or empty");
   		                    return false;
   		                }

   		                String expectedText = getAddedBatchname();
   		                if (expectedText == null) {
   		                    System.out.println("Property 'batchsearchtext' is null");
   		                    return false;
   		                }

   		                for (WebElement e : searchingrid) {
   		                    String text = e.getText().trim();  // Trim to avoid whitespace mismatches
   		                    
   		                    if (text.equalsIgnoreCase(expectedText)) {
   		                        System.out.println("Batch found in grid: " + text);
   		                        return true; // Success
   		                    }
   		                }

   		                System.out.println("Searched text not found in grid");
   		                return false; // If no match found

   		            } catch (StaleElementReferenceException e) {
   		                System.out.println("Retry due to StaleElementReferenceException (Attempt " + (attempt + 1) + ")");
   		            }
   		        }
   		    } catch (Exception e) {
   		        System.out.println("Unexpected exception: " + e.getMessage());
   		    }
   		    
   		    return false;  // Default return if all attempts fail
   		}
   	
//-------------------------------Edit Batch Record---------------------//

	public void FillFormData(String sheetName, String testCase)  {
		
		List<Map<String, String>> exlData = readbatch.getData("./src/test/resources/TestData/TestData2.xlsx", sheetName);
		// System.out.println(exlData.size());

		for (Map<String, String> data : exlData) {
			
			if (data.get("TestCase").equalsIgnoreCase(testCase)) {
				System.out.println("Test Data Found: " + data);

				// Enter values from Excel into UI fields
				WebElement batch_progmatch = comMethod.presenceOfElementLocated(batchprogmatch);
				batch_progmatch.clear();
				WebElement batchprog_dropdown = comMethod.presenceOfElementLocated(batchprogdropdown);
				batchprog_dropdown.click();
				List<WebElement> BatchpageProgList = comMethod.presenceOfElementsLocated(batchproglist);
				System.out.println("BatchpageProgList Size: " + BatchpageProgList.size());
				// Iterate through options and add text to the list
				for (int i = 0; i <= BatchpageProgList.size() - 1; i++) {
					String B_ProgName = BatchpageProgList.get(i).getText();
					// System.out.println("BatchpageProgList: "+B_ProgName);
					if (B_ProgName.equalsIgnoreCase(((batchRecords) data).getProgramName()))
					{
						 Firstbatchname=((batchRecords) data).getProgramName();
						BatchpageProgList.get(i).click();
						break;
					}

				}

				// Log.info("Batch Extracted from the excel");
				WebElement batchB_Name2 = comMethod.presenceOfElementLocated(batchBName2);
				// batchB_Name2.click();
				batchB_Name2.sendKeys(((batchRecords) data).getBatchName());
				 Secondbatchname=((batchRecords) data).getBatchName();
				// Log.info("batch no is entering");
				WebElement batch_Desc = comMethod.visibilityOfElementLocated(batchDesc);
				batch_Desc.sendKeys(((batchRecords) data).getDescription());
				WebElement batchstatus_Active = comMethod.elementToBeClickable(batchstatusActive);
				batchstatus_Active.click();
				WebElement batch_NoOfClass = comMethod.presenceOfElementLocated(batchNoOfClass);
				batch_NoOfClass.sendKeys(((batchRecords) data).getNoOfClass());
				// batchprogramName.sendKeys("ChatBotTest");
				WebElement batch_Savebutton = comMethod.elementToBeClickable(batchSavebutton);
				batch_Savebutton.click();


			}
		}
	}}