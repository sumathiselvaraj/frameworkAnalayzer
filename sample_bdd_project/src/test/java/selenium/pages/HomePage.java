package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page object for the home page
 */
public class HomePage extends BasePage {
    // Page elements
    @FindBy(id = "search-input")
    private WebElement searchInput;
    
    @FindBy(id = "search-button")
    private WebElement searchButton;
    
    @FindBy(id = "login-link")
    private WebElement loginLink;
    
    @FindBy(id = "register-link")
    private WebElement registerLink;
    
    @FindBy(id = "cart-icon")
    private WebElement cartIcon;
    
    @FindBy(className = "featured-product")
    private List<WebElement> featuredProducts;
    
    @FindBy(className = "category-item")
    private List<WebElement> categoryItems;
    
    @FindBy(id = "banner-image")
    private WebElement bannerImage;
    
    /**
     * Constructor for HomePage
     * @param driver WebDriver instance
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigate to home page
     * @param baseUrl Base URL of the application
     * @return HomePage instance for method chaining
     */
    public HomePage navigateTo(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }
    
    /**
     * Perform search
     * @param searchTerm Search term to enter
     * @return SearchResultsPage instance
     */
    public SearchResultsPage performSearch(String searchTerm) {
        clearAndType(searchInput, searchTerm);
        waitForElementToBeClickable(searchButton).click();
        return new SearchResultsPage(driver);
    }
    
    /**
     * Click login link
     * @return LoginPage instance
     */
    public LoginPage clickLoginLink() {
        waitForElementToBeClickable(loginLink).click();
        return new LoginPage(driver);
    }
    
    /**
     * Click register link
     * @return RegisterPage instance
     */
    public RegisterPage clickRegisterLink() {
        waitForElementToBeClickable(registerLink).click();
        return new RegisterPage(driver);
    }
    
    /**
     * Click cart icon
     * @return CartPage instance
     */
    public CartPage clickCartIcon() {
        waitForElementToBeClickable(cartIcon).click();
        return new CartPage(driver);
    }
    
    /**
     * Click featured product by index
     * @param index Index of featured product to click (0-based)
     * @return ItemDetailPage instance
     */
    public ItemDetailPage clickFeaturedProduct(int index) {
        waitForElementsToBeVisible(featuredProducts);
        if (index < featuredProducts.size()) {
            scrollToElement(featuredProducts.get(index));
            waitForElementToBeClickable(featuredProducts.get(index)).click();
            return new ItemDetailPage(driver);
        }
        throw new IndexOutOfBoundsException("Featured product index out of bounds");
    }
    
    /**
     * Click category by index
     * @param index Index of category to click (0-based)
     * @return CategoryPage instance
     */
    public CategoryPage clickCategory(int index) {
        waitForElementsToBeVisible(categoryItems);
        if (index < categoryItems.size()) {
            scrollToElement(categoryItems.get(index));
            waitForElementToBeClickable(categoryItems.get(index)).click();
            return new CategoryPage(driver);
        }
        throw new IndexOutOfBoundsException("Category index out of bounds");
    }
    
    /**
     * Check if home page is loaded
     * @return true if home page is loaded, false otherwise
     */
    public boolean isHomePageLoaded() {
        return isElementDisplayed(bannerImage);
    }
}