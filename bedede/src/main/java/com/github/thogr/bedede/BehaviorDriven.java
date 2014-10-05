package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;


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

    public static <T> Expecting<T> expecting(
            final T condition, final Otherwise otherwise) {
        @SuppressWarnings("unchecked")
        final Class<T> conditionClass = (Class<T>) condition.getClass();
        return Expecting.expecting(condition, conditionClass, otherwise);
    }

    public static Expecting<BooleanCondition> expecting(
            final Boolean condition, final Otherwise otherwise) {
        return Expecting.expecting(() -> condition, BooleanCondition.class, otherwise);
    }

}
