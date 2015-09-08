package com.github.thogr.bedede;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.state.GivenElement;
import com.github.thogr.bedede.state.StateExpressions;
import com.github.thogr.bedede.state.internal.StateExpressionsImpl;

final class SeleniumExpressionsImplementations {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private SeleniumExpressionsImplementations() {}

    static WebDriver getWebDriver() {
        return driver.get();
    }

    static void setWebDriver(final WebDriver webdriver) {
        driver.set(webdriver);
    }

    static <T> GivenElement<T> given(
            final Expecting<ExpectedCondition<T>> precondition) {
       return StateExpressions.given(precondition);
    }

    static <T> Expecting<ExpectedCondition<T>> expecting(
            final ExpectedCondition<T> condition, final Otherwise otherwise) {
                return StateExpressionsImpl.expecting(condition,
                        otherwise);
    }
}
