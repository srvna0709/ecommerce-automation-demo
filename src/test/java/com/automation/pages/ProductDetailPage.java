package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Product Detail Page - Handles product details and review functionality
 */
public class ProductDetailPage extends BasePage {

    // Encapsulated locators
    private final By writeReviewLink = By.xpath("//a[@href='#reviews' and contains(text(),'Write Your Review')]");
    private final By reviewNameInput = By.id("name");
    private final By reviewEmailInput = By.id("email");
    private final By reviewTextArea = By.id("review");
    private final By submitReviewButton = By.id("button-review");
    private final By reviewSuccessMessage = By.xpath("//span[contains(text(),'Thank you for your review.')]");
    private final By brandName = By.xpath("//p[starts-with(normalize-space(.),'Brand:')]");


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify Write Your Review section is visible
     * @return true if write review link is displayed
     */
    public boolean isWriteReviewVisible() {
        scrollToElement(writeReviewLink);
        return isDisplayed(writeReviewLink);
    }

    /**
     * Submit a product review
     * @param name reviewer name
     * @param email reviewer email
     * @param review review text
     */
    public void submitReview(String name, String email, String review) {
        scrollToElement(writeReviewLink);

        type(reviewNameInput, name);
        type(reviewEmailInput, email);
        type(reviewTextArea, review);
        click(submitReviewButton);

        // Minimal wait for success toaster (to handle fast disappearing message)
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.visibilityOfElementLocated(reviewSuccessMessage));
            System.out.println("Review submitted successfully");
        } catch (Exception e) {
            System.out.println("Review success message did not appear");
        }
    }

    /**
     * Get brand name from product details
     * @return brand name text
     */
    public String getBrandName() {
    String fullText = getText(brandName); // e.g. "Brand: Polo"
    if (fullText.contains(":")) {
        return fullText.split(":")[1].trim(); // returns "Polo"
    }
    return fullText.trim();
}


    /**
     * Verify if brand name contains expected text
     * @param expectedBrand expected brand name
     * @return true if brand name matches
     */
    public boolean verifyBrandName(String expectedBrand) {
    String actualBrand = getBrandName();
    return actualBrand.equalsIgnoreCase(expectedBrand);
}

}
