package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.Bedede.expecting;
import static com.github.thogr.bedede.Bedede.otherwise;
import static com.github.thogr.bedede.selenium.BededeSelenium.given;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.Expecting;

@InitialState(config = "url=http://www.google.com")
public class GoogleSearchPage {

    @OnEntry
    public Expecting<ExpectedCondition<WebElement>> queryField() {
        return expecting(visibilityOfElementLocated(By.xpath("//*[@name='q']")),
                otherwise("No query field"));
    }

    public Expecting<ExpectedCondition<WebElement>> searchButton() {
        return expecting(visibilityOfElementLocated(By.id("gbqfb")),
                otherwise("search button not found"));
    }

    public void searchesFor(final String searchString) {
        given(queryField())
        .when(it -> it.sendKeys(searchString))
        .when(it -> it.submit())
        .then()
        .given(searchButton())
        .when(it -> it.click())
        .then().done();
    }
}
