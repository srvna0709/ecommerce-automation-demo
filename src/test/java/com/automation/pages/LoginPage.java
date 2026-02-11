package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Login Page - Handles user authentication
 */
public class LoginPage extends BasePage {

    // Encapsulated locators for login form
    private final By loginEmailInput = By.cssSelector("input[data-qa='login-email']");
    private final By loginPasswordInput = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa='login-button']");
    private final By loggedInUser = By.xpath("//a[contains(text(),'Logged in as')]");
    private final By logoutLink = By.xpath("//a[contains(text(),'Logout')]");
    private final By loginAccountTitle = By.xpath("//h2[contains(text(),'Login to your account')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Login with email and password
     * @param email user email
     * @param password user password
     */
    public void login(String email, String password) {
        waitForElementToBeVisible(loginEmailInput);
        type(loginEmailInput, email);
        type(loginPasswordInput, password);
        click(loginButton);

    }

    /**
     * Verify if user is logged in
     * @return true if logged in as text is visible
     */
    public boolean isUserLoggedIn() {
        try {
            return isDisplayed(loggedInUser);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Navigate to cart after login
     * @return CartPage object
     */
    public CartPage goToCart() {
        By cartLink = By.cssSelector("a[href='/view_cart']");
        click(cartLink);
        return new CartPage(driver);
    }
    
    /**
     * Verify user is logged out (login page is displayed)
     * @return true if Login to your account title is visible
     */
    public boolean isUserLoggedOut() {
        return isDisplayed(loginAccountTitle);
    }
}
