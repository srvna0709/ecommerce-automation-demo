package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrandPage extends BasePage {

    // Fixed locators for specific brands
    private final By poloBrandTitle = By.xpath("//h2[@class='title text-center' and text()='Brand - Polo Products']");
    private final By hmBrandTitle = By.xpath("//h2[@class='title text-center' and text()='Brand - H&M Products']");
    private final By productImage = By.xpath("//img[contains(@src,'/get_product_picture/')]");
    private final By viewProductButton = By.xpath("(//a[contains(text(),'View Product')])[1]");

    public BrandPage(WebDriver driver) {
        super(driver);
    }

    // Verify Polo brand page
    public boolean isPoloBrandPageDisplayed() {
        return isDisplayed(poloBrandTitle);
    }

    // Verify H&M brand page
    public boolean isHMBrandPageDisplayed() {
        return isDisplayed(hmBrandTitle);
    }

    // Verify brand products are displayed
    public boolean areBrandProductsDisplayed() {
        return isDisplayed(productImage);
    }

    // Click View Product
    public ProductDetailPage clickViewProduct() {
        scrollToElement(viewProductButton);
        click(viewProductButton);
        return new ProductDetailPage(driver);
    }
}
