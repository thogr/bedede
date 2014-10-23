package com.github.thogr.bedede.conditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.thogr.bedede.Otherwise;
import com.github.thogr.bedede.selenium.BededeSelenium;

public abstract class AbstractExpectedConditionVerifier<T>
    implements ConditionVerifier<ExpectedCondition<T>> {

    // TODO make timeout configurable
    private static final long TIMEOUT_SECONDS = 40;

    public AbstractExpectedConditionVerifier() {
        super();
    }

    @Override
    public T verify(final ExpectedCondition<T> condition, final Otherwise otherwise) {

        final WebDriver driver = BededeSelenium.getWebDriver();
        final WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
        return wait.until (new ExpectedCondition<T>() {

            @Override
            public T apply(final WebDriver input) {
                return condition.apply(driver);
            }

            @Override
            public String toString() {
                return otherwise.getMessage();
            };
        });
    }
}
