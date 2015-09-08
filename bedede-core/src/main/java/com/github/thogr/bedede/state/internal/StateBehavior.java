package com.github.thogr.bedede.state.internal;

abstract class StateBehavior<T> {

    private final ThreadLocal<BehaviorController> behaviorController = new ThreadLocal<>();

    protected abstract void perform();

    final void perform(final BehaviorController controller) {
        this.behaviorController.set(controller);
        perform();
    }

    final BehaviorController getController() {
        if (behaviorController.get() != null) {
            return behaviorController.get();
        }
        throw new IllegalAccessError("Illegal call to perform()");
    }
}
