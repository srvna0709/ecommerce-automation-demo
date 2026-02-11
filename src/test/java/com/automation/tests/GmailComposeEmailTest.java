package com.automation.tests;

import com.automation.pages.GmailInboxPage;
import com.automation.pages.GmailLoginPage;
import com.automation.pages.GmailComposePage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for Gmail compose and send email
 */
public class GmailComposeEmailTest {

    private WebDriver driver;
    private ConfigReader config;

    @BeforeClass
    public void setUp() {
        config = ConfigReader.getInstance();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.google.com");
    }

    @Test(description = "Compose and send an email from Gmail")
    public void gmailComposeEmailTest() {
        String email = config.getProperty("gmailemail");
        String password = config.getProperty("gmailpassword");
        String recipient = config.getProperty("email"); // your existing recipient

        // Login
        GmailLoginPage loginPage = new GmailLoginPage(driver);
        loginPage.login(email, password);

        // Verify inbox loaded
        GmailInboxPage inboxPage = new GmailInboxPage(driver);
        inboxPage.isInboxLoaded();

        // Compose and send email
        GmailComposePage composePage = new GmailComposePage(driver);
        composePage.composeEmail(recipient, "Test Subject", "Hello, this is a test email!");
        composePage.sendEmail();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
