package com.github.thogr.bedede.selenium.internal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.SeleniumExpressions;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.conditions.Otherwise;
import com.github.thogr.bedede.core.internal.Internal;
import com.github.thogr.bedede.state.GivenElement;
import com.github.thogr.bedede.state.internal.StateExpressionsImpl;

public final class SeleniumExpressionsImpl {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Internal
    public SeleniumExpressionsImpl(final SeleniumExpressions expr) {
        if (expr == null) {
            throw new NullPointerException();
        }
    }

    @Internal
    public WebDriver getWebDriver() {
        return driver.get();
    }

    @Internal
    public void setWebDriver(final WebDriver webdriver) {
        driver.set(webdriver);
    }

    @Internal
    public <T> GivenElement<T> given(
            final Expecting<ExpectedCondition<T>> precondition) {
       return StateExpressionsImpl.given(precondition);
    }

    @Internal
    public <T> Expecting<ExpectedCondition<T>> expecting(
            final ExpectedCondition<T> condition, final Otherwise otherwise) {
                return StateExpressionsImpl.expecting(condition,
                        otherwise);
    }
}
