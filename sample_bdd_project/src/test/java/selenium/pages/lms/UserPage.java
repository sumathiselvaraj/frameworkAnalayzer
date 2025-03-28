package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the LMS User Module page
 */
public class UserPage extends BasePage {
    // Page elements
    @FindBy(xpath = "//button[text()='Add New User']")
    private WebElement addNewUserButton;
    
    @FindBy(xpath = "//table[contains(@class, 'user-table')]//tr")
    private List<WebElement> userTableRows;
    
    @FindBy(xpath = "//input[@placeholder='Search...']")
    private WebElement searchField;
    
    @FindBy(xpath = "//button[contains(@class, 'search-button')]")
    private WebElement searchButton;
    
    @FindBy(xpath = "//button[contains(@class, 'edit-button')]")
    private List<WebElement> editButtons;
    
    @FindBy(xpath = "//button[contains(@class, 'delete-button')]")
    private List<WebElement> deleteButtons;
    
    @FindBy(xpath = "//button[contains(@class, 'active-button')]")
    private List<WebElement> activeButtons;
    
    @FindBy(xpath = "//div[contains(@class, 'user-modal')]")
    private WebElement userModal;
    
    @FindBy(id = "user-first-name")
    private WebElement userFirstNameField;
    
    @FindBy(id = "user-last-name")
    private WebElement userLastNameField;
    
    @FindBy(id = "user-email")
    private WebElement userEmailField;
    
    @FindBy(id = "user-phone")
    private WebElement userPhoneField;
    
    @FindBy(id = "user-role")
    private WebElement userRoleDropdown;
    
    @FindBy(id = "user-batch")
    private WebElement userBatchDropdown;
    
    @FindBy(id = "user-status")
    private WebElement userStatusDropdown;
    
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;
    
    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelButton;
    
    @FindBy(xpath = "//div[contains(@class, 'confirmation-dialog')]")
    private WebElement confirmationDialog;
    
    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement yesButton;
    
    @FindBy(xpath = "//button[text()='No']")
    private WebElement noButton;
    
    @FindBy(xpath = "//div[contains(@class, 'pagination')]//button")
    private List<WebElement> paginationButtons;
    
    /**
     * Constructor for UserPage
     * @param driver WebDriver instance
     */
    public UserPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click add new user button
     * @return UserPage instance for method chaining
     */
    public UserPage clickAddNewUser() {
        waitForElementToBeClickable(addNewUserButton).click();
        waitForElementToBeVisible(userModal);
        return this;
    }
    
    /**
     * Enter user first name in modal
     * @param firstName First name to enter
     * @return UserPage instance for method chaining
     */
    public UserPage enterUserFirstName(String firstName) {
        clearAndType(userFirstNameField, firstName);
        return this;
    }
    
    /**
     * Enter user last name in modal
     * @param lastName Last name to enter
     * @return UserPage instance for method chaining
     */
    public UserPage enterUserLastName(String lastName) {
        clearAndType(userLastNameField, lastName);
        return this;
    }
    
    /**
     * Enter user email in modal
     * @param email Email to enter
     * @return UserPage instance for method chaining
     */
    public UserPage enterUserEmail(String email) {
        clearAndType(userEmailField, email);
        return this;
    }
    
    /**
     * Enter user phone in modal
     * @param phone Phone to enter
     * @return UserPage instance for method chaining
     */
    public UserPage enterUserPhone(String phone) {
        clearAndType(userPhoneField, phone);
        return this;
    }
    
    /**
     * Select user role in modal
     * @param role Role to select (e.g., "Admin", "Staff", "Student")
     * @return UserPage instance for method chaining
     */
    public UserPage selectUserRole(String role) {
        selectDropdownOptionByText(userRoleDropdown, role);
        return this;
    }
    
    /**
     * Select user batch in modal
     * @param batch Batch to select
     * @return UserPage instance for method chaining
     */
    public UserPage selectUserBatch(String batch) {
        selectDropdownOptionByText(userBatchDropdown, batch);
        return this;
    }
    
    /**
     * Select user status in modal
     * @param status Status to select (e.g., "Active", "Inactive")
     * @return UserPage instance for method chaining
     */
    public UserPage selectUserStatus(String status) {
        selectDropdownOptionByText(userStatusDropdown, status);
        return this;
    }
    
    /**
     * Select dropdown option by visible text
     * @param dropdown WebElement dropdown
     * @param text Text of option to select
     */
    private void selectDropdownOptionByText(WebElement dropdown, String text) {
        org.openqa.selenium.support.ui.Select select = 
            new org.openqa.selenium.support.ui.Select(waitForElementToBeVisible(dropdown));
        select.selectByVisibleText(text);
    }
    
    /**
     * Click save button in modal
     * @return UserPage instance for method chaining
     */
    public UserPage clickSave() {
        waitForElementToBeClickable(saveButton).click();
        wait.until(d -> !isElementDisplayed(userModal));
        return this;
    }
    
    /**
     * Click cancel button in modal
     * @return UserPage instance for method chaining
     */
    public UserPage clickCancel() {
        waitForElementToBeClickable(cancelButton).click();
        wait.until(d -> !isElementDisplayed(userModal));
        return this;
    }
    
    /**
     * Create a new user
     * @param firstName First name
     * @param lastName Last name
     * @param email Email
     * @param phone Phone
     * @param role Role
     * @param batch Batch
     * @param status Status
     * @return UserPage instance for method chaining
     */
    public UserPage createUser(String firstName, String lastName, String email, 
                             String phone, String role, String batch, String status) {
        clickAddNewUser();
        enterUserFirstName(firstName);
        enterUserLastName(lastName);
        enterUserEmail(email);
        enterUserPhone(phone);
        selectUserRole(role);
        selectUserBatch(batch);
        selectUserStatus(status);
        return clickSave();
    }
    
    /**
     * Search for a user
     * @param searchText Text to search for
     * @return UserPage instance for method chaining
     */
    public UserPage searchUser(String searchText) {
        clearAndType(searchField, searchText);
        waitForElementToBeClickable(searchButton).click();
        return this;
    }
    
    /**
     * Click edit button for a user row
     * @param rowIndex Index of the row (0-based)
     * @return UserPage instance for method chaining
     */
    public UserPage clickEditUser(int rowIndex) {
        List<WebElement> buttons = waitForElementsToBeVisible(editButtons);
        if (rowIndex < buttons.size()) {
            waitForElementToBeClickable(buttons.get(rowIndex)).click();
            waitForElementToBeVisible(userModal);
            return this;
        }
        throw new IndexOutOfBoundsException("Edit button index out of bounds");
    }
    
    /**
     * Click delete button for a user row
     * @param rowIndex Index of the row (0-based)
     * @return UserPage instance for method chaining
     */
    public UserPage clickDeleteUser(int rowIndex) {
        List<WebElement> buttons = waitForElementsToBeVisible(deleteButtons);
        if (rowIndex < buttons.size()) {
            waitForElementToBeClickable(buttons.get(rowIndex)).click();
            waitForElementToBeVisible(confirmationDialog);
            return this;
        }
        throw new IndexOutOfBoundsException("Delete button index out of bounds");
    }
    
    /**
     * Confirm deletion in dialog
     * @return UserPage instance for method chaining
     */
    public UserPage confirmDeletion() {
        waitForElementToBeClickable(yesButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Cancel deletion in dialog
     * @return UserPage instance for method chaining
     */
    public UserPage cancelDeletion() {
        waitForElementToBeClickable(noButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Get user table row count
     * @return Number of rows in user table
     */
    public int getUserTableRowCount() {
        return waitForElementsToBeVisible(userTableRows).size() - 1; // Subtract header row
    }
    
    /**
     * Get user name from row
     * @param rowIndex Index of the row (0-based)
     * @return User full name text
     */
    public String getUserNameFromRow(int rowIndex) {
        List<WebElement> rows = waitForElementsToBeVisible(userTableRows);
        if (rowIndex + 1 < rows.size()) { // +1 to account for header row
            WebElement nameCell = rows.get(rowIndex + 1).findElement(
                org.openqa.selenium.By.xpath("./td[1]"));
            return nameCell.getText();
        }
        throw new IndexOutOfBoundsException("User row index out of bounds");
    }
    
    /**
     * Get user email from row
     * @param rowIndex Index of the row (0-based)
     * @return User email text
     */
    public String getUserEmailFromRow(int rowIndex) {
        List<WebElement> rows = waitForElementsToBeVisible(userTableRows);
        if (rowIndex + 1 < rows.size()) { // +1 to account for header row
            WebElement emailCell = rows.get(rowIndex + 1).findElement(
                org.openqa.selenium.By.xpath("./td[3]"));
            return emailCell.getText();
        }
        throw new IndexOutOfBoundsException("User row index out of bounds");
    }
    
    /**
     * Check if pagination exists
     * @return true if pagination exists, false otherwise
     */
    public boolean hasPagination() {
        return !paginationButtons.isEmpty();
    }
    
    /**
     * Click on a pagination button by page number
     * @param pageNumber Page number to click
     * @return UserPage instance for method chaining
     */
    public UserPage clickPaginationPage(int pageNumber) {
        for (WebElement button : waitForElementsToBeVisible(paginationButtons)) {
            if (button.getText().trim().equals(String.valueOf(pageNumber))) {
                waitForElementToBeClickable(button).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Page number not found: " + pageNumber);
    }
}