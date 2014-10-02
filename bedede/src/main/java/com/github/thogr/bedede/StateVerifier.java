package com.github.thogr.bedede;

import com.github.thogr.bedede.annotations.State;

public interface StateVerifier<T> {
    void verify(T state);

    public static StateVerifier<?> verifierOf(final Class<?> state) {
        final State stateAnnotation = state.getAnnotation(State.class);
        if (stateAnnotation != null) {
            final Class<? extends StateVerifier<?>> verifier =
                stateAnnotation.verifier();
            try {
                return verifier.newInstance();
            } catch (final InstantiationException e) {
                throw new IllegalStateException(e);
            } catch (final IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        } else {
            final Class<?> superClass = state.getSuperclass();
            if (superClass != null) {
                return verifierOf(superClass);
            } else {
                throw new IllegalStateException(
                        "Class " +
                        state +
                        " is not a State class");
            }
        }
    }
}
