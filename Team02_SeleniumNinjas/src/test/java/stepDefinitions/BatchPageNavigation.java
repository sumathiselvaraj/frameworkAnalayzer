package stepDefinitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.batchModule;

public class BatchPageNavigation {

	private static final Logger log = LogManager.getLogger(BatchPageNavigation .class);
	private batchModule page;
    
	private LoginPage lpage;
	
	String batchlabel;
	
	public BatchPageNavigation() {
		page = new batchModule(DriverFactory.getDriver()); // Get the driver from driverManager
		lpage=new LoginPage(DriverFactory.getDriver());
    }
    
	
	@Given ("Admin is on the home Page")
	public void admin_is_on_the_home_Page() {
		
//		lpage.getloginUrl();
//		lpage.getPageTitle();
//		
	}
	
	@When("Admin clicks Batch tab on top right corner of the LMS page")
	public void admin_clicks_batch_tab_on_top_right_corner_of_the_lms_page() {
	
		page.landbatchpage();
		batchlabel=page.checkBatchpage();
		System.out.println(batchlabel);
	 }

	@Then("Verify the Batch page is displayed")
	public void verify_the_batch_page_is_displayed() {
		 
		 Assert.assertEquals(batchlabel, "Batch");
	   	  String title=page.currentUrl();
	   	  System.out.println("batch url: "+title);
	   	  Assert.assertTrue(title.contains("batch"));

	}


}
