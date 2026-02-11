package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Gmail Compose Page
 * Handles composing and sending emails
 */
public class GmailComposePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By toField = By.name("to");
    private By subjectField = By.name("subjectbox");
    private By bodyField = By.cssSelector("div[aria-label='Message Body']");
    private By sendButton = By.cssSelector("div[aria-label*='Send']");

    public GmailComposePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * Fill in email details: to, subject, body
     */
    public void composeEmail(String to, String subject, String body) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(toField)).sendKeys(to);
        driver.findElement(subjectField).sendKeys(subject);
        driver.findElement(bodyField).sendKeys(body);
    }

    /**
     * Send the composed email
     */
    public void sendEmail() {
        driver.findElement(sendButton).click();
    }
}
