package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;
import java.util.Objects;

public class SessionHelper extends HelperBase{

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void uiLogin() {
        wd.get(app.getProperty("web.mantis.besUrl") + "login_page.php");
        type(By.xpath("//input[@id='username']"), app.getProperty("web.mantis.adminLogin"));
        type(By.xpath("//input[@type='password']"), app.getProperty("web.mantis.adminPassword"));
        wd.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void openManagePage() {
        wd.findElement(By.cssSelector("a.manage-menu-link")).click();
    }

    public void openManageUserPage() {
        openManagePage();
        wd.findElement(By.xpath("//a[@href='/mantisbt-1.3.2/manage_user_page.php']")).click();
    }

    public void selectUser(UserData user) {
        wd.findElements(By.xpath("//div[@class='form-container']//tbody//tr//td[1]//a"))
                .stream().filter(webElement -> Objects.equals(webElement.getText(), user.getUsername())).findFirst().get().click();

    }
}
