package com.github.thogr.bedede;

public abstract class BehaviorDriven {

    private final BehaviorController controller = new BehaviorController();

    protected final <T> GivenResult<T> given(final Class<T> target) {
        return controller.given(target);
    }

    protected final <T> GivenResult<T> given(final Entry<T> given) {
        return controller.given(given);
    }

    protected final <T> GivenResult<T> assuming(final Class<T> state) {
        return controller.assuming(state);
    }

    protected final void then(final Class<?> state) {
        controller.then(state);
    }

    protected final <S, V> void then(final Class<S> state, final ConditionMethod<S, V> c) {
        controller.then(state, c);
    }

    protected final <V> void then(final Condition<V> condition) {
        controller.then(condition);
    }

    public static Otherwise otherwise(final String message) {
        return Otherwise.otherwise(message);
    }

    public static <T> Condition<T> expecting(final T condition, final Otherwise otherwise) {
        return Expecting.expecting(condition, otherwise);
    }
}
