package selenium.pages.lms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the LMS dashboard/landing page
 */
public class DashboardPage extends BasePage {
    // Page elements
    @FindBy(xpath = "//div[contains(text(), 'Welcome')]")
    private WebElement welcomeMessage;
    
    @FindBy(xpath = "//div[contains(@class, 'user-count')]")
    private WebElement userCountCard;
    
    @FindBy(xpath = "//div[contains(@class, 'staff-count')]")
    private WebElement staffCountCard;
    
    @FindBy(xpath = "//div[contains(@class, 'batch-count')]")
    private WebElement batchCountCard;
    
    @FindBy(xpath = "//div[contains(@class, 'program-count')]")
    private WebElement programCountCard;
    
    @FindBy(xpath = "//table[contains(@class, 'staff-table')]//tr")
    private List<WebElement> staffTableRows;
    
    @FindBy(linkText = "Home")
    private WebElement homeNavLink;
    
    @FindBy(linkText = "Programs")
    private WebElement programsNavLink;
    
    @FindBy(linkText = "Batch")
    private WebElement batchNavLink;
    
    @FindBy(linkText = "Class")
    private WebElement classNavLink;
    
    @FindBy(linkText = "User")
    private WebElement userNavLink;
    
    @FindBy(linkText = "Assignment")
    private WebElement assignmentNavLink;
    
    @FindBy(linkText = "Attendance")
    private WebElement attendanceNavLink;
    
    /**
     * Constructor for DashboardPage
     * @param driver WebDriver instance
     */
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get welcome message text
     * @return Welcome message text
     */
    public String getWelcomeMessage() {
        return waitForElementToBeVisible(welcomeMessage).getText();
    }
    
    /**
     * Get user count
     * @return User count as integer
     */
    public int getUserCount() {
        String countText = waitForElementToBeVisible(userCountCard).getText().trim();
        return extractNumberFromText(countText);
    }
    
    /**
     * Get staff count
     * @return Staff count as integer
     */
    public int getStaffCount() {
        String countText = waitForElementToBeVisible(staffCountCard).getText().trim();
        return extractNumberFromText(countText);
    }
    
    /**
     * Get batch count
     * @return Batch count as integer
     */
    public int getBatchCount() {
        String countText = waitForElementToBeVisible(batchCountCard).getText().trim();
        return extractNumberFromText(countText);
    }
    
    /**
     * Get program count
     * @return Program count as integer
     */
    public int getProgramCount() {
        String countText = waitForElementToBeVisible(programCountCard).getText().trim();
        return extractNumberFromText(countText);
    }
    
    /**
     * Get staff table rows count
     * @return Number of rows in staff table
     */
    public int getStaffTableRowCount() {
        return waitForElementsToBeVisible(staffTableRows).size() - 1; // Subtract header row
    }
    
    /**
     * Extract number from text
     * @param text Text containing a number
     * @return Extracted number as integer
     */
    private int extractNumberFromText(String text) {
        try {
            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    /**
     * Navigate to Programs page
     * @return ProgramsPage instance
     */
    public ProgramsPage navigateToPrograms() {
        waitForElementToBeClickable(programsNavLink).click();
        return new ProgramsPage(driver);
    }
    
    /**
     * Navigate to Batch page
     * @return BatchPage instance
     */
    public BatchPage navigateToBatch() {
        waitForElementToBeClickable(batchNavLink).click();
        return new BatchPage(driver);
    }
    
    /**
     * Navigate to Class page
     * @return ClassPage instance
     */
    public ClassPage navigateToClass() {
        waitForElementToBeClickable(classNavLink).click();
        return new ClassPage(driver);
    }
    
    /**
     * Navigate to User page
     * @return UserPage instance
     */
    public UserPage navigateToUser() {
        waitForElementToBeClickable(userNavLink).click();
        return new UserPage(driver);
    }
    
    /**
     * Navigate to Assignment page
     * @return AssignmentPage instance
     */
    public AssignmentPage navigateToAssignment() {
        waitForElementToBeClickable(assignmentNavLink).click();
        return new AssignmentPage(driver);
    }
    
    /**
     * Navigate to Attendance page
     * @return AttendancePage instance
     */
    public AttendancePage navigateToAttendance() {
        waitForElementToBeClickable(attendanceNavLink).click();
        return new AttendancePage(driver);
    }
    
    /**
     * Check if user is on dashboard page
     * @return true if dashboard page is displayed, false otherwise
     */
    public boolean isDashboardDisplayed() {
        return isElementDisplayed(welcomeMessage);
    }
}