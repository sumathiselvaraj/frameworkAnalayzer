package stepDefinitions;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.batchModule;

public class BatchPageValidation {
	
	private batchModule page;
	private SoftAssert S_Assert = new SoftAssert();
	private LoginPage lpage;

	public BatchPageValidation() {
        page = new batchModule(DriverFactory.getDriver()); // Get the driver from driverManager
        lpage = new LoginPage(DriverFactory.getDriver());
		

    }
	
	
	@Given("Admin in Batch Home Page")
	public void admin_in_Batch_Home_Page() {
	}
	
	@When("Admin in batch page")
	public void admin_in_batch_page() {
		page.landbatchpage();
		String batchlabel=page.checkBatchpage();
		System.out.println(batchlabel);
		Assert.assertEquals(batchlabel, "Batch");

	}

	@Then("Validate Title in Batch Page")
	public void validate_title_in_batch_page() {
		String title=page.title();
		System.out.println(title);
		S_Assert.assertEquals(title, "LMS-Learning Management System", "Title mismatch!");
		

	}

	@Then("Validate Heading in Batch Page")
	public void validate_heading_in_batch_page() {
		String batchlabel=page.batchheading();
		S_Assert.assertEquals(batchlabel, "Manage Batch", "Heading mismatch!");
		
	}
	@Then("Validate disabled Delete Icon under the header in the Batch Page")
	public void validate_disabled_under_the_header_in_the_batch_page() {
		
    boolean deleteicon=page.H_deleteIcon();
    S_Assert.assertTrue(deleteicon);
	 
	}

	@Then("Validate pagination in the Batch Page")
	public void validate_pagination_in_the_batch_page() {
		boolean navigatestate=page.pageNavigate();
		S_Assert.assertTrue(navigatestate);
		
	}

	@Then("Validate edit icon in each data rows")
	public void validate_edit_icon_in_each_data_rows() {
		
		boolean editicon=page.editIcon();
		S_Assert.assertTrue(editicon);
	    
	}

	@Then("validate delete icon in each data rows")
	public void validate_delete_icon_in_each_data_rows() {
		boolean deleteicon=page.deleteIcon();
		S_Assert.assertTrue(deleteicon);
	    
	}

	@Then("validate checkbox in each data rows")
	public void validate_checkbox_in_each_data_rows() {
		boolean checkbox=page.checkBox();
		S_Assert.assertTrue(checkbox);
	    
	}

	@Then("Validate batch Datatable headers")
	public void validate_batch_datatable_headers() {
		boolean headerresult=page.tableHeaders();
		S_Assert.assertTrue(headerresult);
	    
	}

	@Then("Validate Checkbox in the Datatable header row")
	public void validate_Checkbox_in_the_datatable_header_row() {
		
		boolean checkAll=page.checkboxvalidate();
		S_Assert.assertTrue(checkAll);
	    
	}

	@Then("Validate sort icon next to all the datatable header")
	public void validate_sort_icon_next_to_all_the_datatable_header() {
	boolean sorticon=page.sortIcon();
	S_Assert.assertTrue(sorticon);
	}


}
