package com.github.thogr.bedede.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.github.thogr.bedede.Bedede;
import com.github.thogr.bedede.Framework;
import com.github.thogr.bedede.GivenElement;
import com.github.thogr.bedede.conditions.Expecting;

public final class BededeSelenium extends Bedede {

    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    private BededeSelenium(final Framework only) {
        super(only);
    }

    public static GivenElement<WebElement> given(final Expecting<?> exp) {
       return Bedede.Internal.<WebElement>given(exp);
    }

    public static WebDriver getWebDriver() {
        return drivers.get();
    }

    public static void setWebDriver(final WebDriver webdriver) {
        drivers.set(webdriver);
    }
}
