package com.github.thogr.bedede;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.internal.CoreExpressionsImpl;

class SeleniumExpressionsImplementations {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    static WebDriver getWebDriver() {
        return driver.get();
    }

    static void setWebDriver(final WebDriver webdriver) {
        driver.set(webdriver);
    }

    static <T> GivenElement<T> given(
            final Expecting<ExpectedCondition<T>> precondition) {
       return CoreExpressionsImpl.given(precondition);
    }

    static <T> Expecting<ExpectedCondition<T>> expecting(
            final ExpectedCondition<T> condition, final Otherwise otherwise) {
                return CoreExpressionsImpl.expecting(condition,
                        otherwise);
    }
}
