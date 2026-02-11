package com.automation.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base Page class with encapsulated common actions
 * Minimal changes for speed
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    private static final int DEFAULT_TIMEOUT = 5;  // Reduced from 10 to 5
    private static final int SHORT_TIMEOUT = 2;     // For quick checks

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT));
    }

    /**
     * Click element with wait
     */
    protected void click(By locator) {
        WebElement element = waitForElementToBeClickable(locator); // use returned element
        element.click();
    }

    /**
     * Type text into input field
     */
    protected void type(By locator, String value) {
        WebElement element = waitForElementToBeVisible(locator); // use returned element
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Check if element is displayed
     */
    protected boolean isDisplayed(By locator) {
        try {
            WebElement element = waitForElementToBeVisible(locator); // use returned element
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Get text from element
     */
    protected String getText(By locator) {
        WebElement element = waitForElementToBeVisible(locator); // use returned element
        return element.getText();
    }

    /**
     * Wait for element to be visible
     */
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     */
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Scroll to element
     */
    protected void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Check if element contains text
     */
    protected boolean containsText(By locator, String expectedText) {
        try {
            String actualText = getText(locator);
            return actualText.toLowerCase().contains(expectedText.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Quick element presence check
     */
    protected boolean isElementPresent(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
