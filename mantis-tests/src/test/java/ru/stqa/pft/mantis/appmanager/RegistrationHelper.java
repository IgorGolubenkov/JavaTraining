package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String usename, String email) {
        wd.get(app.getProperty("web.mantis.besUrl") + "signup_page.php");
        type(By.cssSelector("input[name='username']"), usename);
        type(By.cssSelector("input[name='email']"), email);
        clickSearch(By.xpath("//input[@type='submit']"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        clickSearch(By.cssSelector("//input[@type='submit']"));
    }
}
