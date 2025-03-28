package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the LMS Class Module page
 */
public class ClassPage extends BasePage {
    // Page elements
    @FindBy(xpath = "//button[text()='Add New Class']")
    private WebElement addNewClassButton;
    
    @FindBy(xpath = "//table[contains(@class, 'class-table')]//tr")
    private List<WebElement> classTableRows;
    
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
    
    @FindBy(xpath = "//div[contains(@class, 'class-modal')]")
    private WebElement classModal;
    
    @FindBy(id = "class-batch")
    private WebElement classBatchDropdown;
    
    @FindBy(id = "class-topic")
    private WebElement classTopicField;
    
    @FindBy(id = "class-staff")
    private WebElement classStaffDropdown;
    
    @FindBy(id = "class-date")
    private WebElement classDateField;
    
    @FindBy(id = "class-start-time")
    private WebElement classStartTimeField;
    
    @FindBy(id = "class-end-time")
    private WebElement classEndTimeField;
    
    @FindBy(id = "class-description")
    private WebElement classDescriptionField;
    
    @FindBy(id = "class-recording-link")
    private WebElement classRecordingLinkField;
    
    @FindBy(id = "class-notes")
    private WebElement classNotesField;
    
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
     * Constructor for ClassPage
     * @param driver WebDriver instance
     */
    public ClassPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click add new class button
     * @return ClassPage instance for method chaining
     */
    public ClassPage clickAddNewClass() {
        waitForElementToBeClickable(addNewClassButton).click();
        waitForElementToBeVisible(classModal);
        return this;
    }
    
    /**
     * Select batch for class in modal
     * @param batch Batch to select
     * @return ClassPage instance for method chaining
     */
    public ClassPage selectClassBatch(String batch) {
        selectDropdownOptionByText(classBatchDropdown, batch);
        return this;
    }
    
    /**
     * Enter class topic in modal
     * @param topic Class topic to enter
     * @return ClassPage instance for method chaining
     */
    public ClassPage enterClassTopic(String topic) {
        clearAndType(classTopicField, topic);
        return this;
    }
    
    /**
     * Select staff for class in modal
     * @param staff Staff to select
     * @return ClassPage instance for method chaining
     */
    public ClassPage selectClassStaff(String staff) {
        selectDropdownOptionByText(classStaffDropdown, staff);
        return this;
    }
    
    /**
     * Enter class date in modal
     * @param date Date in format MM/DD/YYYY
     * @return ClassPage instance for method chaining
     */
    public ClassPage enterClassDate(String date) {
        clearAndType(classDateField, date);
        return this;
    }
    
    /**
     * Enter class start time in modal
     * @param time Time in format HH:MM AM/PM
     * @return ClassPage instance for method chaining
     */
    public ClassPage enterClassStartTime(String time) {
        clearAndType(classStartTimeField, time);
        return this;
    }
    
    /**
     * Enter class end time in modal
     * @param time Time in format HH:MM AM/PM
     * @return ClassPage instance for method chaining
     */
    public ClassPage enterClassEndTime(String time) {
        clearAndType(classEndTimeField, time);
        return this;
    }
    
    /**
     * Enter class description in modal
     * @param description Description to enter
     * @return ClassPage instance for method chaining
     */
    public ClassPage enterClassDescription(String description) {
        clearAndType(classDescriptionField, description);
        return this;
    }
    
    /**
     * Enter class recording link in modal
     * @param link Recording link to enter
     * @return ClassPage instance for method chaining
     */
    public ClassPage enterClassRecordingLink(String link) {
        clearAndType(classRecordingLinkField, link);
        return this;
    }
    
    /**
     * Enter class notes in modal
     * @param notes Notes to enter
     * @return ClassPage instance for method chaining
     */
    public ClassPage enterClassNotes(String notes) {
        clearAndType(classNotesField, notes);
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
     * @return ClassPage instance for method chaining
     */
    public ClassPage clickSave() {
        waitForElementToBeClickable(saveButton).click();
        wait.until(d -> !isElementDisplayed(classModal));
        return this;
    }
    
    /**
     * Click cancel button in modal
     * @return ClassPage instance for method chaining
     */
    public ClassPage clickCancel() {
        waitForElementToBeClickable(cancelButton).click();
        wait.until(d -> !isElementDisplayed(classModal));
        return this;
    }
    
    /**
     * Create a new class
     * @param batch Batch for class
     * @param topic Class topic
     * @param staff Staff for class
     * @param date Class date
     * @param startTime Class start time
     * @param endTime Class end time
     * @param description Class description
     * @return ClassPage instance for method chaining
     */
    public ClassPage createClass(String batch, String topic, String staff, 
                               String date, String startTime, String endTime, 
                               String description) {
        clickAddNewClass();
        selectClassBatch(batch);
        enterClassTopic(topic);
        selectClassStaff(staff);
        enterClassDate(date);
        enterClassStartTime(startTime);
        enterClassEndTime(endTime);
        enterClassDescription(description);
        return clickSave();
    }
    
    /**
     * Search for a class
     * @param searchText Text to search for
     * @return ClassPage instance for method chaining
     */
    public ClassPage searchClass(String searchText) {
        clearAndType(searchField, searchText);
        waitForElementToBeClickable(searchButton).click();
        return this;
    }
    
    /**
     * Click edit button for a class row
     * @param rowIndex Index of the row (0-based)
     * @return ClassPage instance for method chaining
     */
    public ClassPage clickEditClass(int rowIndex) {
        List<WebElement> buttons = waitForElementsToBeVisible(editButtons);
        if (rowIndex < buttons.size()) {
            waitForElementToBeClickable(buttons.get(rowIndex)).click();
            waitForElementToBeVisible(classModal);
            return this;
        }
        throw new IndexOutOfBoundsException("Edit button index out of bounds");
    }
    
    /**
     * Click delete button for a class row
     * @param rowIndex Index of the row (0-based)
     * @return ClassPage instance for method chaining
     */
    public ClassPage clickDeleteClass(int rowIndex) {
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
     * @return ClassPage instance for method chaining
     */
    public ClassPage confirmDeletion() {
        waitForElementToBeClickable(yesButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Cancel deletion in dialog
     * @return ClassPage instance for method chaining
     */
    public ClassPage cancelDeletion() {
        waitForElementToBeClickable(noButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Get class table row count
     * @return Number of rows in class table
     */
    public int getClassTableRowCount() {
        return waitForElementsToBeVisible(classTableRows).size() - 1; // Subtract header row
    }
    
    /**
     * Get class topic from row
     * @param rowIndex Index of the row (0-based)
     * @return Class topic text
     */
    public String getClassTopicFromRow(int rowIndex) {
        List<WebElement> rows = waitForElementsToBeVisible(classTableRows);
        if (rowIndex + 1 < rows.size()) { // +1 to account for header row
            WebElement topicCell = rows.get(rowIndex + 1).findElement(
                org.openqa.selenium.By.xpath("./td[2]"));
            return topicCell.getText();
        }
        throw new IndexOutOfBoundsException("Class row index out of bounds");
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
     * @return ClassPage instance for method chaining
     */
    public ClassPage clickPaginationPage(int pageNumber) {
        for (WebElement button : waitForElementsToBeVisible(paginationButtons)) {
            if (button.getText().trim().equals(String.valueOf(pageNumber))) {
                waitForElementToBeClickable(button).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Page number not found: " + pageNumber);
    }
}