package com.automation.base;

import com.automation.utils.ConfigReader;
import com.automation.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;

/**
 * Base Test class with common setup and teardown
 * Demonstrates OOP principles: Inheritance
 * All test classes inherit from this base
 */
public class BaseTest {

    protected WebDriver driver;
    protected ConfigReader config;

    @BeforeClass(alwaysRun = true)
    public void setupClass() {
        config = ConfigReader.getInstance();
        System.out.println("=== Starting Test Suite ===");
    }

    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        driver = DriverManager.getDriver();
        driver.get(config.getProperty("baseUrl"));
        System.out.println("Navigating to: " + config.getProperty("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void teardownMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Test FAILED: " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test PASSED: " + result.getName());
        }
        
        // Clean up downloaded files (optional)
        cleanupDownloads();
        
        DriverManager.quitDriver();
    }

    @AfterClass(alwaysRun = true)
    public void teardownClass() {
        System.out.println("=== Test Suite Completed ===");
    }

    /**
     * Clean up downloaded files from previous test runs
     */
    private void cleanupDownloads() {
        try {
            String downloadPath = System.getProperty("user.home") + "/Downloads";
            File downloadDir = new File(downloadPath);
            if (downloadDir.exists()) {
                File[] files = downloadDir.listFiles((dir, name) -> name.startsWith("invoice") && name.endsWith(".txt"));
                if (files != null) {
                    for (File file : files) {
                        // Optional: delete old invoice files
                        // file.delete();
                    }
                }
            }
        } catch (Exception e) {
            // Ignore cleanup errors
        }
    }
}
