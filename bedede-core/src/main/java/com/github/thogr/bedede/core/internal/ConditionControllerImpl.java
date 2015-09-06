package com.github.thogr.bedede.core.internal;


public class ConditionControllerImpl implements ConditionController {

    private final Framework framework;

    ConditionControllerImpl(final Framework framework) {
        this.framework = framework;
    }

    @Override
    public final <T> Object verify(final Verifiable<T> condition) {
        return condition.verify(framework.getVerifier(condition.getConditionClass()));
    }
}
