package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Gmail Login Page
 * Handles login actions for Gmail using Page Object Model
 */
public class GmailLoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By emailField = By.id("identifierId");
    private By nextButton = By.id("identifierNext");
    private By passwordField = By.xpath("//input[@name='Passwd']");
    private By passwordNext = By.id("passwordNext");

    public GmailLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    public void clickNextEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    public void clickNextPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(passwordNext)).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        clickNextEmail();
        enterPassword(password);
        clickNextPassword();
    }
}
