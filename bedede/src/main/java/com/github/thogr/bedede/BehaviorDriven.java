package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.Condition;


public abstract class BehaviorDriven {

    private final BehaviorController controller = Framework.createBehaviorController();

    protected final <T> Assuming<T> given(final Class<T> target) {
        return controller.given(target);
    }

    protected final <T> Assuming<T> given(final Entry<T> given) {
        return controller.given(given);
    }

    protected final <T> Assuming<T> assuming(final Class<T> state) {
        return controller.assuming(state);
    }

    protected final <T> Then<T> then(final Class<T> state) {
        return controller.then(state);
    }

    public static Otherwise otherwise(final String message) {
        return Otherwise.otherwise(message);
    }

    public static <T> Condition<T> expecting(
            final T condition, final Otherwise otherwise) {
        return Condition.expecting(condition, otherwise);
    }

}
