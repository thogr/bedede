package com.github.thogr.bedede.core.internal;


public abstract class StateBehavior<T> {

    private final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();

    protected abstract void perform();

    final void perform(final BehaviorController controller) {
        this.controller.set(controller);
        perform();
    }

    final BehaviorController getController() {
        if (controller.get() != null) {
            return controller.get();
        }
        throw new IllegalAccessError("Illegal call to perform()");
    }
}
