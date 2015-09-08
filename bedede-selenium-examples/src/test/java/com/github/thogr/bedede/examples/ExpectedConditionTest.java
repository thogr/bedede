package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.core.CoreExpressions.given;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.github.thogr.bedede.SeleniumExpressions;
import com.github.thogr.bedede.state.StateExpressions;

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
        StateExpressions.given(GoogleSearchPage.class)
        .when(it -> it.searchesFor("Selenium"))
        .then(GoogleResultPage.class)
        .then(it -> it.hasTitle("Selenium"));
    }

}

