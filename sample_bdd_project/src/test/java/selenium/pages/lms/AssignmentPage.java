package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the LMS Assignment Module page
 */
public class AssignmentPage extends BasePage {
    // Page elements
    @FindBy(xpath = "//button[text()='Add New Assignment']")
    private WebElement addNewAssignmentButton;
    
    @FindBy(xpath = "//table[contains(@class, 'assignment-table')]//tr")
    private List<WebElement> assignmentTableRows;
    
    @FindBy(xpath = "//input[@placeholder='Search...']")
    private WebElement searchField;
    
    @FindBy(xpath = "//button[contains(@class, 'search-button')]")
    private WebElement searchButton;
    
    @FindBy(xpath = "//button[contains(@class, 'edit-button')]")
    private List<WebElement> editButtons;
    
    @FindBy(xpath = "//button[contains(@class, 'delete-button')]")
    private List<WebElement> deleteButtons;
    
    @FindBy(xpath = "//button[contains(@class, 'grade-button')]")
    private List<WebElement> gradeButtons;
    
    @FindBy(xpath = "//div[contains(@class, 'assignment-modal')]")
    private WebElement assignmentModal;
    
    @FindBy(id = "assignment-name")
    private WebElement assignmentNameField;
    
    @FindBy(id = "assignment-description")
    private WebElement assignmentDescriptionField;
    
    @FindBy(id = "assignment-batch")
    private WebElement assignmentBatchDropdown;
    
    @FindBy(id = "assignment-due-date")
    private WebElement assignmentDueDateField;
    
    @FindBy(id = "assignment-grade")
    private WebElement assignmentGradeField;
    
    @FindBy(id = "assignment-file-upload")
    private WebElement assignmentFileUploadField;
    
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
    
    @FindBy(xpath = "//div[contains(@class, 'grading-modal')]")
    private WebElement gradingModal;
    
    @FindBy(id = "student-name")
    private WebElement studentNameField;
    
    @FindBy(id = "assignment-score")
    private WebElement assignmentScoreField;
    
    @FindBy(id = "assignment-feedback")
    private WebElement assignmentFeedbackField;
    
    @FindBy(xpath = "//button[text()='Submit Grade']")
    private WebElement submitGradeButton;
    
    @FindBy(xpath = "//div[contains(@class, 'pagination')]//button")
    private List<WebElement> paginationButtons;
    
    /**
     * Constructor for AssignmentPage
     * @param driver WebDriver instance
     */
    public AssignmentPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click add new assignment button
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage clickAddNewAssignment() {
        waitForElementToBeClickable(addNewAssignmentButton).click();
        waitForElementToBeVisible(assignmentModal);
        return this;
    }
    
    /**
     * Enter assignment name in modal
     * @param name Assignment name to enter
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage enterAssignmentName(String name) {
        clearAndType(assignmentNameField, name);
        return this;
    }
    
    /**
     * Enter assignment description in modal
     * @param description Assignment description to enter
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage enterAssignmentDescription(String description) {
        clearAndType(assignmentDescriptionField, description);
        return this;
    }
    
    /**
     * Select batch for assignment in modal
     * @param batch Batch to select
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage selectAssignmentBatch(String batch) {
        selectDropdownOptionByText(assignmentBatchDropdown, batch);
        return this;
    }
    
    /**
     * Enter assignment due date in modal
     * @param date Due date in format MM/DD/YYYY
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage enterAssignmentDueDate(String date) {
        clearAndType(assignmentDueDateField, date);
        return this;
    }
    
    /**
     * Enter assignment grade in modal
     * @param grade Grade (e.g., "100")
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage enterAssignmentGrade(String grade) {
        clearAndType(assignmentGradeField, grade);
        return this;
    }
    
    /**
     * Upload assignment file in modal
     * @param filePath Path to file to upload
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage uploadAssignmentFile(String filePath) {
        assignmentFileUploadField.sendKeys(filePath);
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
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage clickSave() {
        waitForElementToBeClickable(saveButton).click();
        wait.until(d -> !isElementDisplayed(assignmentModal));
        return this;
    }
    
    /**
     * Click cancel button in modal
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage clickCancel() {
        waitForElementToBeClickable(cancelButton).click();
        wait.until(d -> !isElementDisplayed(assignmentModal));
        return this;
    }
    
    /**
     * Create a new assignment
     * @param name Assignment name
     * @param description Assignment description
     * @param batch Batch for assignment
     * @param dueDate Due date
     * @param grade Grade
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage createAssignment(String name, String description, 
                                         String batch, String dueDate, String grade) {
        clickAddNewAssignment();
        enterAssignmentName(name);
        enterAssignmentDescription(description);
        selectAssignmentBatch(batch);
        enterAssignmentDueDate(dueDate);
        enterAssignmentGrade(grade);
        return clickSave();
    }
    
    /**
     * Search for an assignment
     * @param searchText Text to search for
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage searchAssignment(String searchText) {
        clearAndType(searchField, searchText);
        waitForElementToBeClickable(searchButton).click();
        return this;
    }
    
    /**
     * Click edit button for an assignment row
     * @param rowIndex Index of the row (0-based)
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage clickEditAssignment(int rowIndex) {
        List<WebElement> buttons = waitForElementsToBeVisible(editButtons);
        if (rowIndex < buttons.size()) {
            waitForElementToBeClickable(buttons.get(rowIndex)).click();
            waitForElementToBeVisible(assignmentModal);
            return this;
        }
        throw new IndexOutOfBoundsException("Edit button index out of bounds");
    }
    
    /**
     * Click delete button for an assignment row
     * @param rowIndex Index of the row (0-based)
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage clickDeleteAssignment(int rowIndex) {
        List<WebElement> buttons = waitForElementsToBeVisible(deleteButtons);
        if (rowIndex < buttons.size()) {
            waitForElementToBeClickable(buttons.get(rowIndex)).click();
            waitForElementToBeVisible(confirmationDialog);
            return this;
        }
        throw new IndexOutOfBoundsException("Delete button index out of bounds");
    }
    
    /**
     * Click grade button for an assignment row
     * @param rowIndex Index of the row (0-based)
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage clickGradeAssignment(int rowIndex) {
        List<WebElement> buttons = waitForElementsToBeVisible(gradeButtons);
        if (rowIndex < buttons.size()) {
            waitForElementToBeClickable(buttons.get(rowIndex)).click();
            waitForElementToBeVisible(gradingModal);
            return this;
        }
        throw new IndexOutOfBoundsException("Grade button index out of bounds");
    }
    
    /**
     * Enter assignment score in grading modal
     * @param score Score to enter
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage enterAssignmentScore(String score) {
        clearAndType(assignmentScoreField, score);
        return this;
    }
    
    /**
     * Enter assignment feedback in grading modal
     * @param feedback Feedback to enter
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage enterAssignmentFeedback(String feedback) {
        clearAndType(assignmentFeedbackField, feedback);
        return this;
    }
    
    /**
     * Click submit grade button in grading modal
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage clickSubmitGrade() {
        waitForElementToBeClickable(submitGradeButton).click();
        wait.until(d -> !isElementDisplayed(gradingModal));
        return this;
    }
    
    /**
     * Grade an assignment
     * @param rowIndex Index of the row (0-based)
     * @param score Score to enter
     * @param feedback Feedback to enter
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage gradeAssignment(int rowIndex, String score, String feedback) {
        clickGradeAssignment(rowIndex);
        enterAssignmentScore(score);
        enterAssignmentFeedback(feedback);
        return clickSubmitGrade();
    }
    
    /**
     * Confirm deletion in dialog
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage confirmDeletion() {
        waitForElementToBeClickable(yesButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Cancel deletion in dialog
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage cancelDeletion() {
        waitForElementToBeClickable(noButton).click();
        wait.until(d -> !isElementDisplayed(confirmationDialog));
        return this;
    }
    
    /**
     * Get assignment table row count
     * @return Number of rows in assignment table
     */
    public int getAssignmentTableRowCount() {
        return waitForElementsToBeVisible(assignmentTableRows).size() - 1; // Subtract header row
    }
    
    /**
     * Get assignment name from row
     * @param rowIndex Index of the row (0-based)
     * @return Assignment name text
     */
    public String getAssignmentNameFromRow(int rowIndex) {
        List<WebElement> rows = waitForElementsToBeVisible(assignmentTableRows);
        if (rowIndex + 1 < rows.size()) { // +1 to account for header row
            WebElement nameCell = rows.get(rowIndex + 1).findElement(
                org.openqa.selenium.By.xpath("./td[1]"));
            return nameCell.getText();
        }
        throw new IndexOutOfBoundsException("Assignment row index out of bounds");
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
     * @return AssignmentPage instance for method chaining
     */
    public AssignmentPage clickPaginationPage(int pageNumber) {
        for (WebElement button : waitForElementsToBeVisible(paginationButtons)) {
            if (button.getText().trim().equals(String.valueOf(pageNumber))) {
                waitForElementToBeClickable(button).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Page number not found: " + pageNumber);
    }
}