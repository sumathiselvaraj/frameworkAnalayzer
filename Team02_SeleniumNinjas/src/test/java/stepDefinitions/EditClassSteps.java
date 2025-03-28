package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddClassPage;
import pageObjects.EditClassPage;
import utils.ClassDataHelper;
import utils.ExcelReader;
import utils.LoggerLoad;

public class EditClassSteps {
	private EditClassPage ECPage = new EditClassPage(DriverFactory.getDriver());
	private AddClassPage ANCPage = new AddClassPage(DriverFactory.getDriver());
	private ClassDataHelper classDataHelper = new ClassDataHelper();
	ExcelReader exlReader = new ExcelReader();
	boolean isClassFound = false;
	String expectedResult;

	@When("Admin clicks on Edit button for Class Topic in manage class")
	public void admin_clicks_on_edit_button_for_class_topic_in_manage_class() throws InterruptedException {
		String classTopic = classDataHelper.getCreatedClassTopic();
		isClassFound = ECPage.selectClassByTopicAndEdit(classTopic);
		Assert.assertTrue(isClassFound, "Class with topic '" + classTopic + "' not found in the grid.");
	}

	@Then("A new Edit pop up with class details appears")
	public void a_new_edit_pop_up_with_class_details_appears() throws InterruptedException {

		if (isClassFound) {
			String cdText = ECPage.getClassDetailsOfEditClass();
			LoggerLoad.info("Edit Class title " + cdText);
			Assert.assertTrue(cdText.equalsIgnoreCase("Class Details"));
		}
	}

	@Then("Admin should see batch name field is disabled in class edit pop up")
	public void admin_should_see_batch_name_field_is_disabled_in_class_edit_pop_up() {
		boolean isDisabled = ECPage.isBatchNameFieldDisabled();
		Assert.assertTrue(isDisabled, "Batch Name field is not disabled");
	}

	@When("Admin enters all mandatory field values with valid data in Edit class and clicks save button {string} and {string}")
	public void admin_enters_all_mandatory_field_values_with_valid_data_in_edit_class_and_clicks_save_button_and(
			String testCase, String sheetName) throws InterruptedException {
		classDataHelper.fillFormData(sheetName, testCase, false);
		ANCPage.clickSaveButton();
	}

	@Then("Admin gets message class updated Successfully and see the updated values in data table")
	public void admin_gets_message_class_updated_successfully_and_see_the_updated_values_in_data_table() {
		LoggerLoad.info("class updated successfully!");
		String message = ANCPage.toastMsg();
		LoggerLoad.info(message);
		Assert.assertTrue(message.contains("Success"));
	}

	@When("Admin enters all  field values with invalid data in Edit class and clicks save button {string} and {string}")
	public void admin_enters_all_field_values_with_invalid_data_in_edit_class_and_clicks_save_button_and(
			String testCase, String sheetName) throws InterruptedException {
		classDataHelper.fillFormData(sheetName, testCase, false);
		ANCPage.clickSaveButton();

	}

	@Then("Admin should get error message")
	public void admin_should_get_error_message() {
		LoggerLoad.info("Admin gets Error message");
		String message = ANCPage.toastMsg();
		LoggerLoad.info(message);
		Assert.assertTrue(message.contains("Error"));
	}

	@When("Admin enters field values with invalid data of special characters and numbers in Edit class and clicks save button {string} and {string}")
	public void admin_enters_field_values_with_invalid_data_of_special_characters_and_numbers_in_edit_class_and_clicks_save_button_and(
			String testCase, String sheetName) throws InterruptedException {
		classDataHelper.fillFormData(sheetName, testCase, false);
		ANCPage.clickSaveButton();
	}

	@Then("Admin should get error message for Edit Class")
	public void admin_should_get_error_message_for_edit_class() {
		LoggerLoad.info("Admin gets Error message");
		String message = ANCPage.toastMsg();
		LoggerLoad.info(message);
		Assert.assertTrue(message.contains("Error"));
	}

}
