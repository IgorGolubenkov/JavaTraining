package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        if (Objects.equals(app.getProperty("ftp.filezilla"), "true")) {
            app.ftp().upload(new File("src/test/resources/config_inc.php"), "config/config_inc.php", "config/config_inc.php.backup");
        }
    }
    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        if (Objects.equals(app.getProperty("ftp.filezilla"), "true")) {
            app.ftp().restore("config_inc.php.back", "config_inc.php");
        }
        app.stop();
    }
}
