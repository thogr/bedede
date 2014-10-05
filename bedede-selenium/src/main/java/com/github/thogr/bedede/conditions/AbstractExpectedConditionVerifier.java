package com.github.thogr.bedede.conditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.thogr.bedede.Otherwise;
import com.github.thogr.bedede.selenium.WebDriverProvider;

public abstract class AbstractExpectedConditionVerifier<T>
    implements ConditionVerifier<ExpectedCondition<T>> {

    private static final long TIMEOUT_SECONDS = 40;

    public AbstractExpectedConditionVerifier() {
        super();
    }

    @Override
    public void verify(final ExpectedCondition<T> condition, final Otherwise otherwise) {

        final WebDriver driver = WebDriverProvider.getWebDriver();
        final WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
        wait.until (new ExpectedCondition<T>() {

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
