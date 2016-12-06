package ru.stqa.pft.sandbox;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestTest {

    private final Properties properties;

    public TestTest() {

        properties = new Properties();
    }

    @BeforeTest
    public void loadProperties() throws IOException {
        String target = System.getProperty("target", "test");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    @Test
    public void testProperties() {
        String envr = properties.getProperty("baseUrl");
        System.out.println(envr);
    }

}
