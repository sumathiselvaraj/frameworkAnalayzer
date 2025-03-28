package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddClassPage;
import utils.ClassDataHelper;
import utils.ExcelReader;
import utils.LoggerLoad;

public class AddClassSteps {

	private AddClassPage ANCPage = new AddClassPage(DriverFactory.getDriver());
	ClassDataHelper classDataHelper = new ClassDataHelper();
	ExcelReader exlReader = new ExcelReader();
	private String newClassTopic = "";	
	WebDriver driver = DriverFactory.getDriver();

	@When("Admin enters all mandatory field values with valid data and clicks save button {string} and {string}")
	public void admin_enters_all_mandatory_field_values_with_valid_data_and_clicks_save_button_and(String testCase,
			String sheetName) throws InterruptedException {
		newClassTopic = classDataHelper.fillFormData(sheetName, testCase, true);
		ANCPage.clickSaveButton();
	}

	@Then("Admin should see new class details is added in the data table")
	public void admin_should_see_new_class_details_is_added_in_the_data_table() {
		LoggerLoad.info("New class successfully added!");
		String message = ANCPage.toastMsg();
		LoggerLoad.info(message);
		ClassDataHelper.setCreatedClassTopic(newClassTopic);
		LoggerLoad.info("Admin should see Class details");
		Assert.assertTrue(message.contains("Success"));
	}

	@When("Admin enters all mandatory field values with invalid data and clicks save button {string} and {string}")
	public void admin_enters_all_mandatory_field_values_with_invalid_data_and_clicks_save_button_and(String testCase,
			String sheetName) throws InterruptedException {
		classDataHelper.fillFormData(sheetName, testCase, true);
		ANCPage.clickSaveButton();

	}

	@Then("Error message should appear in alert")
	public void error_message_should_appear_in_alert() {
		LoggerLoad.info("Admin gets Error message");
		String message = ANCPage.toastMsg();
		LoggerLoad.info(message);
		Assert.assertTrue(message.contains("Error"));
	}

	@When("Admin selects class date in date picker {string} and {string}")
	public void admin_selects_class_date_in_date_picker_and(String testCase, String sheetName)
			throws InterruptedException {
		classDataHelper.fillFormData(sheetName, testCase, true);
	}

	@Then("Admin should see no of class value is added automatically")
	public void admin_should_see_no_of_class_value_is_added_automatically() {
		String value = ANCPage.getNumOfClasses();
		LoggerLoad.info(value);
		assertEquals(value, classDataHelper.getExpectedResult());

	}

	@When("Admin clicks date picker")
	public void admin_clicks_date_picker() {
		ANCPage.getDatePicker();
	}

	@Then("Admin should see weekends dates are disabled to select")
	public void admin_should_see_weekends_dates_are_disabled_to_select() {
		// Locate all the date elements in the calendar
		List<WebElement> weekendDates = driver.findElements(By.xpath("//td[contains(@class, 'p-disabled')]"));

		// Verify that Saturday (Sa) and Sunday (Su) are disabled
		for (WebElement weekend : weekendDates) {
			String dateText = weekend.getText();
			LoggerLoad.info("weekends dates are disabled to select");
			Assert.assertTrue(weekend.getAttribute("class").contains("p-disabled"),
					"Weekend date " + dateText + " is not disabled!");
		}
	}

	@When("Admin skips adding value in the mandatory fields and enters a value in the optional field {string} and {string}")
	public void admin_skips_adding_value_in_the_mandatory_fields_and_enters_a_value_in_the_optional_field_and(
			String testCase, String sheetName) throws InterruptedException {
		classDataHelper.fillFormData(sheetName, testCase, true);
		ANCPage.clickSaveButton();
	}

	@Then("Admin should see an error message below mandatory field {string}, it should be highlighted in red color")
	public void admin_should_see_an_error_message_below_mandatory_field_it_should_be_highlighted_in_red_color(
			String fieldName) {
		if (fieldName == "BatchName") {
			Assert.assertTrue(ANCPage.isBatchNameFieldReq());
			LoggerLoad.info("should see an error message below mandatory field BatchName");
		} else if (fieldName == "ClassTopic") {
			Assert.assertTrue(ANCPage.isClassTopicFieldReqMsg());
			LoggerLoad.info("should see an error message below mandatory field ClassTopic");
		} else if (fieldName == "ClassDates") {
			Assert.assertTrue(ANCPage.isClassDatesReqMsg());
			LoggerLoad.info("should see an error message below mandatory field ClassDates ");
		} else if (fieldName == "StaffName") {
			Assert.assertTrue(ANCPage.isStaffNameReqMsg());
			LoggerLoad.info("should see an error message below mandatory field StaffName");
		} else if (fieldName == "Status") {
			Assert.assertTrue(ANCPage.isStatusReqMsg());
			LoggerLoad.info("should see an error message below mandatory field Status");
		}
		

	}

}
