package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.CoreExpressions.performing;
import static com.github.thogr.bedede.StateExpressions.at;
import static com.github.thogr.bedede.StateExpressions.given;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.thogr.bedede.SeleniumExpressions;

public class ExpectedConditionTest {

    @BeforeClass
    public static void configure() {
        SeleniumExpressions.setWebDriver(new FirefoxDriver());
    }

    @AfterClass
    public static void close() {
        SeleniumExpressions.getWebDriver().close();
    }

    @Test
    public void test() {
        given(at(GoogleSearchPage.class))
        .when(performing(it -> it.searchesFor("Selenium")))
        .then(at(GoogleResultPage.class))
        .then(it -> it.hasTitle("Selenium"));
    }
}

