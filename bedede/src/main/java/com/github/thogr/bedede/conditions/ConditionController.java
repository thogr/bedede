package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.Framework;

public class ConditionController {

    private final Framework framework;

    public ConditionController(final Framework framework) {
        this.framework = framework;
    }

    public <T> void verify(final Condition<T> condition) {
        condition.verify(getVerifier(condition));
    }

    private <V> ConditionVerifier<V> getVerifier(final Condition<V> condition) {
        return framework.getVerifier(condition.getConditionClass());
    }
}
