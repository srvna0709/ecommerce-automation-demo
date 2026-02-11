package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Gmail Inbox Page
 * Handles inbox actions like verifying inbox loaded and reading emails
 */
public class GmailInboxPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By composeButton = By.cssSelector(".T-I.T-I-KE.L3"); // Compose button
    private By firstEmailSubject = By.cssSelector("tr.zA:first-child span.bog"); // First email subject

    public GmailInboxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Verify inbox page loaded successfully
     */
    public boolean isInboxLoaded() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(composeButton)).isDisplayed();
    }

    /**
     * Get subject of the first email in inbox
     */
    public String getFirstEmailSubject() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstEmailSubject)).getText();
    }
}
