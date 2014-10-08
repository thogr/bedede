package com.github.thogr.bedede;



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
}
