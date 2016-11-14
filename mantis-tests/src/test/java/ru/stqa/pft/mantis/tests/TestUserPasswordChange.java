package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        app.session().uiLogin();
        app.session().openManageUserPage();
        app.session().selectUser(user)
        System.out.println();
    }
}
