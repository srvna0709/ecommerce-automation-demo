package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Payment Page - Handles payment information and order confirmation
 */
public class PaymentPage extends BasePage {

    // Encapsulated locators for payment form
    private final By nameOnCardInput = By.cssSelector("input[name='name_on_card'][data-qa='name-on-card']");
    private final By cardNumberInput = By.cssSelector("input[name='card_number'][data-qa='card-number']");
    private final By cvcInput = By.cssSelector("input[name='cvc'][data-qa='cvc']");
    private final By expiryMonthInput = By.cssSelector("input[name='expiry_month'][data-qa='expiry-month']");
    private final By expiryYearInput = By.cssSelector("input[name='expiry_year'][data-qa='expiry-year']");
    private final By payConfirmButton = By.cssSelector("button[data-qa='pay-button']");
    private final By orderSuccessMessage = By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]");
    private final By downloadInvoiceButton = By.xpath("//a[contains(@href,'/download_invoice') and contains(text(),'Download Invoice')]");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enter payment details and confirm order
     * @param cardName name on card
     * @param cardNumber card number
     * @param cvc CVC code
     * @param expiryMonth expiry month (MM)
     * @param expiryYear expiry year (YYYY)
     */
    public void enterPaymentDetails(String cardName, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        waitForElementToBeVisible(nameOnCardInput);
        
        type(nameOnCardInput, cardName);
        type(cardNumberInput, cardNumber);
        type(cvcInput, cvc);
        type(expiryMonthInput, expiryMonth);
        type(expiryYearInput, expiryYear);
    }

    /**
     * Click pay and confirm order button
     */
    public void clickPayAndConfirm() {
        scrollToElement(payConfirmButton);
        click(payConfirmButton);
        

    }

    /**
     * Complete payment process
     * @param cardName name on card
     * @param cardNumber card number
     * @param cvc CVC code
     * @param expiryMonth expiry month
     * @param expiryYear expiry year
     */
    public void pay(String cardName, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        enterPaymentDetails(cardName, cardNumber, cvc, expiryMonth, expiryYear);
        clickPayAndConfirm();
    }

    /**
     * Verify order success message is displayed
     * @return true if success message is visible
     */
    public boolean isOrderSuccessMessageDisplayed() {
        return isDisplayed(orderSuccessMessage);
    }

    /**
     * Verify download invoice button is available
     * @return true if download invoice button is visible
     */
    public boolean isDownloadInvoiceVisible() {
        return isDisplayed(downloadInvoiceButton);
    }

    /**
     * Get order success message text
     * @return success message text
     */
    public String getOrderSuccessMessage() {
        return getText(orderSuccessMessage);
    }
}
