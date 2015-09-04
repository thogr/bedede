package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.conditions.ConditionController;

public class ConditionControllerImpl implements ConditionController {

    private final Framework framework;

    public ConditionControllerImpl(final Framework framework) {
        this.framework = framework;
    }

    @Override
    public final <T> Object verify(final Verifiable<T> condition) {
        return condition.verify(framework.getVerifier(condition.getConditionClass()));
    }
}
