package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Add Product Review Test - Test for adding review on products
 */
public class AddProductReviewTest extends BaseTest {

    @Test(priority = 2, description = "Add Review on Product")
    public void testAddProductReview() {
        
        SoftAssert softAssert = new SoftAssert();
        
        System.out.println("\n========== STARTING ADD PRODUCT REVIEW TEST ==========");
        
        // Step 1-2: Navigate to homepage and click Products
        System.out.println("\n--- Step 1-2: Navigate to Products Page ---");
        HomePage homePage = new HomePage(driver);
        
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "FAILED: Home page is not visible");
        System.out.println("✓ Homepage is visible");
        
        System.out.println("\n--- Step 3-4: Navigate to Products Page ---");
        ProductsPage productsPage = homePage.clickProducts();
        
        // SOFT ASSERTION - Products page should be visible
        softAssert.assertTrue(productsPage.isAllProductsVisible(), 
            "FAILED: ALL PRODUCTS page is not visible");
        System.out.println("✓ ALL PRODUCTS page is visible");
        
        // Step 3: Click on View Product
        System.out.println("\n--- Step 3: Click View Product ---");
        ProductDetailPage productDetailPage = productsPage.clickViewProduct();
        System.out.println("✓ Clicked on View Product");
        
        // Step 4: Verify Write Your Review is visible
        System.out.println("\n--- Step 4: Verify Write Your Review Section ---");
        softAssert.assertTrue(productDetailPage.isWriteReviewVisible(), 
            "FAILED: Write Your Review section is not visible");
        System.out.println("✓ 'Write Your Review' section is visible");
        
        // Step 5-6: Enter review details and submit
        System.out.println("\n--- Step 5-6: Submit Product Review ---");
        String reviewerName = "Test Reviewer";
        String reviewerEmail = config.getProperty("email");
        String reviewText = "Good Product";
        
        productDetailPage.submitReview(reviewerName, reviewerEmail, reviewText);
        System.out.println("✓ Review submitted with:");
        System.out.println("  - Name: " + reviewerName);
        System.out.println("  - Email: " + reviewerEmail);
        System.out.println("  - Review: " + reviewText);
        
        // Step 7: Success message is already handled inside submitReview()
        System.out.println("\n--- Step 7: Review submission verified ---");
        System.out.println("✓ Review submitted and toaster appeared briefly");

        System.out.println("\n========== ADD PRODUCT REVIEW TEST COMPLETED ==========\n");
        
        softAssert.assertAll();
    }
}
