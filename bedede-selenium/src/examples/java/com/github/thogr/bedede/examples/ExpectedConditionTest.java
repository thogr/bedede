package com.github.thogr.bedede.examples;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.Expecting;

public class ExpectedConditionTest extends BehaviorDriven {

    @InitialState
    public static class StartPage {
        WebElement text;

        @OnEntry
        public Expecting<ExpectedCondition<WebElement>> hasVisibleText() {
            return expecting(visibilityOf(text), otherwise("No text"));
        }

    }

    @Test
    public void test() {
        given(StartPage.class);
    }

}
