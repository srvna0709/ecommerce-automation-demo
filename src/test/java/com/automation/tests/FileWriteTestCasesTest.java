package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.TestCasesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.File;

/**
 * File Write Test Cases Test - Extract test cases and store in project folder
 */
public class FileWriteTestCasesTest extends BaseTest {

    @Test(priority = 5, description = "Extract Test Cases and Write to File")
    public void testFileWriteTestCases() {

        SoftAssert softAssert = new SoftAssert();

        System.out.println("\n========== STARTING FILE WRITE TEST CASES TEST ==========");

        // Step 1-2: Verify homepage
        System.out.println("\n--- Step 1-2: Verify Homepage ---");
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isHomePageVisible(),
                "FAILED: Home page is not visible");
        System.out.println("✓ Homepage is visible successfully");

        // Step 3: Click on Test Cases
        System.out.println("\n--- Step 3: Navigate to Test Cases Page ---");
        homePage.clickTestCases();   
        TestCasesPage testCasesPage = homePage.clickTestCases();
        System.out.println("✓ Clicked on Test Cases link");

        softAssert.assertTrue(testCasesPage.isTestCasesPageDisplayed(),
                "FAILED: Test Cases page is not displayed");
        System.out.println("✓ Test Cases page is displayed");


        

        // Step 4-5: Extract and write test cases to file
        System.out.println("\n--- Step 4-5: Extract and Write Test Cases ---");
        String filename = "TestCases_" + System.currentTimeMillis() + ".txt";

        boolean fileWritten = testCasesPage.writeTestCasesToFile(filename);

        Assert.assertTrue(fileWritten,
                "FAILED: Test cases file was not created");
        System.out.println("✓ Test cases extracted and written to file");

        // Step 6: Verify file exists
        System.out.println("\n--- Step 6: Verify File Created ---");
        String filePath = testCasesPage.getTestCasesFilePath(filename);
        File testCasesFile = new File(filePath);

        softAssert.assertTrue(testCasesFile.exists(),
                "FAILED: Test cases file does not exist at: " + filePath);
        System.out.println("✓ File exists at: " + filePath);
        System.out.println("✓ File size: " + testCasesFile.length() + " bytes");

        
        if (!homePage.isUserLoggedIn()) {
            System.out.println("⚠ User not logged in. Logging in before logout");

            LoginPage loginPage = homePage.clickSignupLogin();
            loginPage.login(
                    config.getProperty("email"),
                    config.getProperty("password")
            );

            Assert.assertTrue(loginPage.isUserLoggedIn(),
                    "FAILED: Login failed before logout");
            System.out.println("✓ User logged in successfully");
        }

        // Step 7: Click Logout
        System.out.println("\n--- Step 7: Logout User ---");
        LoginPage loginPage = homePage.clickLogout();
        System.out.println("✓ Clicked on Logout button");

        // Step 8: Verify user is logged out
        System.out.println("\n--- Step 8: Verify User Logged Out ---");
        Assert.assertTrue(loginPage.isUserLoggedOut(),
                "FAILED: User is not logged out - Login page not displayed");
        System.out.println("✓ User is successfully logged out");

        System.out.println("\n========== FILE WRITE TEST CASES TEST COMPLETED ==========\n");

        softAssert.assertAll();
    }
}
