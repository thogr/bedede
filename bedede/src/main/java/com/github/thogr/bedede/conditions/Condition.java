package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.Otherwise;


public abstract class Condition<T> {

    private final T condition;

    private Condition(final T condition) {
        this.condition = condition;
    }

    abstract void verify(ConditionVerifier<T> verifier);

    @SuppressWarnings("unchecked")
    Class<T> getConditionClass() {
        return (Class<T>) condition.getClass();
    }

    public static <T> Condition<T> expecting(
            final T condition, final Otherwise otherwise) {

        return new Condition<T>(condition) {

            @Override
            void verify(final ConditionVerifier<T> verifier) {
                verifier.verify(condition, otherwise);
            }
        };
    }

}
