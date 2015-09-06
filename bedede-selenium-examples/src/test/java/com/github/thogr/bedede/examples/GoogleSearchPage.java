package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.SeleniumExpressions.expecting;
import static com.github.thogr.bedede.SeleniumExpressions.given;
import static com.github.thogr.bedede.core.CoreExpressions.otherwise;
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

    /**
     * Example
     * @return the expecting
     */
    public Expecting<ExpectedCondition<WebElement>> searchButton() {
        return expecting(visibilityOfElementLocated(By.xpath("//*[@type='submit']")),
                otherwise("search button not found"));
    }

    /**
     * Example
     * @param searchString the searched string
     */
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
