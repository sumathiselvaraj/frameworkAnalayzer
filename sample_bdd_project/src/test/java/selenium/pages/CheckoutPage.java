package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the checkout page
 */
public class CheckoutPage extends BasePage {
    // Page elements
    @FindBy(id = "shipping-address-form")
    private WebElement shippingAddressForm;
    
    @FindBy(id = "shipping-first-name")
    private WebElement firstNameField;
    
    @FindBy(id = "shipping-last-name")
    private WebElement lastNameField;
    
    @FindBy(id = "shipping-address-line1")
    private WebElement addressLine1Field;
    
    @FindBy(id = "shipping-city")
    private WebElement cityField;
    
    @FindBy(id = "shipping-state")
    private WebElement stateField;
    
    @FindBy(id = "shipping-zip")
    private WebElement zipField;
    
    @FindBy(id = "shipping-country")
    private WebElement countryField;
    
    @FindBy(id = "billing-same-as-shipping")
    private WebElement sameAsShippingCheckbox;
    
    @FindBy(id = "payment-card-number")
    private WebElement cardNumberField;
    
    @FindBy(id = "payment-expiry")
    private WebElement expiryField;
    
    @FindBy(id = "payment-cvv")
    private WebElement cvvField;
    
    @FindBy(id = "place-order-button")
    private WebElement placeOrderButton;
    
    @FindBy(id = "return-to-cart")
    private WebElement returnToCartButton;
    
    @FindBy(className = "error-message")
    private List<WebElement> errorMessages;
    
    /**
     * Constructor for CheckoutPage
     * @param driver WebDriver instance
     */
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Fill shipping address form
     * @param firstName First name
     * @param lastName Last name
     * @param addressLine1 Address line 1
     * @param city City
     * @param state State
     * @param zip ZIP code
     * @param country Country
     * @return CheckoutPage instance for method chaining
     */
    public CheckoutPage fillShippingAddress(String firstName, String lastName, String addressLine1,
                                           String city, String state, String zip, String country) {
        clearAndType(firstNameField, firstName);
        clearAndType(lastNameField, lastName);
        clearAndType(addressLine1Field, addressLine1);
        clearAndType(cityField, city);
        clearAndType(stateField, state);
        clearAndType(zipField, zip);
        clearAndType(countryField, country);
        return this;
    }
    
    /**
     * Toggle same as shipping checkbox
     * @return CheckoutPage instance for method chaining
     */
    public CheckoutPage toggleSameAsShipping() {
        waitForElementToBeClickable(sameAsShippingCheckbox).click();
        return this;
    }
    
    /**
     * Fill payment information
     * @param cardNumber Card number
     * @param expiry Expiry date (MM/YY)
     * @param cvv CVV code
     * @return CheckoutPage instance for method chaining
     */
    public CheckoutPage fillPaymentInformation(String cardNumber, String expiry, String cvv) {
        clearAndType(cardNumberField, cardNumber);
        clearAndType(expiryField, expiry);
        clearAndType(cvvField, cvv);
        return this;
    }
    
    /**
     * Click place order button
     * @return OrderConfirmationPage instance
     */
    public OrderConfirmationPage clickPlaceOrder() {
        waitForElementToBeClickable(placeOrderButton).click();
        return new OrderConfirmationPage(driver);
    }
    
    /**
     * Click return to cart button
     * @return CartPage instance
     */
    public CartPage clickReturnToCart() {
        waitForElementToBeClickable(returnToCartButton).click();
        return new CartPage(driver);
    }
    
    /**
     * Check if there are validation errors
     * @return true if there are validation errors, false otherwise
     */
    public boolean hasValidationErrors() {
        return !waitForElementsToBeVisible(errorMessages).isEmpty();
    }
    
    /**
     * Get all error messages
     * @return List of error message texts
     */
    public List<String> getErrorMessages() {
        List<WebElement> errors = waitForElementsToBeVisible(errorMessages);
        return errors.stream().map(WebElement::getText).toList();
    }
}