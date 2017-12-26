package com.epam.framework.core.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class ChromeDriverCreator extends WebDriverCreator{

    private static final String PATH_TO_DRIVER = "src/main/resources/binaries/chromedriver.exe";

    @Override
    public WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", PATH_TO_DRIVER);
        LoggingPreferences logs = new LoggingPreferences();
        logs.enable(LogType.DRIVER, Level.INFO);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
        WebDriver driver = new ChromeDriver( desiredCapabilities);
        return driver;
    }
}
