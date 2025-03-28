package stepDefinitions;

import org.testng.Assert;

import driverFactory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ClassDetailsPage;

public class ClassDetailsSteps {
	
	private ClassDetailsPage CDPage= new ClassDetailsPage(DriverFactory.getDriver());

	@When("Admin clicks on {string} under the class menu bar")
	public void admin_clicks_on_under_the_class_menu_bar(String string) {
	    CDPage.getAddClasspopup();
	}
	
	@Then("Admin should see a popup  with  heading {string}")
	public void admin_should_see_a_popup_with_heading(String string) throws InterruptedException {
		String cdText= CDPage.getCD();
	    System.out.println("Class detail modal window title "+cdText);
		Assert.assertTrue(cdText.equalsIgnoreCase("Class Details"));
	}

}
