package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Cart Page - Handles cart and checkout operations
 */
public class CartPage extends BasePage {

    // Encapsulated locators
    private final By proceedToCheckoutButton = By.xpath("//a[contains(@class,'check_out') and contains(text(),'Proceed To Checkout')]");
    private final By registerLoginLink = By.xpath("//u[contains(text(),'Register / Login')]");
    private final By cartProducts = By.cssSelector("tr[id^='product-']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify cart has products
     * @return true if products are present in cart
     */
    public boolean areProductsInCart() {
        try {
            return driver.findElements(cartProducts).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click proceed to checkout button
     * This may lead to login page if not logged in
     */
    public void clickProceedToCheckout() {
        scrollToElement(proceedToCheckoutButton);
        click(proceedToCheckoutButton);
    }

    /**
     * Navigate to login page for checkout
     * @return LoginPage object
     */
    public LoginPage goToLoginForCheckout() {
        clickProceedToCheckout();
        
        // Check if Register/Login link appears
        try {
            if (isDisplayed(registerLoginLink)) {
                click(registerLoginLink);
            }
        } catch (Exception e) {
            // User might already be logged in
        }
        
        return new LoginPage(driver);
    }

    /**
     * Proceed to checkout (when already logged in)
     * @return CheckoutPage object
     */
    public CheckoutPage proceedToCheckout() {
        clickProceedToCheckout();
        return new CheckoutPage(driver);
    }
}
