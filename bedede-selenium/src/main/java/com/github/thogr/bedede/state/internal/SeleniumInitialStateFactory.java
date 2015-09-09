package com.github.thogr.bedede.state.internal;

import java.util.Map;

import com.github.thogr.bedede.SeleniumExpressions;

public final class SeleniumInitialStateFactory implements InitialStateFactory {

    @Override
    public <T> T createInitialState(final StateFactory factory, final Class<T> stateClass,
            final Map<String, String> params) {

        SeleniumExpressions.getWebDriver().get(params.get("url"));
        return factory.createState(stateClass);
    }
}
