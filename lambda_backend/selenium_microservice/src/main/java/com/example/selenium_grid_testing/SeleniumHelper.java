package com.example.selenium_grid_testing;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumHelper {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static String remote_url_chrome = "http://localhost:4444/";
    public static String remote_url_firefox = "http://localhost:4444/";
 
    public void setupThread(String browserName) throws MalformedURLException
    {
        if(browserName.equalsIgnoreCase("remote-chrome"))
        {
            System.out.println("Inside Chrome");
            ChromeOptions options = new ChromeOptions();
            driver.set(new RemoteWebDriver(new URL(remote_url_chrome), options));
        }
        else if (browserName.equalsIgnoreCase("remote-firefox"))
        {
            System.out.println("Inside Firefox");
            FirefoxOptions options = new FirefoxOptions();
            driver.set(new RemoteWebDriver(new URL(remote_url_firefox), options));
        }
    }
 
    public WebDriver getDriver()
    {
        return driver.get();
    }
 
    public void tearDownDriver()
    {
        getDriver().quit();
    }
}