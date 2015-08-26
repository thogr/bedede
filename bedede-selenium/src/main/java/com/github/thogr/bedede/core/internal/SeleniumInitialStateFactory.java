package com.github.thogr.bedede.core.internal;

import java.util.Map;

import com.github.thogr.bedede.SeleniumExpressions;
import com.github.thogr.bedede.core.internal.InitialStateFactory;
import com.github.thogr.bedede.core.internal.StateFactory;

public final class SeleniumInitialStateFactory implements InitialStateFactory {

    @Override
    public <T> T createInitialState(final StateFactory factory, final Class<T> stateClass,
            final Map<String, String> params) {

        SeleniumExpressions.getWebDriver().get(params.get("url"));
        return factory.createState(stateClass);
    }
}
