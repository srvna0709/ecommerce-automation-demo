package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * View & Cart Brand Products Test - Test brand product navigation
 */
public class ViewCartBrandProductsTest extends BaseTest {

    @Test(priority = 3, description = "View & Cart Brand Products")
    public void testViewCartBrandProducts() {

        SoftAssert softAssert = new SoftAssert();

        System.out.println("\n========== STARTING VIEW BRAND PRODUCTS TEST ==========");

        // Step 1-2: Navigate to Products page
        System.out.println("\n--- Step 1-2: Navigate to Products Page ---");
        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(homePage.isHomePageVisible(),
                "FAILED: Home page is not visible");
        System.out.println("✓ Homepage is visible");

        ProductsPage productsPage = homePage.clickProducts();

        softAssert.assertTrue(productsPage.isAllProductsVisible(),
                "FAILED: ALL PRODUCTS page is not visible");
        System.out.println("✓ ALL PRODUCTS page is visible");

        // Step 3: Verify Brands section is visible
        System.out.println("\n--- Step 3: Verify Brands Section ---");
        softAssert.assertTrue(productsPage.isBrandsSectionVisible(),
                "FAILED: Brands section is not visible on left sidebar");
        System.out.println("✓ Brands section is visible on left sidebar");

        // Step 4: Click on Polo brand
        System.out.println("\n--- Step 4: Navigate to Polo Brand ---");
        BrandPage brandPage = productsPage.clickPoloBrand();
        System.out.println("✓ Clicked on Polo brand");

        // Step 5: Verify Polo brand page
        System.out.println("\n--- Step 5: Verify Polo Brand Page ---");
        softAssert.assertTrue(brandPage.isPoloBrandPageDisplayed(),
                "FAILED: Polo brand page is not displayed");
        System.out.println("✓ Navigated to Polo brand page");

        softAssert.assertTrue(brandPage.areBrandProductsDisplayed(),
                "FAILED: Polo brand products are not displayed");
        System.out.println("✓ Polo brand products are displayed");

        // Step 6: Click View Product and verify brand name
        System.out.println("\n--- Step 6: Verify Product Brand Name ---");
        ProductDetailPage productDetailPage = brandPage.clickViewProduct();
        System.out.println("✓ Clicked on View Product");

        softAssert.assertTrue(productDetailPage.verifyBrandName("Polo"),
                "FAILED: Brand name Polo is not displayed on product details");
        System.out.println("✓ Brand name verified: " + productDetailPage.getBrandName());

        // Navigate back to products page for next brand
        driver.navigate().back();
        driver.navigate().back();
        productsPage = new ProductsPage(driver);

        // Step 7: Click on H&M brand
        System.out.println("\n--- Step 7: Navigate to H&M Brand ---");
        brandPage = productsPage.clickHMBrand();
        System.out.println("✓ Clicked on H&M brand");

        // Step 8: Verify H&M brand page
        System.out.println("\n--- Step 8: Verify H&M Brand Page ---");
        softAssert.assertTrue(brandPage.isHMBrandPageDisplayed(),
                "FAILED: H&M brand page is not displayed");
        System.out.println("✓ Navigated to H&M brand page");

        softAssert.assertTrue(brandPage.areBrandProductsDisplayed(),
                "FAILED: H&M brand products are not displayed");
        System.out.println("✓ H&M brand products are displayed");

        // Verify H&M brand name on product detail
        productDetailPage = brandPage.clickViewProduct();
        System.out.println("✓ Clicked on View Product");

        softAssert.assertTrue(productDetailPage.verifyBrandName("H&M"),
                "FAILED: Brand name H&M is not displayed on product details");
        System.out.println("✓ Brand name verified: " + productDetailPage.getBrandName());

        System.out.println("\n========== VIEW BRAND PRODUCTS TEST COMPLETED ==========\n");

        softAssert.assertAll();
    }
}
