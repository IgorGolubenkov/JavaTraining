package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void clickSearch(By locator) {
        wd.findElement(locator).click();
    }
    protected void clickLocator(WebElement locator) {
        locator.click();
    }
    protected void type(By locator, String text) {
        clickSearch(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }
    protected Alert alert() {
        Alert alert = wd.switchTo().alert();
        return alert;
    }
    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
