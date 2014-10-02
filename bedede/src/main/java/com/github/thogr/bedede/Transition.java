package com.github.thogr.bedede;
/**
 * An action that will change state
 *
 * @param <Before> current state class
 * @param <After> state class after transition
 */
public abstract class Transition<Before, After> extends
Action<Before> {

    protected final void then(final Class<After> next) {
        getController().then(next);
    }
}
