package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.Otherwise;

public abstract class Expecting<T> {

    private final Class<T> conditionClass;

    private Expecting(final Class<T> conditionClass) {
        this.conditionClass = conditionClass;
    }

    abstract Object verify(ConditionVerifier<T> verifier);

    public Expecting<T> and(final Expecting<T> other) {
        return new Expecting<T>(conditionClass) {

            @Override
            Object verify(final ConditionVerifier<T> verifier) {
                Expecting.this.verify(verifier);
                return other.verify(verifier);
            }
        };
    }

    Class<T> getConditionClass() {
        return conditionClass;
    }

    public static <T> Expecting<T> expecting(
            final T condition, final Class<T> conditionClass, final Otherwise otherwise) {

        return new Expecting<T>(conditionClass) {

            @Override
            Object verify(final ConditionVerifier<T> verifier) {
                return verifier.verify(condition, otherwise);
            }
        };
    }
}
