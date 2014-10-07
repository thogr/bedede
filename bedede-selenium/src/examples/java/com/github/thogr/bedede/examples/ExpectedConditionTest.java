package com.github.thogr.bedede.examples;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.selenium.WebDriverProvider;

public class ExpectedConditionTest extends BehaviorDriven {

    @InitialState(config = "url=http://www.google.com")
    public static class StartPage {

        @OnEntry
        public Expecting<ExpectedCondition<WebElement>> hasQueryField() {
            return expecting(visibilityOfElementLocated(By.xpath("//*[@name='q']")),
                    otherwise("No query field"));
        }

    }

    @BeforeClass
    public static void configure() {
        WebDriverProvider.setWebDriver(new FirefoxDriver());
    }

    @AfterClass
    public static void close() {
        WebDriverProvider.getWebDriver().close();
    }

    @Test
    public void test() {
        given(StartPage.class);
    }

}
