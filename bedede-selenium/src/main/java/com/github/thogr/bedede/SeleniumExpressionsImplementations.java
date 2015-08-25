package com.github.thogr.bedede;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.conditions.Expecting;

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
       return CoreExpressionsImplementations.given(precondition);
    }

    static <T> Expecting<ExpectedCondition<T>> expecting(
            final ExpectedCondition<T> condition, final Otherwise otherwise) {
                return CoreExpressionsImplementations.expecting(condition,
                        otherwise);
    }
}
