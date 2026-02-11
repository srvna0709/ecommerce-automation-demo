# Quick Start Guide

## Prerequisites
- Java 17 or higher installed
- Maven 3.6 or higher installed
- Chrome browser installed

## Run Tests in 3 Steps

### Step 1: Navigate Project Path
```bash
cd root
```

### Step 2: Run Tests
```bash
mvn clean test
```

### Step 3: View Results
- Console output shows test execution steps
- HTML Report: `target/surefire-reports/index.html`

## What the Tests Do

### Scenario 1: Product Order Flow
✅ Navigates to automationexercise.com
✅ Searches for "Sleeveless" products
✅ Adds 2 products to cart
✅ Proceeds through checkout
✅ Logs in with test account
✅ Completes payment
✅ Downloads and verifies invoice

### Scenario 2: Add Product Review
✅ Views product details
✅ Submits a product review
✅ Verifies success message

### Scenario 3: View Brand Products
✅ Navigates to brand pages (Polo, H&M)
✅ Verifies brand products
✅ Checks brand names on products

### Scenario 4: Scroll Functionality
✅ Scrolls to page bottom
✅ Uses scroll-up arrow button
✅ Verifies page scrolled to top

### Scenario 5: Test Cases Extraction
✅ Extracts all test cases from site
✅ Writes test cases to file
✅ Stores file in project folder
✅ Logs out and verifies

## Test Credentials
- Email: 
- Password: 

## Customization
Edit `src/test/resources/config.properties` to change:
- Browser (chrome/firefox/edge)
- Base URL
- Login credentials

## Need Help?
See detailed README.md for:
- Complete documentation
- Troubleshooting guide
- Project structure explanation
- OOP concepts used
- TestNG features demonstrated

---
