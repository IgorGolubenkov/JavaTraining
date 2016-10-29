package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {

    private final WebDriver wd;
    private final ApplicationManager app;

    public RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String usename, String email) {
        wd.get(app.getProperty("web.mantis.besUrl") + "login_page.php");
    }
}
