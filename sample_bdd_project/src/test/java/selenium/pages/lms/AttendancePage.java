package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the LMS Attendance Module page
 */
public class AttendancePage extends BasePage {
    // Page elements
    @FindBy(xpath = "//button[text()='Mark Attendance']")
    private WebElement markAttendanceButton;
    
    @FindBy(xpath = "//table[contains(@class, 'attendance-table')]//tr")
    private List<WebElement> attendanceTableRows;
    
    @FindBy(xpath = "//select[@id='batch-filter']")
    private WebElement batchFilterDropdown;
    
    @FindBy(xpath = "//select[@id='date-filter']")
    private WebElement dateFilterDropdown;
    
    @FindBy(xpath = "//button[contains(@class, 'filter-button')]")
    private WebElement filterButton;
    
    @FindBy(xpath = "//input[@placeholder='Search...']")
    private WebElement searchField;
    
    @FindBy(xpath = "//button[contains(@class, 'search-button')]")
    private WebElement searchButton;
    
    @FindBy(xpath = "//div[contains(@class, 'attendance-modal')]")
    private WebElement attendanceModal;
    
    @FindBy(id = "attendance-batch")
    private WebElement attendanceBatchDropdown;
    
    @FindBy(id = "attendance-date")
    private WebElement attendanceDateField;
    
    @FindBy(xpath = "//table[contains(@class, 'student-attendance-table')]//tr")
    private List<WebElement> studentAttendanceTableRows;
    
    @FindBy(xpath = "//input[contains(@class, 'present-checkbox')]")
    private List<WebElement> presentCheckboxes;
    
    @FindBy(xpath = "//input[contains(@class, 'absent-checkbox')]")
    private List<WebElement> absentCheckboxes;
    
    @FindBy(xpath = "//button[text()='Save']")
    private WebElement saveButton;
    
    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelButton;
    
    @FindBy(xpath = "//div[contains(@class, 'attendance-report-section')]")
    private WebElement attendanceReportSection;
    
    @FindBy(xpath = "//button[text()='Generate Report']")
    private WebElement generateReportButton;
    
    @FindBy(xpath = "//select[@id='report-batch']")
    private WebElement reportBatchDropdown;
    
    @FindBy(xpath = "//input[@id='report-start-date']")
    private WebElement reportStartDateField;
    
    @FindBy(xpath = "//input[@id='report-end-date']")
    private WebElement reportEndDateField;
    
    @FindBy(xpath = "//button[text()='Download Report']")
    private WebElement downloadReportButton;
    
    @FindBy(xpath = "//div[contains(@class, 'pagination')]//button")
    private List<WebElement> paginationButtons;
    
    /**
     * Constructor for AttendancePage
     * @param driver WebDriver instance
     */
    public AttendancePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Click mark attendance button
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage clickMarkAttendance() {
        waitForElementToBeClickable(markAttendanceButton).click();
        waitForElementToBeVisible(attendanceModal);
        return this;
    }
    
    /**
     * Select batch for attendance in modal
     * @param batch Batch to select
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage selectAttendanceBatch(String batch) {
        selectDropdownOptionByText(attendanceBatchDropdown, batch);
        return this;
    }
    
    /**
     * Enter attendance date in modal
     * @param date Date in format MM/DD/YYYY
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage enterAttendanceDate(String date) {
        clearAndType(attendanceDateField, date);
        return this;
    }
    
    /**
     * Mark student as present
     * @param rowIndex Index of the student row (0-based)
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage markStudentPresent(int rowIndex) {
        if (rowIndex < presentCheckboxes.size()) {
            WebElement checkbox = presentCheckboxes.get(rowIndex);
            if (!checkbox.isSelected()) {
                waitForElementToBeClickable(checkbox).click();
            }
            return this;
        }
        throw new IndexOutOfBoundsException("Student row index out of bounds");
    }
    
    /**
     * Mark student as absent
     * @param rowIndex Index of the student row (0-based)
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage markStudentAbsent(int rowIndex) {
        if (rowIndex < absentCheckboxes.size()) {
            WebElement checkbox = absentCheckboxes.get(rowIndex);
            if (!checkbox.isSelected()) {
                waitForElementToBeClickable(checkbox).click();
            }
            return this;
        }
        throw new IndexOutOfBoundsException("Student row index out of bounds");
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
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage clickSave() {
        waitForElementToBeClickable(saveButton).click();
        wait.until(d -> !isElementDisplayed(attendanceModal));
        return this;
    }
    
    /**
     * Click cancel button in modal
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage clickCancel() {
        waitForElementToBeClickable(cancelButton).click();
        wait.until(d -> !isElementDisplayed(attendanceModal));
        return this;
    }
    
    /**
     * Filter attendance records
     * @param batch Batch to filter by
     * @param date Date to filter by
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage filterAttendance(String batch, String date) {
        selectDropdownOptionByText(batchFilterDropdown, batch);
        selectDropdownOptionByText(dateFilterDropdown, date);
        waitForElementToBeClickable(filterButton).click();
        return this;
    }
    
    /**
     * Search for attendance records
     * @param searchText Text to search for (student name, etc.)
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage searchAttendance(String searchText) {
        clearAndType(searchField, searchText);
        waitForElementToBeClickable(searchButton).click();
        return this;
    }
    
    /**
     * Click generate report button
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage clickGenerateReport() {
        waitForElementToBeClickable(generateReportButton).click();
        waitForElementToBeVisible(attendanceReportSection);
        return this;
    }
    
    /**
     * Select batch for report
     * @param batch Batch to select
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage selectReportBatch(String batch) {
        selectDropdownOptionByText(reportBatchDropdown, batch);
        return this;
    }
    
    /**
     * Enter report start date
     * @param date Date in format MM/DD/YYYY
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage enterReportStartDate(String date) {
        clearAndType(reportStartDateField, date);
        return this;
    }
    
    /**
     * Enter report end date
     * @param date Date in format MM/DD/YYYY
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage enterReportEndDate(String date) {
        clearAndType(reportEndDateField, date);
        return this;
    }
    
    /**
     * Click download report button
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage clickDownloadReport() {
        waitForElementToBeClickable(downloadReportButton).click();
        // Note: This typically initiates a file download
        // You might need to handle browser download dialogs separately
        return this;
    }
    
    /**
     * Generate attendance report
     * @param batch Batch for report
     * @param startDate Start date for report
     * @param endDate End date for report
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage generateAttendanceReport(String batch, String startDate, String endDate) {
        clickGenerateReport();
        selectReportBatch(batch);
        enterReportStartDate(startDate);
        enterReportEndDate(endDate);
        return clickDownloadReport();
    }
    
    /**
     * Get attendance table row count
     * @return Number of rows in attendance table
     */
    public int getAttendanceTableRowCount() {
        return waitForElementsToBeVisible(attendanceTableRows).size() - 1; // Subtract header row
    }
    
    /**
     * Get student attendance table row count in modal
     * @return Number of rows in student attendance table
     */
    public int getStudentAttendanceTableRowCount() {
        return waitForElementsToBeVisible(studentAttendanceTableRows).size() - 1; // Subtract header row
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
     * @return AttendancePage instance for method chaining
     */
    public AttendancePage clickPaginationPage(int pageNumber) {
        for (WebElement button : waitForElementsToBeVisible(paginationButtons)) {
            if (button.getText().trim().equals(String.valueOf(pageNumber))) {
                waitForElementToBeClickable(button).click();
                return this;
            }
        }
        throw new IllegalArgumentException("Page number not found: " + pageNumber);
    }
}