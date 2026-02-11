package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Checkout Page - Handles checkout process
 */
public class CheckoutPage extends BasePage {

    // Encapsulated locators
    private final By deliveryAddress = By.xpath("//h3[text()='Your delivery address']");
    private final By billingAddress = By.cssSelector("ul.address.invoice");
    private final By placeOrderButton = By.xpath("//a[contains(@href,'/payment') and contains(text(),'Place Order')]");
    private final By proceedToCheckoutButton = By.xpath("//a[contains(@class,'check_out') and contains(text(),'Proceed To Checkout')]");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify delivery address is displayed
     * @return true if delivery address is visible
     */
    public boolean isDeliveryAddressVisible() {
        try {
            return isDisplayed(deliveryAddress);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verify billing address is displayed
     * @return true if billing address is visible
     */
    public boolean isBillingAddressVisible() {
        try {
            return isDisplayed(billingAddress);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click proceed to checkout if available
     */
    public void clickProceedToCheckout() {
        try {
            if (isDisplayed(proceedToCheckoutButton)) {
                click(proceedToCheckoutButton);
            }
        } catch (Exception e) {
            // Button not present, continue
        }
    }

    /**
     * Place order and navigate to payment page
     * @return PaymentPage object
     */
    public PaymentPage placeOrder() {
        
        // First try to proceed to checkout if button is visible
        clickProceedToCheckout();
        
        // Then click place order
        scrollToElement(placeOrderButton);
        waitForElementToBeClickable(placeOrderButton);
        click(placeOrderButton);
        
        return new PaymentPage(driver);
    }
}
