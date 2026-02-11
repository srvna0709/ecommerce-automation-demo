package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Product Order Test - Complete E2E test for product ordering flow
 * Demonstrates:
 * - TestNG annotations (@Test, @DataProvider, etc.)
 * - Hard and Soft assertions
 * - Page Object Model
 * - OOP principles (Inheritance from BaseTest)
 * 
 * Test Flow:
 * 1. Navigate to homepage
 * 2. Verify homepage is visible
 * 3. Click on Products
 * 4. Verify ALL PRODUCTS page
 * 5. Search for 'Sleeveless' product
 * 6. Verify SEARCHED PRODUCTS is visible
 * 7. Add two products to cart
 * 8. Go to cart
 * 9. Proceed to checkout
 * 10. Login/Register
 * 11. Proceed to checkout again
 * 12. Place order
 * 13. Enter payment details
 * 14. Verify order confirmation
 * 15. Download invoice
 * 16. Verify invoice content
 */
public class ProductOrderTest extends BaseTest {

    /**
     * Data provider for product search
     * @return test data array
     */
    @DataProvider(name = "productSearchData")
    public Object[][] getProductSearchData() {
        return new Object[][]{
                {"Sleeveless"}
        };
    }

    /**
     * Data provider for payment details
     * @return test data array
     */
    @DataProvider(name = "paymentData")
    public Object[][] getPaymentData() {
        return new Object[][]{
                {"Tester", "4111111111111111", "123", "12", "2026"}
        };
    }

    /**
     * Main test for complete product order flow
     * Uses both hard and soft assertions as per requirements
     */
    @Test(priority = 1, description = "Complete Product Order Flow - Search, Add to Cart, Checkout, Payment, Invoice")
    public void testProductOrderCompleteFlow() {
        
        // Initialize soft assert for non-critical validations
        SoftAssert softAssert = new SoftAssert();
        
        System.out.println("\n========== STARTING PRODUCT ORDER TEST ==========");
        
        // Step 1 & 2: Navigate to homepage and verify
        System.out.println("\n--- Step 1-2: Verify Homepage ---");
        HomePage homePage = new HomePage(driver);
        
        // HARD ASSERTION - Homepage must be visible to proceed
        Assert.assertTrue(homePage.isHomePageVisible(), 
            "FAILED: Home page is not visible");
        System.out.println("✓ Homepage is visible");
        
        // Step 3 & 4: Navigate to Products page and verify
        System.out.println("\n--- Step 3-4: Navigate to Products Page ---");
        ProductsPage productsPage = homePage.clickProducts();
        
        // SOFT ASSERTION - Products page should be visible
        softAssert.assertTrue(productsPage.isAllProductsVisible(), 
            "FAILED: ALL PRODUCTS page is not visible");
        System.out.println("✓ ALL PRODUCTS page is visible");
        
        // Step 5: Search for product
        System.out.println("\n--- Step 5: Search for 'Sleeveless' Product ---");
        String searchTerm = "Sleeveless";
        productsPage.searchProduct(searchTerm);
        System.out.println("✓ Searched for product: " + searchTerm);
        
        // Step 6: Verify searched products section
        System.out.println("\n--- Step 6: Verify Search Results ---");
        
        // SOFT ASSERTION - Searched products section should be visible
        softAssert.assertTrue(productsPage.isSearchedProductsVisible(), 
            "FAILED: SEARCHED PRODUCTS section is not visible");
        System.out.println("✓ SEARCHED PRODUCTS section is visible");
        
        // SOFT ASSERTION - Verify product contains search term
        softAssert.assertTrue(productsPage.isSleevelessProductVisible(), 
            "FAILED: Product containing 'Sleeveless' is not visible in search results");
        System.out.println("✓ 'Sleeveless' product is visible in search results");
        
        // Step 7: Add two products to cart
        System.out.println("\n--- Step 7: Add Two Products to Cart ---");
        productsPage.addFirstProductToCart();
        System.out.println("✓ Added first product (ID: 3) to cart");
        
        productsPage.clickContinueShopping();
        System.out.println("✓ Clicked Continue Shopping");
        
        productsPage.addSecondProductToCart();
        System.out.println("✓ Added second product (ID: 19) to cart");
        
        productsPage.clickContinueShopping();
        System.out.println("✓ Clicked Continue Shopping");
        
        // Step 8: Navigate to Cart
        System.out.println("\n--- Step 8: Navigate to Cart ---");
        CartPage cartPage = productsPage.goToCart();
        
        // SOFT ASSERTION - Verify products are in cart
        softAssert.assertTrue(cartPage.areProductsInCart(), 
            "FAILED: No products found in cart");
        System.out.println("✓ Products are present in cart");
        
        // Step 9-10: Proceed to checkout and login
        System.out.println("\n--- Step 9-10: Proceed to Checkout and Login ---");
        LoginPage loginPage = cartPage.goToLoginForCheckout();
        
        String email = config.getProperty("email");
        String password = config.getProperty("password");
        
        loginPage.login(email, password);
        System.out.println("✓ Logged in with email: " + email);
        
        // HARD ASSERTION - User must be logged in to proceed
        Assert.assertTrue(loginPage.isUserLoggedIn(), 
            "FAILED: User login failed - cannot proceed with checkout");
        System.out.println("✓ User is successfully logged in");
        
        // Navigate back to cart after login
        cartPage = loginPage.goToCart();
        
        // Step 11: Proceed to checkout (after login)
        System.out.println("\n--- Step 11: Proceed to Checkout ---");
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        
        // SOFT ASSERTION - Verify checkout page elements
        softAssert.assertTrue(checkoutPage.isDeliveryAddressVisible(), 
            "FAILED: Delivery address is not visible on checkout page");
        System.out.println("✓ Delivery address is visible");
        
        // Step 12: Place Order
        System.out.println("\n--- Step 12: Place Order ---");
        PaymentPage paymentPage = checkoutPage.placeOrder();
        System.out.println("✓ Navigated to payment page");
        
        // Step 13: Enter payment details and confirm
        System.out.println("\n--- Step 13: Enter Payment Details ---");
        String cardName = "Tester";
        String cardNumber = "4111111111111111";
        String cvc = "123";
        String expiryMonth = "12";
        String expiryYear = "2026";
        
        paymentPage.pay(cardName, cardNumber, cvc, expiryMonth, expiryYear);
        System.out.println("✓ Payment details entered and order confirmed");
        
        // Step 14: Verify order success
        System.out.println("\n--- Step 14: Verify Order Confirmation ---");
        
        // HARD ASSERTION - Order must be successful
        Assert.assertTrue(paymentPage.isOrderSuccessMessageDisplayed(), 
            "FAILED: Order success message is not displayed - Order may have failed");
        System.out.println("✓ Order success message is displayed: 'Congratulations! Your order has been confirmed!'");
        
        // SOFT ASSERTION - Download invoice button should be visible
        softAssert.assertTrue(paymentPage.isDownloadInvoiceVisible(), 
            "FAILED: Download Invoice button is not visible");
        System.out.println("✓ Download Invoice button is visible");
        
        // Step 15: Download invoice
        System.out.println("\n--- Step 15: Download Invoice ---");
        InvoicePage invoicePage = new InvoicePage(driver);
        
        // SOFT ASSERTION - Invoice download should be available
        softAssert.assertTrue(invoicePage.isInvoiceDownloadAvailable(), 
            "FAILED: Invoice download is not available");
        
        invoicePage.downloadInvoice();
        System.out.println("✓ Invoice download initiated");
        
        // Wait for download to complete
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Step 16: Verify invoice file and content
        System.out.println("\n--- Step 16: Verify Invoice Content ---");
        
        // SOFT ASSERTION - Invoice file should be downloaded
        softAssert.assertTrue(invoicePage.isInvoiceFileDownloaded(), 
            "FAILED: Invoice file was not downloaded");
        System.out.println("✓ Invoice file has been downloaded");
        
        // SOFT ASSERTION - Verify invoice content
        softAssert.assertTrue(invoicePage.verifyInvoiceDetails("Tester"), 
            "FAILED: Invoice content verification failed - Expected 'Hi Tester' and 'Your total purchase amount'");
        System.out.println("✓ Invoice content verified successfully");
        
        System.out.println("\n========== PRODUCT ORDER TEST COMPLETED SUCCESSFULLY ==========\n");
        
        // Assert all soft assertions at the end
        softAssert.assertAll();
    }

    /**
     * Separate test with data provider for product search
     */
    @Test(priority = 2, dataProvider = "productSearchData", 
          description = "Test Product Search Functionality", enabled = false)
    public void testProductSearch(String productName) {
        
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = homePage.clickProducts();
        
        Assert.assertTrue(productsPage.isAllProductsVisible(), 
            "All Products page not visible");
        
        productsPage.searchProduct(productName);
        
        Assert.assertTrue(productsPage.isSearchedProductsVisible(), 
            "Searched Products section not visible");
        Assert.assertTrue(productsPage.isSleevelessProductVisible(), 
            "Product '" + productName + "' not found in search results");
        
        System.out.println("Product search test passed for: " + productName);
    }

    /**
     * Separate test for payment flow with data provider
     */
    @Test(priority = 3, dataProvider = "paymentData", 
          description = "Test Payment Details Entry", enabled = false)
    public void testPaymentDetailsEntry(String name, String cardNum, String cvv, String month, String year) {
        
        // This test would require setup of cart and login first
        // Keeping it disabled as it's covered in main flow
        // Included to demonstrate @DataProvider usage
        
        System.out.println("Payment test with data: " + name + ", Card: " + cardNum);
    }
}
