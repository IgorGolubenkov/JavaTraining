package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Navigationhelper extends HelperBase{

    public Navigationhelper(ChromeDriver wd) {
        super(wd);
    }
    public void goToGroupPage() {
        clickSearch(By.linkText("groups"));
    }
    public void goToAddandEditContactPage() {
        clickSearch(By.xpath("//a[contains(.,'add new')]"));
    }
}
