package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the shopping cart page
 */
public class CartPage extends BasePage {
    // Page elements
    @FindBy(className = "cart-item")
    private List<WebElement> cartItems;
    
    @FindBy(id = "subtotal")
    private WebElement subtotalLabel;
    
    @FindBy(id = "tax")
    private WebElement taxLabel;
    
    @FindBy(id = "total")
    private WebElement totalLabel;
    
    @FindBy(id = "proceed-to-checkout")
    private WebElement proceedToCheckoutButton;
    
    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;
    
    @FindBy(className = "remove-item")
    private List<WebElement> removeItemButtons;
    
    @FindBy(className = "quantity-input")
    private List<WebElement> quantityInputs;
    
    @FindBy(className = "update-quantity")
    private List<WebElement> updateQuantityButtons;
    
    /**
     * Constructor for CartPage
     * @param driver WebDriver instance
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Get cart item count
     * @return Number of items in the cart
     */
    public int getCartItemCount() {
        return waitForElementsToBeVisible(cartItems).size();
    }
    
    /**
     * Get subtotal text
     * @return Subtotal text
     */
    public String getSubtotal() {
        return waitForElementToBeVisible(subtotalLabel).getText();
    }
    
    /**
     * Get tax text
     * @return Tax text
     */
    public String getTax() {
        return waitForElementToBeVisible(taxLabel).getText();
    }
    
    /**
     * Get total text
     * @return Total text
     */
    public String getTotal() {
        return waitForElementToBeVisible(totalLabel).getText();
    }
    
    /**
     * Click proceed to checkout button
     * @return CheckoutPage instance
     */
    public CheckoutPage clickProceedToCheckout() {
        waitForElementToBeClickable(proceedToCheckoutButton).click();
        return new CheckoutPage(driver);
    }
    
    /**
     * Click continue shopping button
     * @return HomePage instance
     */
    public HomePage clickContinueShopping() {
        waitForElementToBeClickable(continueShoppingButton).click();
        return new HomePage(driver);
    }
    
    /**
     * Remove item by index
     * @param index Index of item to remove (0-based)
     * @return CartPage instance for method chaining
     */
    public CartPage removeItem(int index) {
        waitForElementsToBeVisible(removeItemButtons);
        if (index < removeItemButtons.size()) {
            waitForElementToBeClickable(removeItemButtons.get(index)).click();
            return this;
        }
        throw new IndexOutOfBoundsException("Remove item index out of bounds");
    }
    
    /**
     * Update quantity for item by index
     * @param index Index of item to update (0-based)
     * @param quantity New quantity
     * @return CartPage instance for method chaining
     */
    public CartPage updateQuantity(int index, int quantity) {
        waitForElementsToBeVisible(quantityInputs);
        if (index < quantityInputs.size()) {
            clearAndType(quantityInputs.get(index), String.valueOf(quantity));
            waitForElementToBeClickable(updateQuantityButtons.get(index)).click();
            return this;
        }
        throw new IndexOutOfBoundsException("Update quantity index out of bounds");
    }
}