package com.github.thogr.bedede.examples

import static com.github.thogr.bedede.BededeSelenium.*
import static org.openqa.selenium.support.ui.ExpectedConditions.*

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.ExpectedCondition

import com.github.thogr.bedede.annotations.InitialState
import com.github.thogr.bedede.annotations.OnEntry
import com.github.thogr.bedede.conditions.Expecting

class GroovyConditionTest {

    @BeforeClass
    static void setUpClass() {
        setWebDriver(new FirefoxDriver())
    }

    @AfterClass
    static void tearDownClass() {
        getWebDriver().close()
    }


    @InitialState(config = "url=http://www.google.com")
    static class GroovySearchPage {

        @OnEntry
        def Expecting<ExpectedCondition<WebElement>> queryField() {
            expecting(visibilityOfElementLocated(By.xpath("//*[@name='q']")),
                    otherwise("No query field"))
        }

        def Expecting<ExpectedCondition<WebElement>> searchButton() {
            expecting(visibilityOfElementLocated(By.xpath("//*[@type='submit']")),
                    otherwise("search button not found"))
        }

        def searchesFor(final String searchString) {
            given(queryField())
            .when {it.sendKeys(searchString)}
            .when {it.submit()}
            .then()
            .given(searchButton())
            .when {it.click()}
            .then().done()
        }
    }

    static class GroovyResultPage {
        def Expecting<ExpectedCondition<Boolean>> hasTitle(title) {
            expecting(titleContains(title), otherwise("Wrong title"))
        }
    }

    @Test
    void test() {
        given(GroovySearchPage)
        .when{it.searchesFor("Selenium")}
        .then(GroovyResultPage)
        .then{it.hasTitle("Selenium")}
    }

}

