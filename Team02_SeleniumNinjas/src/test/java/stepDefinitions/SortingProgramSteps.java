package stepDefinitions;
	
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DeleteProgram;
import pageObjects.EditProgram;
import pageObjects.SortingProgram;
import java.util.List;

	public class SortingProgramSteps {
	
	    SortingProgram sortingPg = new SortingProgram(DriverFactory.getDriver());
	    List<String> beforeSort, afterSort;

	

	    @When("Admin clicks on Arrow next to Program Name")
	    public void admin_clicks_arrow_program_name() {
	        beforeSort = sortingPg.getColumnData();
	        sortingPg.clickSortArrow();
	        afterSort = sortingPg.getColumnData();
	        
	   
	    }
	    
//	    @When("Admin clicks on Arrow next to {string}")
//	    public void admin_clicks_arrow_program_name(String columnName) {
//	        // Capture the column data before sorting
//	        beforeSort = sortingPg.getColumnData(columnName);
//	        
//	        // Click the sort arrow for the specific column
//	        sortingPg.clickSortArrow(columnName);
//	        
//	        // Capture the column data after sorting
//	        afterSort = sortingPg.getColumnData(columnName);
//	    }
//   

	 @Then("Admin sees the Program Name is sorted in Ascending order or Descending order")
	    public void admin_sees_the_program_name_is_sorted_in_Ascending_order_or_descending_order() {
	
		 
		 boolean isSortedAsc = sortingPg.isSortedAscending(afterSort);
	        boolean isSortedDesc = sortingPg.isSortedDescending(afterSort);

	        Assert.assertTrue(isSortedAsc || isSortedDesc, "Sorting failed for Program Name");
	    }

	    @When("Admin clicks on Arrow next to Program Description")
	    public void admin_clicks_arrow_program_description() {
	
	        beforeSort = sortingPg.getDescriptionColumnData();
	        sortingPg.clickDescriptionSortArrow();
	        afterSort = sortingPg.getDescriptionColumnData();
	    }

	    @Then("Admin sees the Program Description is sorted in Ascending order or Descending order")
	    public void admin_sees_the_program_Description_is_sorted_in_Ascending_order_or_descending_order(){
	    	  boolean isSortedAsc = sortingPg.isSortedAscending(afterSort);
	          boolean isSortedDesc = sortingPg.isSortedDescending(afterSort);

	          Assert.assertTrue(isSortedAsc || isSortedDesc, "Sorting failed for Program Description");
	    	
	    }

	    @When("Admin clicks on Arrow next to Program Status")
	    public void admin_clicks_arrow_program_status() {
	        beforeSort = sortingPg.getStatusColumnData();
	        sortingPg.clickStatusSortArrow();
	        afterSort = sortingPg.getStatusColumnData();
	    }

	    @Then("Admin sees the Program Status is sorted in Ascending order or Descending order")
	    public void admin_sees_the_program_status_is_sorted_in_ascending_order_or_descending_order() {
	           boolean isSortedAsc = sortingPg.isSortedAscending(afterSort);
	    	   boolean isSortedDesc = sortingPg.isSortedDescending(afterSort);

	    	        Assert.assertTrue(isSortedAsc || isSortedDesc, "Sorting failed for Program Status");
	    	    }
	}



