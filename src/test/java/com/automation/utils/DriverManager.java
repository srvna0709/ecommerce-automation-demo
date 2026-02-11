package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Driver Manager - Manages WebDriver instances
 * Demonstrates Singleton pattern and Encapsulation
 */
public class DriverManager {

    private static WebDriver driver;

    /**
     * Get WebDriver instance (creates if not exists)
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = ConfigReader.getInstance().getProperty("browser").toLowerCase();
            driver = createDriver(browser);
            configureDriver();

            // Apply ad-blocking for Chrome via DevTools Protocol
            if (browser.equals("chrome")) {
                blockAdsCDP(driver);
            }
        }
        return driver;
    }

    /**
     * Create driver based on browser type
     * @param browser browser name
     * @return WebDriver instance
     */
    private static WebDriver createDriver(String browser) {
        WebDriver webDriver;

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = getChromeOptions();
                webDriver = new ChromeDriver(options);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        return webDriver;
    }

    /**
     * Get Chrome options with custom settings
     * @return ChromeOptions
     */
    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Page load strategy NORMAL
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        // Download directory
        Map<String, Object> prefs = new HashMap<>();
        String downloadPath = System.getProperty("user.home") + "/Downloads";
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", prefs);

        // Performance / stability options
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // Uncomment for headless mode
        // options.addArguments("--headless=new");

        return options;
    }

    /**
     * Configure driver with common settings
     */
    private static void configureDriver() {
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    /**
     * Quit driver and clean up
     */
    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println("Error quitting driver: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }

    /**
     * Block common ad URLs using Chrome DevTools Protocol
     */
    private static void blockAdsCDP(WebDriver driver) {
        try {
            Map<String, Object> blockMap = new HashMap<>();
            blockMap.put("urls", Arrays.asList(
                    "*://*.doubleclick.net/*",
                    "*://*.adservice.google.com/*",
                    "*://*.googlesyndication.com/*",
                    "*://*.googleadservices.com/*",
                    "*://*.adroll.com/*",
                    "*://*.taboola.com/*"
            ));
            ((ChromeDriver) driver).executeCdpCommand("Network.setBlockedURLs", blockMap);
            ((ChromeDriver) driver).executeCdpCommand("Network.enable", new HashMap<>());
        } catch (Exception e) {
            System.out.println("Error enabling ad-blocking via CDP: " + e.getMessage());
        }
    }
}
