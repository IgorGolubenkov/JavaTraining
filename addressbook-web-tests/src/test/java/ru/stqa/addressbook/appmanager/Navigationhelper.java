package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Navigationhelper {
    /**FirefoxDriver wd;*/
    private ChromeDriver wd;

    public Navigationhelper(ChromeDriver wd) {
        this.wd = wd;
    }
    public void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }
    public void goToAddandEditContactPage() {
        wd.findElement(By.xpath("//a[contains(.,'add new')]")).click();
    }
}
