package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Invoice Page - Handles invoice download and verification
 */
public class InvoicePage extends BasePage {

    // Encapsulated locators
    private final By downloadInvoiceButton = By.xpath("//a[contains(@href,'/download_invoice') and contains(text(),'Download Invoice')]");
    private final By orderSuccessMessage = By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]");

    public InvoicePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if invoice download button is available
     * @return true if download button is visible
     */
    public boolean isInvoiceDownloadAvailable() {
        return isDisplayed(downloadInvoiceButton);
    }

    /**
     * Download invoice file
     */
    public void downloadInvoice() {
        click(downloadInvoiceButton);

    }

    /**
     * Verify invoice file exists in downloads folder
     * @return true if invoice file is found
     */
    public boolean isInvoiceFileDownloaded() {
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        File downloadDir = new File(downloadPath);
        
        if (!downloadDir.exists()) {
            return false;
        }
        
        File[] files = downloadDir.listFiles((dir, name) -> name.toLowerCase().contains("invoice") && name.endsWith(".txt"));
        return files != null && files.length > 0;
    }

    /**
     * Get the latest invoice file from downloads
     * @return File object or null if not found
     */
    public File getLatestInvoiceFile() {
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        File downloadDir = new File(downloadPath);
        
        if (!downloadDir.exists()) {
            return null;
        }
        
        File[] files = downloadDir.listFiles((dir, name) -> name.toLowerCase().contains("invoice") && name.endsWith(".txt"));
        
        if (files == null || files.length == 0) {
            return null;
        }
        
        // Return the most recently modified file
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }
        
        return latestFile;
    }

    /**
     * Read and verify invoice content
     * @param expectedName expected name in invoice
     * @param expectedText expected text in invoice
     * @return true if invoice contains expected content
     */
    public boolean verifyInvoiceContent(String expectedName, String expectedText) {
        File invoiceFile = getLatestInvoiceFile();
        
        if (invoiceFile == null) {
            System.out.println("Invoice file not found!");
            return false;
        }
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(invoiceFile.getAbsolutePath()));
            String content = String.join(" ", lines);
            
            System.out.println("Invoice Content: " + content);
            
            boolean hasName = content.contains(expectedName);
            boolean hasExpectedText = content.toLowerCase().contains(expectedText.toLowerCase());
            
            return hasName && hasExpectedText;
            
        } catch (IOException e) {
            System.out.println("Error reading invoice file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verify specific invoice details
     * @param expectedName expected customer name
     * @return true if invoice is verified
     */
    public boolean verifyInvoiceDetails(String expectedName) {
        return verifyInvoiceContent(expectedName, "Your total purchase amount");
    }
}
