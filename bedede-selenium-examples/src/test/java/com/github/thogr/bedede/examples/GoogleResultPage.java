package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.SeleniumExpressions.expecting;
import static com.github.thogr.bedede.state.StateExpressions.otherwise;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.conditions.Expecting;

public class GoogleResultPage {

    public Expecting<ExpectedCondition<Boolean>> hasTitle(final String title) {
        return expecting(titleContains(title), otherwise("Wrong title"));
    }
}
