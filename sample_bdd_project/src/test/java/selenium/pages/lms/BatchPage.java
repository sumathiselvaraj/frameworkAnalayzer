package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the LMS Batch Module page
 */
public class BatchPage extends BasePage {
    // Page elements
    @FindBy(xpath = "//button[text()='Add New Batch']")
    private WebElement addNewBatchButton;
    
    @FindBy(xpath = "//table[contains(@class, 'batch-table')]//tr")
    private List<WebElement> batchTableRows;
    
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
    
    @FindBy(xpath = "//div[contains(@class, 'batch-modal')]")
    private WebElement batchModal;
    
    @FindBy(id = "batch-name")
    private WebElement batchNameField;
    
    @FindBy(id = "batch-description")
    private WebElement batchDescriptionField;
    
    @FindBy(id = "batch-program")
    private WebElement batchProgramDropdown;
    
    @FindBy(id = "batch-status")
    private WebElement batchStatusDropdown;
    
    @FindBy(id = "batch-start-date")
    private WebElement batchStartDateField;
    
    @FindBy(id = "batch-end-date")
    private WebElement batchEndDateField;
    
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
     * Constructor for BatchPage
     * @param driver WebDriver instance
     */
    public BatchPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click add new batch button
     * @return BatchPage instance for method chaining
     */
    public BatchPage clickAddNewBatch() {
        waitForElementToBeClickable(addNewBatchButton).click();
        waitForElementToBeVisible(batchModal);
        return this;
    }
    
    /**
     * Enter batch name in modal
     * @param name Batch name to enter
     * @return BatchPage instance for method chaining
     */
    public BatchPage enterBatchName(String name) {
        clearAndType(batchNameField, name);
        return this;
    }
    
    /**
     * Enter batch description in modal
     * @param description Batch description to enter
     * @return BatchPage instance for method chaining
     */
    public BatchPage enterBatchDescription(String description) {
        clearAndType(batchDescriptionField, description);
        return this;
    }
    
    /**
     * Select program for batch in modal
     * @param program Program to select
     * @return BatchPage instance for method chaining
     */
    public BatchPage selectBatchProgram(String program) {
        selectDropdownOptionByText(batchProgramDropdown, program);
        return this;
    }
    
    /**
     * Select batch status in modal
     * @param status Status to select (e.g., "Active", "Inactive")
     * @return BatchPage instance for method chaining
     */
    public BatchPage selectBatchStatus(String status) {
        selectDropdownOptionByText(batchStatusDropdown, status);
        return this;
    }
    
    /**
     * Enter batch start date in modal
     * @param date Start date in format MM/DD/YYYY
     * @return BatchPage instance for method chaining
     */
    public BatchPage enterBatchStartDate(String date) {
        clearAndType(batchStartDateField, date);
        return this;
    }
    
    /**
     * Enter batch end date in modal
     * @param date End date in format MM/DD/YYYY
     * @return BatchPage instance for method chaining
     */
    public BatchPage enterBatchEndDate(String date) {
        clearAndType(batchEndDateField, date);
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
     * @return BatchPage instance for method chaining
     */
    public BatchPage clickSave() {
        waitForElementToBeClickable(saveButton).click();
        wait.until(d -> !isElementDisplayed(batchModal));
        return this;
    }
    
    /**
     * Click cancel button in modal
     * @return BatchPage instance for method chaining
     */
    public BatchPage clickCancel() {
        waitForElementToBeClickable(cancelButton).click();
        wait.until(d -> !isElementDisplayed(batchModal));
        return this;
    }
    
    /**
     * Create a new batch
     * @param name Batch name
     * @param description Batch description
     * @param program Program for batch
     * @param status Batch status
     * @param startDate Start date
     * @param endDate End date
     * @return BatchPage instance for method chaining
     */
    public BatchPage createBatch(String name, String description, String program, 
                                String status, String startDate, String endDate) {
        clickAddNewBatch();
        enterBatchName(name);
        enterBatchDescription(description);
        selectBatchProgram(program);
        selectBatchStatus(status);
        enterBatchStartDate(startDate);
        enterBatchEndDate(endDate);
        return clickSave();
    }
    
    /**
     * Search for a batch
     * @param searchText Text to search for
     * @return BatchPage instance for method chaining
     */
    public BatchPage searchBatch(String searchText) {
        clearAndType(searchField, searchText);
        waitForElementToBeClickable(searchButton).click();
        return this;
    }
    
    /**
     * Click edit button for a batch row
     * @param rowIndex Index of the row (0-based)
     * @return BatchPage instance for method chaining
     */
    public BatchPage clickEditBatch(int rowIndex) {
        List<WebElement> buttons = waitForElementsToBeVisible(editButtons);
        if (rowIndex < buttons.size()) {
            waitForElementToBeClickable(buttons.get(rowIndex)).click();
            waitForElementToBeVisible(batchModal);
            return this;
        }
        throw new IndexOutOfBoundsException("Edit button index out of bounds");
    }
    
    /**
     * Click delete button for a batch row
     * @param rowIndex Index of the row (0-based)
     * @return BatchPage instance for method chaining
     */
    public BatchPage clickDeleteBatch(int rowIndex) {
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
     * @return BatchPage instance for method chaining
     */
    public BatchPage confirmDeletion() {
        waitForElementToBeClickable(yesButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Cancel deletion in dialog
     * @return BatchPage instance for method chaining
     */
    public BatchPage cancelDeletion() {
        waitForElementToBeClickable(noButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Get batch table row count
     * @return Number of rows in batch table
     */
    public int getBatchTableRowCount() {
        return waitForElementsToBeVisible(batchTableRows).size() - 1; // Subtract header row
    }
    
    /**
     * Get batch name from row
     * @param rowIndex Index of the row (0-based)
     * @return Batch name text
     */
    public String getBatchNameFromRow(int rowIndex) {
        List<WebElement> rows = waitForElementsToBeVisible(batchTableRows);
        if (rowIndex + 1 < rows.size()) { // +1 to account for header row
            WebElement nameCell = rows.get(rowIndex + 1).findElement(
                org.openqa.selenium.By.xpath("./td[1]"));
            return nameCell.getText();
        }
        throw new IndexOutOfBoundsException("Batch row index out of bounds");
    }
    
    /**
     * Get batch program from row
     * @param rowIndex Index of the row (0-based)
     * @return Batch program text
     */
    public String getBatchProgramFromRow(int rowIndex) {
        List<WebElement> rows = waitForElementsToBeVisible(batchTableRows);
        if (rowIndex + 1 < rows.size()) { // +1 to account for header row
            WebElement programCell = rows.get(rowIndex + 1).findElement(
                org.openqa.selenium.By.xpath("./td[2]"));
            return programCell.getText();
        }
        throw new IndexOutOfBoundsException("Batch row index out of bounds");
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
     * @return BatchPage instance for method chaining
     */
    public BatchPage clickPaginationPage(int pageNumber) {
        for (WebElement button : waitForElementsToBeVisible(paginationButtons)) {
            if (button.getText().trim().equals(String.valueOf(pageNumber))) {
                waitForElementToBeClickable(button).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Page number not found: " + pageNumber);
    }
}