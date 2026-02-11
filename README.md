# E-Commerce Automation Testing Framework

## Overview
This is a comprehensive automation testing framework built using:
- **Selenium WebDriver** - Browser automation
- **TestNG** - Test framework
- **Java** - Programming language
- **Page Object Model (POM)** - Design pattern
- **Maven** - Build and dependency management

## Project Structure

```
ecommerce-automation/
│
├── src/
│   └── test/
│       ├── java/
│       │   └── com.automation/
│       │       ├── base/
│       │       │   ├── BasePage.java          # Base page with common methods
│       │       │   └── BaseTest.java          # Base test with setup/teardown
│       │       ├── pages/
│       │       │   ├── HomePage.java          # Home page objects
│       │       │   ├── ProductsPage.java      # Products page objects
│       │       │   ├── ProductDetailPage.java # Product detail & review
│       │       │   ├── BrandPage.java         # Brand products page
│       │       │   ├── CartPage.java          # Cart page objects
│       │       │   ├── LoginPage.java         # Login page objects
│       │       │   ├── CheckoutPage.java      # Checkout page objects
│       │       │   ├── PaymentPage.java       # Payment page objects
│       │       │   ├── InvoicePage.java       # Invoice download/verify
│       │       │   └── TestCasesPage.java     # Test cases extraction
│       │       ├── tests/
│       │       │   ├── ProductOrderTest.java        # Scenario 1
│       │       │   ├── AddProductReviewTest.java    # Scenario 2
│       │       │   ├── ViewCartBrandProductsTest.java # Scenario 3
│       │       │   ├── ScrollUpDownTest.java        # Scenario 4
│       │       │   └── FileWriteTestCasesTest.java  # Scenario 5
│       │       └── utils/
│       │           ├── DriverManager.java     # WebDriver management
│       │           └── ConfigReader.java      # Configuration reader
│       └── resources/
│           └── config.properties              # Test configuration
├── test-data/                                  # Generated test data files
├── pom.xml                                     # Maven dependencies
├── testng.xml                                  # TestNG configuration
└── README.md                                   # This file
```

## Test Scenarios

This framework includes 5 comprehensive test scenarios:

### 1. **Product Order Flow** (ProductOrderTest)
Complete e-commerce product ordering process:
- Navigate to homepage and verify
- Search for 'Sleeveless' product
- Add two products to cart (Product ID: 3 and 19)
- Proceed to checkout
- Login with credentials
- Enter payment details
- Verify order confirmation
- Download and verify invoice

### 2. **Add Product Review** (AddProductReviewTest)
Test product review functionality:
- Navigate to Products page
- Click on View Product
- Verify Write Your Review section
- Submit product review
- Verify success message

### 3. **View & Cart Brand Products** (ViewCartBrandProductsTest)
Test brand-based product navigation:
- Verify Brands section on sidebar
- Navigate to Polo brand products
- Verify Polo brand page and products
- View product and verify brand name
- Navigate to H&M brand products
- Verify H&M brand page and products

### 4. **Scroll Up/Down Functionality** (ScrollUpDownTest)
Test page scroll with arrow button:
- Scroll down to page bottom
- Verify SUBSCRIPTION section
- Click scroll up arrow button
- Verify page scrolled to top
- Verify Full-Fledged text is visible

### 5. **File Write Test Cases** (FileWriteTestCasesTest)
Extract and store test cases:
- Navigate to Test Cases page
- Extract all test case titles
- Write test cases to file in project folder
- Verify file creation
- Logout and verify

## Performance Optimizations

The framework has been optimized for faster execution:

✅ **Reduced Wait Times**
- Implicit wait: 10s → 3s
- Page load timeout: 30s → 15s
- Explicit waits: 10s → 5s
- Short waits: 3s → 2s

✅ **Page Load Strategy**
- Using NORMAL page load strategy
- Elements interacted with as soon as they're available
- No waiting for complete page load

✅ **Optimized Timeouts**
- Thread.sleep reduced across all pages
- Login wait: 1000ms → 500ms
- Modal close: 500ms → 300ms
- Ad close: 500ms → 300ms
- Download wait: 3000ms → 2000ms

✅ **Browser Performance**
- Added `--disable-dev-shm-usage`
- Added `--no-sandbox`
- Disabled unnecessary features

## OOP Principles Demonstrated

### 1. **Encapsulation**
- All page locators are private
- Public methods expose only necessary functionality
- Internal implementation is hidden

### 2. **Inheritance**
- `BasePage` - Common page actions
- `BaseTest` - Common test setup/teardown
- All page classes extend `BasePage`
- All test classes extend `BaseTest`

### 3. **Abstraction**
- Abstract common operations in base classes
- Hide complex Selenium operations behind simple methods

### 4. **Polymorphism**
- Method overloading in utility classes
- Different implementations of common operations

## TestNG Features Used

### Annotations
- `@BeforeClass` - Suite-level setup
- `@BeforeMethod` - Test-level setup (runs before each test)
- `@AfterMethod` - Test-level teardown (runs after each test)
- `@AfterClass` - Suite-level teardown
- `@Test` - Marks test methods with priority and description
- `@DataProvider` - Provides test data

### Assertions
- **Hard Assertions** (`Assert.assertTrue`)
  - Used for critical validations
  - Test stops immediately if assertion fails
  - Examples: Homepage visible, User logged in, Order success
  
- **Soft Assertions** (`SoftAssert`)
  - Used for non-critical validations
  - Test continues even if assertion fails
  - All failures reported at end via `assertAll()`
  - Examples: Products page visible, Search results, Invoice details

## Prerequisites

- Java JDK 17 or higher
- Maven 3.6 or higher
- Chrome browser installed
- Internet connection

## Setup Instructions

### 1. Install Java
```bash
java -version  # Should show Java 17 or higher
```

### 2. Install Maven
```bash
mvn -version  # Should show Maven 3.6 or higher
```

### 3. Clone/Extract Project
```bash
# Extract the zip file or clone repository
cd ecommerce-automation
```

### 4. Update Configuration (if needed)
Edit `src/test/resources/config.properties`:
```properties
baseUrl=https://automationexercise.com
browser=chrome
email=testdart117@mailinator.com
password=Human
```

## Running Tests

### Option 1: Using Maven Command Line
```bash
# Run all tests
mvn clean test

# Run specific test
mvn clean test -Dtest=ProductOrderTest#testProductOrderCompleteFlow

# Run with specific browser
mvn clean test -Dbrowser=chrome
```

### Option 2: Using TestNG XML
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### Option 3: From IDE (IntelliJ IDEA / Eclipse)
1. Right-click on `testng.xml`
2. Select "Run" or "Run as TestNG Suite"

OR

1. Right-click on `ProductOrderTest.java`
2. Select "Run 'ProductOrderTest'"

## Test Execution Flow

```
1. setupClass()
   ↓
2. setupMethod()
   ↓
3. testProductOrderCompleteFlow()
   ├── Hard Assert: Homepage visible
   ├── Soft Assert: Products page visible
   ├── Soft Assert: Search results visible
   ├── Soft Assert: Product in results
   ├── Soft Assert: Products in cart
   ├── Hard Assert: User logged in
   ├── Soft Assert: Delivery address visible
   ├── Hard Assert: Order success
   ├── Soft Assert: Invoice button visible
   ├── Soft Assert: Invoice downloaded
   └── Soft Assert: Invoice content verified
   ↓
4. teardownMethod()
   ↓
5. teardownClass()
```

## Expected Output

```
========== STARTING PRODUCT ORDER TEST ==========

--- Step 1-2: Verify Homepage ---
✓ Homepage is visible

--- Step 3-4: Navigate to Products Page ---
✓ ALL PRODUCTS page is visible

--- Step 5: Search for 'Sleeveless' Product ---
✓ Searched for product: Sleeveless

--- Step 6: Verify Search Results ---
✓ SEARCHED PRODUCTS section is visible
✓ 'Sleeveless' product is visible in search results

--- Step 7: Add Two Products to Cart ---
✓ Added first product (ID: 3) to cart
✓ Clicked Continue Shopping
✓ Added second product (ID: 19) to cart
✓ Clicked Continue Shopping

--- Step 8: Navigate to Cart ---
✓ Products are present in cart

--- Step 9-10: Proceed to Checkout and Login ---
✓ Logged in with email: testdart117@mailinator.com
✓ User is successfully logged in

--- Step 11: Proceed to Checkout ---
✓ Delivery address is visible

--- Step 12: Place Order ---
✓ Navigated to payment page

--- Step 13: Enter Payment Details ---
✓ Payment details entered and order confirmed

--- Step 14: Verify Order Confirmation ---
✓ Order success message is displayed
✓ Download Invoice button is visible

--- Step 15: Download Invoice ---
✓ Invoice download initiated

--- Step 16: Verify Invoice Content ---
✓ Invoice file has been downloaded
✓ Invoice content verified successfully

========== PRODUCT ORDER TEST COMPLETED SUCCESSFULLY ==========

PASSED: testProductOrderCompleteFlow
```

## Handling Common Issues

### 1. Ad Popup Blocking Test
- The framework includes `closeAdIfPresent()` method
- Automatically handles ad dismissal when detected
- No manual intervention required

### 2. Element Click Intercepted
- Uses explicit waits for clickable elements
- Scrolls to elements before clicking
- Handles modal overlays automatically

### 3. Download Verification
- Downloads go to user's Downloads folder
- Invoice verification reads actual file content
- Verifies expected text: "Hi Tester" and "Your total purchase amount"

### 4. Login Credentials
- Default: testdart117@mailinator.com / Human
- Can be changed in config.properties
- Ensure account exists before running tests

## Page Object Model Benefits

1. **Maintainability**: Changes to UI only require updates in page classes
2. **Reusability**: Page methods can be reused across multiple tests
3. **Readability**: Tests read like business scenarios
4. **Separation**: Test logic separated from page interactions

## Customization

### Adding New Tests
1. Create test method in `ProductOrderTest.java`
2. Use `@Test` annotation
3. Follow AAA pattern (Arrange-Act-Assert)

### Adding New Pages
1. Create new class in `pages` package
2. Extend `BasePage`
3. Define private locators
4. Create public action methods

### Changing Browser
Edit `config.properties`:
```properties
browser=chrome  # or firefox, edge
```

## Reports

TestNG generates HTML reports:
- Location: `target/surefire-reports/index.html`
- Open in browser to view detailed results
- Contains pass/fail status, execution time, stack traces

## Troubleshooting

### Test Fails at Login
- Verify email/password in config.properties
- Ensure account exists on automationexercise.com
- Check internet connection

### Driver Issues
- WebDriverManager auto-downloads drivers
- Ensure Chrome/Firefox is installed
- Check firewall/proxy settings

### Element Not Found
- Website structure may have changed
- Update locators in respective page class
- Check if ad popup is blocking elements

## Contact & Support

For issues or questions:
- Review console output for detailed error messages
- Check TestNG reports for stack traces
- Verify all prerequisites are met

---

**Built with ❤️ using Selenium, TestNG, and Page Object Model**
