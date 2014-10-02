package com.github.thogr.bedede;

public abstract class Condition<T> {

    private final Class<T> conditionClass;

    Condition(final Class<T> conditionClass) {
        this.conditionClass = conditionClass;
    }

    abstract void verify(ConditionVerifier<T> verifier);

    public Class<T> getConditionClass() {
        return conditionClass;
    }

}
