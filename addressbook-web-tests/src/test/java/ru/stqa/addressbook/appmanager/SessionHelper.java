package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SessionHelper extends HelperBase{

    public SessionHelper(ChromeDriver wd) {
        super(wd);
    }
    public void login(String username, String password) {
        wd.get("http://localhost/addressbook/");
        type(By.name("user"), username);
        type(By.name("pass"), password);
        clickSearch(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}

