package com.github.thogr.bedede.internal;

import java.util.Map;

import com.github.thogr.bedede.internal.InitialStateFactory;
import com.github.thogr.bedede.internal.StateFactory;
import com.github.thogr.bedede.selenium.BededeSelenium;

public final class SeleniumInitialStateFactory implements InitialStateFactory {

    @Override
    public <T> T createInitialState(final StateFactory factory, final Class<T> stateClass,
            final Map<String, String> params) {

        BededeSelenium.getWebDriver().get(params.get("url"));
        return factory.createState(stateClass);
    }
}
