package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.model.UserData;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(UserData user) {
        wd.get(app.getProperty("web.mantis.besUrl") + "signup_page.php");
        type(By.cssSelector("input[name='username']"), user.getUsername());
        type(By.cssSelector("input[name='email']"), user.getEmail());
        clickSearch(By.xpath("//input[@type='submit']"));
    }

    public void finish(String confirmationLink, UserData user) {
        wd.get(confirmationLink);
        type(By.name("password"), user.getPassword());
        type(By.name("password_confirm"), user.getPassword());
        clickSearch(By.cssSelector("//input[@type='submit']"));
    }
}
