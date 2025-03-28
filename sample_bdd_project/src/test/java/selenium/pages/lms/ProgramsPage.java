package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the LMS Program Module page
 */
public class ProgramsPage extends BasePage {
    // Page elements
    @FindBy(xpath = "//button[text()='Add New Program']")
    private WebElement addNewProgramButton;
    
    @FindBy(xpath = "//table[contains(@class, 'program-table')]//tr")
    private List<WebElement> programTableRows;
    
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
    
    @FindBy(xpath = "//div[contains(@class, 'program-modal')]")
    private WebElement programModal;
    
    @FindBy(id = "program-name")
    private WebElement programNameField;
    
    @FindBy(id = "program-description")
    private WebElement programDescriptionField;
    
    @FindBy(id = "program-status")
    private WebElement programStatusDropdown;
    
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
     * Constructor for ProgramsPage
     * @param driver WebDriver instance
     */
    public ProgramsPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click add new program button
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage clickAddNewProgram() {
        waitForElementToBeClickable(addNewProgramButton).click();
        waitForElementToBeVisible(programModal);
        return this;
    }
    
    /**
     * Enter program name in modal
     * @param name Program name to enter
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage enterProgramName(String name) {
        clearAndType(programNameField, name);
        return this;
    }
    
    /**
     * Enter program description in modal
     * @param description Program description to enter
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage enterProgramDescription(String description) {
        clearAndType(programDescriptionField, description);
        return this;
    }
    
    /**
     * Select program status in modal
     * @param status Status to select (e.g., "Active", "Inactive")
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage selectProgramStatus(String status) {
        selectDropdownOptionByText(programStatusDropdown, status);
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
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage clickSave() {
        waitForElementToBeClickable(saveButton).click();
        wait.until(d -> !isElementDisplayed(programModal));
        return this;
    }
    
    /**
     * Click cancel button in modal
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage clickCancel() {
        waitForElementToBeClickable(cancelButton).click();
        wait.until(d -> !isElementDisplayed(programModal));
        return this;
    }
    
    /**
     * Create a new program
     * @param name Program name
     * @param description Program description
     * @param status Program status
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage createProgram(String name, String description, String status) {
        clickAddNewProgram();
        enterProgramName(name);
        enterProgramDescription(description);
        selectProgramStatus(status);
        return clickSave();
    }
    
    /**
     * Search for a program
     * @param searchText Text to search for
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage searchProgram(String searchText) {
        clearAndType(searchField, searchText);
        waitForElementToBeClickable(searchButton).click();
        return this;
    }
    
    /**
     * Click edit button for a program row
     * @param rowIndex Index of the row (0-based)
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage clickEditProgram(int rowIndex) {
        List<WebElement> buttons = waitForElementsToBeVisible(editButtons);
        if (rowIndex < buttons.size()) {
            waitForElementToBeClickable(buttons.get(rowIndex)).click();
            waitForElementToBeVisible(programModal);
            return this;
        }
        throw new IndexOutOfBoundsException("Edit button index out of bounds");
    }
    
    /**
     * Click delete button for a program row
     * @param rowIndex Index of the row (0-based)
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage clickDeleteProgram(int rowIndex) {
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
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage confirmDeletion() {
        waitForElementToBeClickable(yesButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Cancel deletion in dialog
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage cancelDeletion() {
        waitForElementToBeClickable(noButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Get program table row count
     * @return Number of rows in program table
     */
    public int getProgramTableRowCount() {
        return waitForElementsToBeVisible(programTableRows).size() - 1; // Subtract header row
    }
    
    /**
     * Get program name from row
     * @param rowIndex Index of the row (0-based)
     * @return Program name text
     */
    public String getProgramNameFromRow(int rowIndex) {
        List<WebElement> rows = waitForElementsToBeVisible(programTableRows);
        if (rowIndex + 1 < rows.size()) { // +1 to account for header row
            WebElement nameCell = rows.get(rowIndex + 1).findElement(
                org.openqa.selenium.By.xpath("./td[1]"));
            return nameCell.getText();
        }
        throw new IndexOutOfBoundsException("Program row index out of bounds");
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
     * @return ProgramsPage instance for method chaining
     */
    public ProgramsPage clickPaginationPage(int pageNumber) {
        for (WebElement button : waitForElementsToBeVisible(paginationButtons)) {
            if (button.getText().trim().equals(String.valueOf(pageNumber))) {
                waitForElementToBeClickable(button).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Page number not found: " + pageNumber);
    }
}