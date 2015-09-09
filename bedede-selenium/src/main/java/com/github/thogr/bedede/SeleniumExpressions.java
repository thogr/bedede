package com.github.thogr.bedede;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.conditions.Otherwise;
import com.github.thogr.bedede.selenium.internal.SeleniumExpressionsImpl;
import com.github.thogr.bedede.state.GivenElement;
import com.github.thogr.bedede.state.StateExpressions;

public final class SeleniumExpressions extends StateExpressions {

    private static SeleniumExpressionsImpl impl =
            new SeleniumExpressionsImpl(new SeleniumExpressions());

    private SeleniumExpressions() {
    }

    public static <T> GivenElement<T> given(
            final Expecting<ExpectedCondition<T>> precondition) {
       return impl.given(precondition);
    }

    public static <T> Expecting<ExpectedCondition<T>> expecting(
            final ExpectedCondition<T> condition, final Otherwise otherwise) {
                return impl.expecting(condition,
                        otherwise);
    }

    public static WebDriver getWebDriver() {
        return impl.getWebDriver();
    }

    public static void setWebDriver(final WebDriver webdriver) {
        impl.setWebDriver(webdriver);
    }
}
