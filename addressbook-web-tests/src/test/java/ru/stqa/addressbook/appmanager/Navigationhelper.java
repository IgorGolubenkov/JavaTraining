package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Navigationhelper extends HelperBase{
    private final ApplicationManager app;

    //public Navigationhelper(WebDriver wd) {
    //    super(wd);
    //}

    public Navigationhelper(ApplicationManager app) {
        super(app.wd);
        this.app = app;
    }

    public void goToGroupPage() {
        if (isElementPresent(By.xpath("//h1[contains(.,'Groups')]")) &&
                wd.findElement(By.xpath("//h1[contains(.,'Groups')]")).getText().equals("Group") &&
                isElementPresent(By.xpath(".//*[@id='content']/form/input[1]"))) {
            return;
        }
        clickSearch(By.linkText("groups"));
    }

    public void goToAddandEditContactPage() {
        clickSearch(By.xpath("//a[contains(.,'add new')]"));
    }

    public void goToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        clickSearch(By.xpath("//a[contains(.,'home')]"));
    }
}
