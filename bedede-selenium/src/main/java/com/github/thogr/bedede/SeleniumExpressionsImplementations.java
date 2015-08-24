package com.github.thogr.bedede;

import org.openqa.selenium.WebDriver;

class SeleniumExpressionsImplementations {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    static WebDriver getWebDriver() {
        return driver.get();
    }

    static void setWebDriver(final WebDriver webdriver) {
        driver.set(webdriver);
    }
}
