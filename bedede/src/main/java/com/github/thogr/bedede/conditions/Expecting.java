package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.Otherwise;

public abstract class Expecting<T> {

    private final Class<T> conditionClass;

    private Expecting(final Class<T> conditionClass) {
        this.conditionClass = conditionClass;
    }

    abstract void verify(ConditionVerifier<T> verifier);

    Class<T> getConditionClass() {
        return conditionClass;
    }

    public static <T> Expecting<T> expecting(
            final T condition, final Class<T> conditionClass, final Otherwise otherwise) {

        return new Expecting<T>(conditionClass) {

            @Override
            void verify(final ConditionVerifier<T> verifier) {
                verifier.verify(condition, otherwise);
            }
        };
    }
}
