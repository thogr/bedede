package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.Framework;

public class ConditionController {

    private final Framework framework;

    public ConditionController(final Framework framework) {
        this.framework = framework;
    }

    public final <T> Object verify(final Expecting<T> condition) {
        return condition.verify(framework.getVerifier(condition.getConditionClass()));
    }
}
