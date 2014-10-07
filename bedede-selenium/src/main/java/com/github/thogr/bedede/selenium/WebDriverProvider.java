package com.github.thogr.bedede.selenium;

import org.openqa.selenium.WebDriver;

public final class WebDriverProvider {

    private WebDriverProvider() {

    }

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        return drivers.get();
    }

    public static void setWebDriver(final WebDriver webdriver) {
        drivers.set(webdriver);
    }
}
