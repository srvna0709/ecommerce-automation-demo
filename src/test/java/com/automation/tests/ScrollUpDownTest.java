package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Scroll Up Down Test - Test scroll functionality with arrow button
 * 
 * Test Flow:
 * 1. Navigate to homepage
 * 2. Verify homepage is visible
 * 3. Scroll down to bottom
 * 4. Verify SUBSCRIPTION is visible
 * 5. Click scroll up arrow button
 * 6. Verify Full-Fledged text is visible at top
 */
public class ScrollUpDownTest extends BaseTest {

    @Test(priority = 4, description = "Verify Scroll Up using Arrow button and Scroll Down functionality")
    public void testScrollUpDownFunctionality() {
        
        SoftAssert softAssert = new SoftAssert();
        
        System.out.println("\n========== STARTING SCROLL UP/DOWN TEST ==========");
        
        // Step 1-2: Verify homepage
        System.out.println("\n--- Step 1-2: Verify Homepage ---");
        HomePage homePage = new HomePage(driver);
        
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "FAILED: Home page is not visible");
        System.out.println("✓ Homepage is visible successfully");
        
        // Step 3: Scroll down to bottom
        System.out.println("\n--- Step 3: Scroll Down to Bottom ---");
        homePage.scrollToBottom();
        System.out.println("✓ Scrolled down to bottom of page");
        
        // Step 4: Verify SUBSCRIPTION is visible
        System.out.println("\n--- Step 4: Verify SUBSCRIPTION Text ---");
        softAssert.assertTrue(homePage.isSubscriptionVisible(), 
            "FAILED: SUBSCRIPTION text is not visible at bottom");
        System.out.println("✓ 'SUBSCRIPTION' text is visible at bottom");
        
        // Step 5: Click on scroll up arrow button
        System.out.println("\n--- Step 5: Click Scroll Up Arrow ---");
        homePage.clickScrollUpButton();
        System.out.println("✓ Clicked on scroll up arrow button");
        
        // Step 6: Verify page scrolled up and Full-Fledged text is visible
        System.out.println("\n--- Step 6: Verify Page Scrolled to Top ---");
        Assert.assertTrue(homePage.isFullFledgedTextVisible(), 
            "FAILED: Page did not scroll up - Full-Fledged text not visible");
        System.out.println("✓ Page scrolled up successfully");
        System.out.println("✓ 'Full-Fledged practice website for Automation Engineers' text is visible");
        
        System.out.println("\n========== SCROLL UP/DOWN TEST COMPLETED ==========\n");
        
        softAssert.assertAll();
    }
}
