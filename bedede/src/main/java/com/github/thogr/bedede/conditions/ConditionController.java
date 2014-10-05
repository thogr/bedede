package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.Framework;

public class ConditionController {

    private final Framework framework;

    public ConditionController(final Framework framework) {
        this.framework = framework;
    }

    public <T> void verify(final Expecting<T> condition) {
        condition.verify(framework.getVerifier(condition.getConditionClass()));
    }
}
