package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

public class TestUserPasswordChange extends TestBase{


    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }


    @Test
    public void testUserPasswordChange() {
        UserData user = new UserData().withUsername("Java Training").withEmail("javatraining2@localhost");
        app.session().uiLogin();
        app.session().openManageUserPage();
        app.session().selectUser(user);
        app.session().resetPassowrd();
        System.out.println();
    }
}
