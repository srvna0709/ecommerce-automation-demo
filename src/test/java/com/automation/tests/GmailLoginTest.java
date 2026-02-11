package com.automation.tests;

import com.automation.pages.GmailLoginPage;
import com.automation.pages.GmailInboxPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Gmail login
 * Uses GmailLoginPage and GmailInboxPage
 */
public class GmailLoginTest {

    private WebDriver driver;
    private ConfigReader config;

    @BeforeClass
    public void setUp() {
        config = ConfigReader.getInstance(); // get singleton instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.google.com");
    }

    @Test(description = "Verify Gmail login with valid credentials")
    public void gmailLoginTest() {
        String email = config.getProperty("gmailemail");
        String password = config.getProperty("gmailpassword");

        GmailLoginPage loginPage = new GmailLoginPage(driver);
        loginPage.login(email, password);

        GmailInboxPage inboxPage = new GmailInboxPage(driver);
        Assert.assertTrue(inboxPage.isInboxLoaded(), "Inbox should be loaded after login");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
