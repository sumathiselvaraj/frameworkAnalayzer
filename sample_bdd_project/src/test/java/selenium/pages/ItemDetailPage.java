package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the item detail page
 */
public class ItemDetailPage extends BasePage {
    // Page elements
    @FindBy(id = "item-title")
    private WebElement itemTitle;
    
    @FindBy(id = "item-price")
    private WebElement itemPrice;
    
    @FindBy(id = "item-description")
    private WebElement itemDescription;
    
    @FindBy(className = "item-image")
    private List<WebElement> itemImages;
    
    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartButton;
    
    @FindBy(id = "buy-now-button")
    private WebElement buyNowButton;
    
    @FindBy(id = "quantity-input")
    private WebElement quantityInput;
    
    @FindBy(className = "review-item")
    private List<WebElement> reviewItems;
    
    @FindBy(id = "back-to-results")
    private WebElement backToResultsLink;
    
    /**
     * Constructor for ItemDetailPage
     * @param driver WebDriver instance
     */
    public ItemDetailPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get item title
     * @return Item title
     */
    public String getItemTitle() {
        return waitForElementToBeVisible(itemTitle).getText();
    }
    
    /**
     * Get item price
     * @return Item price
     */
    public String getItemPrice() {
        return waitForElementToBeVisible(itemPrice).getText();
    }
    
    /**
     * Get item description
     * @return Item description
     */
    public String getItemDescription() {
        return waitForElementToBeVisible(itemDescription).getText();
    }
    
    /**
     * Get number of item images
     * @return Number of item images
     */
    public int getItemImageCount() {
        return waitForElementsToBeVisible(itemImages).size();
    }
    
    /**
     * Set quantity
     * @param quantity Quantity to set
     * @return ItemDetailPage instance for method chaining
     */
    public ItemDetailPage setQuantity(int quantity) {
        clearAndType(quantityInput, String.valueOf(quantity));
        return this;
    }
    
    /**
     * Click add to cart button
     * @return CartPage instance
     */
    public CartPage clickAddToCart() {
        waitForElementToBeClickable(addToCartButton).click();
        return new CartPage(driver);
    }
    
    /**
     * Click buy now button
     * @return CheckoutPage instance
     */
    public CheckoutPage clickBuyNow() {
        waitForElementToBeClickable(buyNowButton).click();
        return new CheckoutPage(driver);
    }
    
    /**
     * Get review count
     * @return Number of reviews
     */
    public int getReviewCount() {
        return waitForElementsToBeVisible(reviewItems).size();
    }
    
    /**
     * Click back to results link
     * @return SearchResultsPage instance
     */
    public SearchResultsPage clickBackToResults() {
        waitForElementToBeClickable(backToResultsLink).click();
        return new SearchResultsPage(driver);
    }
}