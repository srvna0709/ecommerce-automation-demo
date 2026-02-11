package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Cases Page - Handles test cases listing and extraction
 */
public class TestCasesPage extends BasePage {

    // Encapsulated locators
    private final By testCasesTitle = By.xpath("//b[contains(text(),'Test Cases')]");
    private final By testCasePanels = By.xpath("//div[@class='panel-heading']//a");
    private final By testCaseSteps = By.xpath("//li[@class='list-group-item']");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify test cases page is displayed
     */
    public boolean isTestCasesPageDisplayed() {
        return isDisplayed(testCasesTitle);
    }

    /**
     * Get all test case titles
     */
    public List<String> getAllTestCaseTitles() {
        List<String> testCases = new ArrayList<>();

        List<WebElement> testCaseElements = driver.findElements(testCasePanels);
        for (WebElement element : testCaseElements) {
            String text = element.getText().trim();
            if (!text.isEmpty()) {
                testCases.add(text);
            }
        }
        return testCases;
    }

    /**
     * Write test cases WITH steps to a file
     */
    public boolean writeTestCasesToFile(String filename) {

        String projectPath = System.getProperty("user.dir");
        File testDataDir = new File(projectPath + "/test-data");

        if (!testDataDir.exists()) {
            testDataDir.mkdirs();
        }

        File outputFile = new File(testDataDir, filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            writer.write("=== AUTOMATION EXERCISE TEST CASES ===\n");
            writer.write("Extracted on: " + LocalDateTime.now() + "\n");
            writer.write("===================================================\n\n");

            List<WebElement> testCaseElements = driver.findElements(testCasePanels);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            int count = 1;

            for (WebElement testCase : testCaseElements) {

                String title = testCase.getText().trim();
                if (title.isEmpty()) {
                    continue;
                }

                // Write test case title
                writer.write(count + ". " + title + "\n");

                // Expand test case
                testCase.click();

                // Wait until steps are visible
                wait.until(ExpectedConditions.visibilityOfElementLocated(testCaseSteps));

                List<WebElement> steps = driver.findElements(testCaseSteps);

                for (WebElement step : steps) {
                    writer.write("   - " + step.getText().trim() + "\n");
                }

                writer.write("\n");
                count++;
            }

            writer.write("===================================================\n");
            writer.write("End of Test Cases\n");

            System.out.println("✓ Test cases with steps written to: " + outputFile.getAbsolutePath());
            return true;

        } catch (Exception e) {
            System.err.println("Error writing test cases to file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get the file path where test cases are stored
     */
    public String getTestCasesFilePath(String filename) {
        String projectPath = System.getProperty("user.dir");
        return projectPath + "/test-data/" + filename;
    }
}
