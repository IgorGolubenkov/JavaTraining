package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Navigationhelper extends HelperBase{

    public Navigationhelper(WebDriver wd) {
        super(wd);
    }
    public void goToGroupPage() {
        clickSearch(By.linkText("groups"));
    }
    public void goToAddandEditContactPage() {
        clickSearch(By.xpath("//a[contains(.,'add new')]"));
    }
    public void goToHomePage() {
        clickSearch(By.xpath("//a[contains(.,'home')]"));
    }
}
