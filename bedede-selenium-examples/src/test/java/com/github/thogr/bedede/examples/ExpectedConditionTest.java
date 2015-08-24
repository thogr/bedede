package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.Bedede.given;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.thogr.bedede.selenium.BededeSelenium;

public class ExpectedConditionTest {

    @BeforeClass
    public static void configure() {
        BededeSelenium.setWebDriver(new FirefoxDriver());
    }

    @AfterClass
    public static void close() {
        BededeSelenium.getWebDriver().close();
    }

    @Test
    public void test() {
        given(GoogleSearchPage.class)
        .when(it -> it.searchesFor("Selenium"))
        .then(GoogleResultPage.class)
        .then(it -> it.hasTitle("Selenium"));
    }

}

