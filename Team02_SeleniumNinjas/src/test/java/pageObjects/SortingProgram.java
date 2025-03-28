 package pageObjects;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortingProgram {
        WebDriver driver;
        WebDriver wait;

        public SortingProgram(WebDriver driver) {
            this.driver = driver;
            
        }

        // Method to get data from a Program column
        public List<String> getColumnData() {
            List<WebElement> columnElements = driver.findElements(By.xpath("//table//tr//td[contains(@psortablecolumn,'programName')]"));
            return columnElements.stream().map(WebElement::getText).collect(Collectors.toList());
        }
        
//        public List<String> getColumnData() {
//            // Adjust the XPath to select the data (td) elements that contain the program names
//            List<WebElement> columnElements = driver.findElements(By.xpath("//table//tr/td[contains(@class, 'programName')]"));
//            return columnElements.stream().map(WebElement::getText).collect(Collectors.toList());
//        }
        public void clickSortArrow() {
            WebElement sortArrow = driver.findElement(By.xpath("//th[@psortablecolumn='programName']//i[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']"));
            
            // Execute JavaScript to click the element directly
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", sortArrow);
        }
        
        // Method to get data from a Description column
        public List<String> getDescriptionColumnData() {
            List<WebElement> columnElements = driver.findElements(By.xpath("//th[@role='columnheader' and @psortablecolumn='programDescription']"));
            return columnElements.stream().map(WebElement::getText).collect(Collectors.toList());
        }
        public void clickDescriptionSortArrow() {
            WebElement sortArrow = driver.findElement(By.xpath("//th[@psortablecolumn='programDescription']//i[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']"));
            
            // Execute JavaScript to click the element directly
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", sortArrow);
        }
        
        // Method to get data from a Status column
        public List<String> getStatusColumnData() {
            List<WebElement> columnElements = driver.findElements(By.xpath("//th[@role='columnheader' and @psortablecolumn='Status']"));
            return columnElements.stream().map(WebElement::getText).collect(Collectors.toList());
        }
        public void clickStatusSortArrow() {
            WebElement sortArrow = driver.findElement(By.xpath("//th[@psortablecolumn='programStatus']//i[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']"));
            
            // Execute JavaScript to click the element directly
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript("arguments[0].click();", sortArrow);
        }
       
     // Method to check if the list is sorted in Ascending Order
        public boolean isSortedAscending(List<String> list) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareToIgnoreCase(list.get(i + 1)) > 0) {
                    return false; // Not sorted
                }
            }
            return true; // Sorted
        }

        // Method to check if the list is sorted in Descending Order
        public boolean isSortedDescending(List<String> list) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareToIgnoreCase(list.get(i + 1)) < 0) {
                    return false; // Not sorted
                }
            }
            return true; // Sorted
        }
    }
        
//        public List<String> getColumnData(String columnName) {
//            List<WebElement> columnElements = driver.findElements(By.xpath("//th[@role='columnheader' and @psortablecolumn='" + columnName + "']"));
//            return columnElements.stream().map(WebElement::getText).collect(Collectors.toList());
//        }
//
//        public void clickSortArrow(String columnName) {
//            WebElement sortArrow = driver.findElement(By.xpath("//th[contains(text(),'" + columnName + "']//i[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']"));
//            
//            // Execute JavaScript to click the element directly
//            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//            jsExecutor.executeScript("arguments[0].click();", sortArrow);
//        }


//       

