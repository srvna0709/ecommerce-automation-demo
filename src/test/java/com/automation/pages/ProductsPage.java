package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

/**
 * Products Page - Handles product search and cart operations
 * Demonstrates Encapsulation and Single Responsibility Principle
 */
public class ProductsPage extends BasePage {

    // Encapsulated locators
    private final By allProductsTitle = By.xpath("//h2[contains(@class,'title') and contains(text(),'All Products')]");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By searchedProductsTitle = By.xpath("//h2[contains(text(),'Searched Products')]");
    private final By sleevelessProductText = By.xpath("//*[contains(text(),'Sleeveless')]");
    
    // Product IDs for adding to cart
    private final By firstProduct = By.cssSelector("a[data-product-id='3']");
    private final By secondProduct = By.cssSelector("a[data-product-id='19']");
    private final By continueShoppingButton = By.xpath("//button[contains(text(),'Continue Shopping')]");
    private final By viewCartLink = By.xpath("//u[contains(text(),'View Cart')]");
    private final By cartLink = By.cssSelector("a[href='/view_cart']");
    
    // Additional locators for new scenarios
    private final By viewProductButton = By.xpath("(//a[contains(text(),'View Product')])[1]");
    private final By brandsSection = By.xpath("//h2[text()='Brands']");
    private final By poloBrandLink = By.xpath("//a[@href='/brand_products/Polo']");
    private final By hmBrandLink = By.xpath("//a[@href='/brand_products/H&M']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify All Products page is loaded
     * @return true if All Products title is visible
     */
    public boolean isAllProductsVisible() {
        return isDisplayed(allProductsTitle);
    }

    /**
     * Search for a product
     * @param productName name of product to search
     */
    public void searchProduct(String productName) {
        type(searchInput, productName);
        click(searchButton);
    }

    /**
     * Verify searched products section is visible
     * @return true if searched products title is displayed
     */
    public boolean isSearchedProductsVisible() {
        return isDisplayed(searchedProductsTitle);
    }

    /**
     * Verify sleeveless product is visible in search results
     * @return true if sleeveless text is found
     */
    public boolean isSleevelessProductVisible() {
        return containsText(sleevelessProductText, "Sleeveless");
    }

    /**
     * Add first product to cart
     */
    public void addFirstProductToCart() {
        scrollToElement(firstProduct);
        click(firstProduct);
        waitForContinueShoppingButton();
    }

    /**
     * Add second product to cart
     */
    public void addSecondProductToCart() {
        scrollToElement(secondProduct);
        click(secondProduct);
        waitForContinueShoppingButton();
    }

    /**
     * Click continue shopping button
     */
    public void clickContinueShopping() {
        click(continueShoppingButton);

    }

    /**
     * Wait for continue shopping button to appear
     */
    private void waitForContinueShoppingButton() {
        waitForElementToBeVisible(continueShoppingButton);
    }

    /**
     * Navigate to cart page
     * @return CartPage object
     */
    public CartPage goToCart() {
        try {
            // Try clicking the view cart link from modal if visible
            if (isDisplayed(viewCartLink)) {
                click(viewCartLink);
            } else {
                click(cartLink);
            }
        } catch (Exception e) {
            // Fallback to cart link in header
            click(cartLink);
        }
        return new CartPage(driver);
    }

    /**
     * Add two products to cart and navigate to cart page
     * This demonstrates a composite action
     * @return CartPage object
     */
    public CartPage addTwoProductsAndGoToCart() {
        addFirstProductToCart();
        clickContinueShopping();
        addSecondProductToCart();
        clickContinueShopping();
        return goToCart();
    }
    
    /**
     * Click on View Product button for first product
     * @return ProductDetailPage object
     */
    public ProductDetailPage clickViewProduct() {
        scrollToElement(viewProductButton);
        click(viewProductButton);
        return new ProductDetailPage(driver);
    }
    
    /**
     * Verify brands section is visible
     * @return true if brands section is displayed
     */
    public boolean isBrandsSectionVisible() {
        return isDisplayed(brandsSection);
    }
    
    /**
     * Click on Polo brand link
     * @return BrandPage object
     */
    public BrandPage clickPoloBrand() {
        scrollToElement(poloBrandLink);
        click(poloBrandLink);
        return new BrandPage(driver);
    }
    
    /**
     * Click on H&M brand link
     * @return BrandPage object
     */
    public BrandPage clickHMBrand() {
        scrollToElement(hmBrandLink);
        click(hmBrandLink);
        return new BrandPage(driver);
    }
}
