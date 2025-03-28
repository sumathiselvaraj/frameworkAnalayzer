package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import driverFactory.DriverFactory;

import pageObjects.LoginPage;
import utils.CommonFunctions;
import utils.Constants;
import utils.ExcelReader;
import utils.CommonFunctions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.sourceforge.tess4j.TesseractException;
public class LoginPageSteps {
	
	private static WebDriver driver;
	private LoginPage loginpg = new LoginPage(DriverFactory.getDriver());
	private CommonFunctions commonfunc = new CommonFunctions(DriverFactory.getDriver(), 0);
	
	//###### Background
	@When("Admin gives the correct LMS portal URL")
	public void admin_gives_the_correct_lms_portal_url() {
		loginpg.getloginUrl();
	}

	@Then("Admin should land on the login page")
	public void admin_should_land_on_the_login_page() {
		String Expected_title="LMS";
		String actual_title=loginpg.getPageTitle();
		Assert.assertEquals(Expected_title, actual_title);
	}

	//###################### TC1#####
	@Given("Admin is in LoginPage")
	public void admin_is_in_login_page() {
	   
	}

	@When("Admin enters valid user and password with select role as Admin.")
	public void admin_enters_valid_user_and_password_with_select_role_as_admin() {
		loginpg.getloginUrl();
		commonfunc.loginDetails();
	}

	@Then("Admin should be landing to home page")
	public void admin_should_be_landing_to_home_page() {
		String actualTitle = loginpg.getPageTitle();
		Assert.assertEquals("LMS", actualTitle.trim());
	}
		
	//##########TC2
	@When("Admin gives the invalid LMS portal URL")
	public void admin_gives_the_invalid_lms_portal_url() {
	   loginpg.getInvalidLoginUrl();
	}

	@Then("Admin should recieve {int} page not found error.")
	public void admin_should_recieve_page_not_found_error(Integer errorcode) throws IOException, InterruptedException {
		
		//Assert.assertTrue(loginpg.getPageTitle().contains("Application Error"),
		//		"'This site can’t be reached");
		commonfunc.httpcodestatus();;
	}
	
	//TC3
	@Given("Admin launch the browser")
	public void admin_launch_the_browser() {
	   
	}

	@When("Admin gives the correct LMS portal URL to check for broken links.")
	public void admin_gives_the_correct_lms_portal_url_to_check_for_broken_links() {
		loginpg.getloginUrl();
	}

	@Then("HTTP response >= {int},Then the link is broken")
	public void http_response_then_the_link_is_broken(Integer int1) throws IOException, InterruptedException {
	   loginpg.Submitforlogin();
	   System.out.println(commonfunc.httpcodestatus());
	   Assert.assertEquals(commonfunc.httpcodestatus(),200);
	}

//##########TC5

@Then("Admin should see correct spellings in all fields")
public void admin_should_see_correct_spellings_in_all_fields() throws TesseractException, IOException {
	String LMSapp_text=loginpg.image_to_text_converter();
	String LMSapp_User_pwd=loginpg.image_to_astrix_converter();
	System.out.println(loginpg.image_to_text_converter());
	if(LMSapp_text.contains("LMS - Learning Management System")) {
		Assert.assertTrue(true);
	}
	else {
		Assert.assertTrue(false);
	}
	if(LMSapp_text.contains("NumpyNinja")) {
		Assert.assertTrue(true);			
		}
	else {
		Assert.assertTrue(false);
	}
	if(LMSapp_User_pwd.contains("User")) {
	Assert.assertTrue(true);}
	else {
		Assert.assertTrue(false);
	}
	if(LMSapp_User_pwd.contains("Password")) {
		Assert.assertTrue(true);}
		else {
			Assert.assertTrue(false);
		}
	
   
}

	
	//############TC4
	
	@Then("Admin should see expected logo image in login page")
	public void admin_should_see_expected_logo_image_in_login_page() {
		Assert.assertTrue(loginpg.getloginimage().trim().contains("assets/img/LMS-logo.jpg"),
				"Expected image source is displayed in loginpage");
		Assert.assertTrue(loginpg.dis_logo());
	}
	
	//##############TC5
	@Then("Admin should see input and loginbutton are in center of login page")
	public void admin_should_see_input_and_loginbutton_are_in_center_of_login_page() {

		Assert.assertTrue(loginpg.getloginfieldposition().trim().contains("center"),
				"Input and login button are not center alligned");
	}
	
	//###############TC6
	@Given("Admin is on signin page")
	public void admin_is_on_signin_page() {
	   
	}

	@When("Admin enters sheet {string} and {int} from excel file,selects role from the dropdown and clicks login button .")
	public void admin_enters_sheet_and_from_excel_file_selects_role_from_the_dropdown_and_clicks_login_button(String sheetname, Integer rownum) {
		commonfunc.enterExcelDataForLogin(sheetname, rownum);
	}

	@Then("Admin should see the Error message.")
	public void admin_should_see_the_error_message() {
	    
	}

//#######################################################################
	
	@Then("Admin should see  LMS - Learning Management System")
	public void admin_should_see_lms_learning_management_system() throws TesseractException, IOException {
		String LMS_text=loginpg.image_to_text_converter();
		System.out.println(loginpg.image_to_text_converter());
		if(LMS_text.contains("LMS - Learning Management System")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
			}
	}

	@Then("Admin should see company name below the app name")
	public void admin_should_see_company_name_below_the_app_name() throws TesseractException, IOException {
		String numpyninja_text=loginpg.image_to_text_converter();
		System.out.println(loginpg.image_to_text_converter());
		if(numpyninja_text.contains("NumpyNinja")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
			
		}  
	}

	@Then("Admin should see {string}")
	public void admin_should_see(String disp_name) {
		Assert.assertEquals(disp_name, loginpg.dis_appName());
	}

	@Then("Admin should see {int} text field")
	public void admin_should_see_text_field(Integer no_of_textfields) {
		Assert.assertEquals(loginpg.num_List_Text(),no_of_textfields); 
	}

	@Then("Admin should  see {string} in the first text field")
	public void admin_should_see_in_the_first_text_field(String user) {
		Assert.assertTrue(loginpg.dis_user(),user);
	}
	

	@Then("Admin should see field mandatory asterik symbol next to Admin text")
	public void admin_should_see_field_mandatory_asterik_symbol_next_to_admin_text() throws IOException, TesseractException {
		String astrsym_admin=loginpg.image_to_astrix_converter();
		System.out.println(astrsym_admin);
		if(astrsym_admin.contains("User*")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}

	@Then("Admin should {string} in the second text field")
	public void admin_should_in_the_second_text_field(String string) {
		Assert.assertTrue(loginpg.dis_pwd()); 
	}

	@Then("Admin should see * symbol next to password text")
	public void admin_should_see_symbol_next_to_password_text() throws IOException, TesseractException {
		String astrsym_admin=loginpg.image_to_astrix_converter();
		System.out.println(astrsym_admin);
		if(astrsym_admin.contains("User*")) {
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}

	@Then("Admin should see input field on the centre of the page")
	public void admin_should_see_input_field_on_the_centre_of_the_page() {
		//loginpg.align_inputfield();
		Assert.assertTrue(loginpg.align_inputfield());
	}
	
	@Then("Admin should see drop down to select the particular role")
	public void admin_should_see_drop_down_to_select_the_particular_role() {
		Assert.assertTrue(loginpg.dis_dropdownForRole());
	}

	@Then("Admin should see login button")
	public void admin_should_see_login_button() {
		Assert.assertTrue(loginpg.dis_login_btn());
	}

	@Then("Admin should see login button on the centre of the page")
	public void admin_should_see_login_button_on_the_centre_of_the_page() {
		loginpg.text_alignment_Loginbtn(); 
	}

	@Then("Admin should see Admin in gray color")
	public void admin_should_see_admin_in_gray_color() {
		String actual=loginpg.color_of_Admin();
		   Assert.assertEquals(actual, "gray");
	}

	@Then("Admin should see password in gray color")
	public void admin_should_see_password_in_gray_color() {
		   String actual=loginpg.color_of_password();
		   Assert.assertEquals(actual, "gray");
	}
	
	
	//Through Keyboard actions
	@When("Admin enter valid credentials  and clicks login button through keyboard")
	public void admin_enter_valid_credentials_and_clicks_login_button_through_keyboard() {
		commonfunc.loginThroughKeyboard();
	}

	@Then("Admin should land on dashboard page with successful keyboard action.")
	public void admin_should_land_on_dashboard_page_with_successful_keyboard_action() {
	   loginpg.getPageTitle();
	}
//Through mouse actions
	@When("Admin enter valid credentials  and clicks login button through mouse")
	public void admin_enter_valid_credentials_and_clicks_login_button_through_mouse() {
		commonfunc.loginThroughMouseOverAction();
	}

	@Then("Admin should land on dashboard page with successful mouse action.")
	public void admin_should_land_on_dashboard_page_with_successful_mouse_action() {
		 loginpg.getPageTitle();
	}

}
