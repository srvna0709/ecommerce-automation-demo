package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Home Page - Encapsulates homepage elements and actions
 * Demonstrates Encapsulation: Private locators, public methods
 */
public class HomePage extends BasePage {

    // Encapsulated locators - private access
    private final By homeIcon = By.cssSelector("a[href='/'] i.fa-home");
    private final By productsLink = By.cssSelector("a[href='/products']");
    private final By cartLink = By.cssSelector("a[href='/view_cart']");
    private final By testCasesLink = By.xpath("//a[@href='/test_cases']");
    private final By logoutLink = By.xpath("//a[@href='/logout']");
    private final By subscriptionText = By.xpath("//h2[text()='Subscription']");
    private final By scrollUpButton = By.id("scrollUp");
    private final By fullFledgedText = By.xpath("//div[contains(@class,'item') and contains(@class,'active')]//h2[contains(text(),'Full-Fledged practice website')]");
    private final By signupLoginLink = By.xpath("//a[@href='/login' and contains(.,'Signup')]");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    /** Verify if home page is visible */
    public boolean isHomePageVisible() {
        return isDisplayed(homeIcon);
    }

    /** Click on Products button */
    public ProductsPage clickProducts() {
        click(productsLink);
        return new ProductsPage(driver);
    }

    /** Navigate to cart page */
    public CartPage clickCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    /** Navigate to Test Cases page */
    public TestCasesPage clickTestCases() {
        click(testCasesLink);
        return new TestCasesPage(driver);
    }

    /** Navigate to Signup / Login page */
    public LoginPage clickSignupLogin() {
        waitForElementToBeClickable(signupLoginLink);
        click(signupLoginLink);
        return new LoginPage(driver);
    }


    /** Click logout button */
    public LoginPage clickLogout() {
    waitForElementToBeClickable(logoutLink);
    click(logoutLink);
    return new LoginPage(driver);
}

    /** Scroll to bottom of page */
    public void scrollToBottom() {
        ((JavascriptExecutor) driver)
            .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /** Verify subscription text is visible */
    public boolean isSubscriptionVisible() {
        return isDisplayed(subscriptionText);
    }

    /** Click scroll up arrow button and scroll to top */
    public void clickScrollUpButton() {
        waitForElementToBeClickable(scrollUpButton);
        click(scrollUpButton);

        // Simple JS scroll to top
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    /** Verify Full-Fledged text is visible at top */
    public boolean isFullFledgedTextVisible() {
        // Ensure the element is scrolled into view
        ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(fullFledgedText));
        return isDisplayed(fullFledgedText);
    }

    public boolean isUserLoggedIn() {
    return isElementPresent(logoutLink);
}

}
