package com.github.thogr.bedede;

abstract class Behavior<T> {

    private BehaviorController controller;

    protected abstract void perform();

    final void perform(final BehaviorController controller) {
        this.controller = controller;
        perform();
    }

    final BehaviorController getController() {
        if (controller != null) {
            return controller;
        }
        throw new IllegalAccessError("Illegal call to perform()");
    }
}
