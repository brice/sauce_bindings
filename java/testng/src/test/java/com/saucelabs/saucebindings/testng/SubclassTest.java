package com.saucelabs.saucebindings.testng;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertEquals;

public class SubclassTest extends SauceBaseTest {
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @Override
    public RemoteWebDriver getDriver() {
        if (getSession() == null) {
            return driver.get();
        } else {
            return super.getDriver();
        }
    }

    @BeforeSuite
    public void demoPurposes() {
        System.setProperty("SELENIUM_TARGET", "SAUCE_LABS");
    }

    @BeforeMethod
    public void setup(Method method) {
        if (System.getProperty("SELENIUM_TARGET").equals("SAUCE_LABS")) {
            super.setup(method);
        } else {
            driver.set(new ChromeDriver());
        }
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (getSession() == null) {
            getDriver().quit();
        } else {
            super.teardown(result);
        }
    }

    @Test
    public void subclassedExample() {
        getDriver().get("https://www.saucedemo.com");
        assertEquals(getDriver().getTitle(), "Swag Labs");
    }
}
