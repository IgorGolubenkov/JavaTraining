package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HTTPSession;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HTTPSession session = app.httpSession();
        assertTrue(session.login(new UserData().withUsername("administrator").withPassword("root")));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
