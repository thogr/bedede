package com.github.thogr.bedede;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.conditions.Expecting;

public final class BededeSelenium extends Bedede {

    private BededeSelenium(final Framework only) {
        super(only);
    }

    public static <T> GivenElement<T> given(
            final Expecting<ExpectedCondition<T>> precondition) {
       return CoreExpressionsImplementations.given(precondition);
    }
    public static <T> Expecting<ExpectedCondition<T>> expecting(
            final ExpectedCondition<T> condition, final Otherwise otherwise) {
                return CoreExpressionsImplementations.expecting(condition,
                        otherwise);
    }

    public static WebDriver getWebDriver() {
        return SeleniumExpressionsImplementations.getWebDriver();
    }

    public static void setWebDriver(final WebDriver webdriver) {
        SeleniumExpressionsImplementations.setWebDriver(webdriver);
    }
}
