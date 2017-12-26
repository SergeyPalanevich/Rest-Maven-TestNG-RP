package com.epam.framework.core.drivers;

import com.epam.framework.core.exeptions.NoSuchWebDriverExeption;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.epam.framework.core.utils.LoggerUtils.info;

public class DriverManager {

    public static final long MANAGE_TIMEOUT = 45;
    private static final String PATH_TO_COOKIES = "src/main/resources/cookies.out";
    private static final String ERROR_MSG = "Invalid name for WebDriver...";
    private static final String INFO_MSG = "Driver type is ";
    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getDriver(DriverTypes driverName) throws NoSuchWebDriverExeption {
        if(driver == null) {
            switch (driverName) {
                case CHROME:
                    info(INFO_MSG + "CHROME");
                    driver = new ChromeDriverCreator().createDriver();
                    setDriverManage();
                    return driver;
                case FIREFOX:
                    driver = new FireFoxDriverCreator().createDriver();
                    setDriverManage();
                    info(INFO_MSG + "FIREFOX");
                    return driver;
                default:
                    throw new NoSuchWebDriverExeption(ERROR_MSG);
            }
        } else {
            return driver;
        }
    }

    private static void setDriverManage(){
        driver.manage().timeouts().implicitlyWait(MANAGE_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(MANAGE_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(MANAGE_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static void closeDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }

    public static File takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }


    public static WebDriver setCookie(WebDriver wDriver){
        WebDriver driver = wDriver;

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(PATH_TO_COOKIES));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream oin = null;
        try {
            oin = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Cookie> cookies = null;
        try {
            cookies = (Set<Cookie>) oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        return driver;
    }
}
