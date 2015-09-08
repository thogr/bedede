package com.github.thogr.bedede;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.CoreExpressions;
import com.github.thogr.bedede.state.GivenElement;

public final class SeleniumExpressions extends CoreExpressions {

    private SeleniumExpressions() {
    }

    public static <T> GivenElement<T> given(
            final Expecting<ExpectedCondition<T>> precondition) {
       return SeleniumExpressionsImplementations.given(precondition);
    }

    public static <T> Expecting<ExpectedCondition<T>> expecting(
            final ExpectedCondition<T> condition, final Otherwise otherwise) {
                return SeleniumExpressionsImplementations.expecting(condition,
                        otherwise);
    }

    public static WebDriver getWebDriver() {
        return SeleniumExpressionsImplementations.getWebDriver();
    }

    public static void setWebDriver(final WebDriver webdriver) {
        SeleniumExpressionsImplementations.setWebDriver(webdriver);
    }
}
