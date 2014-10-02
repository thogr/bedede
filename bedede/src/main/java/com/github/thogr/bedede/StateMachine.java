package com.github.thogr.bedede;

import com.github.thogr.bedede.annotations.InitialState;

final class StateMachine {

    private Object current;
    private final StateFactory factory;
    private final InitialStateFactory initialStateFactory;

    StateMachine(final StateFactory factory, final InitialStateFactory initialStateFactory) {
        this.factory = factory;
        this.initialStateFactory = initialStateFactory;
    }

    @SuppressWarnings("unchecked")
    <T> T go(final Class<T> state) {
        if (current == null) {
            if (isInitial(state)) {
                initial(state);
            } else {
                throw new IllegalStateException(
                        state + " not a start state");
            }
        } else {
            if (!was(state)) {
                next(state);
            }
        }

        return (T) current;
    }

    <T> boolean was(final Class<T> state) {
        return current != null && state.isAssignableFrom(current.getClass());
    }

    private boolean isInitial(final Class<?> state) {
        return state.isAnnotationPresent(InitialState.class);
    }

    private <T> void initial(final Class<T> state) {
        final T initialState =
                initialStateFactory.createInitialState(factory, state, getConfigParameters(state));
        advance(state, initialState);
    }

    private static <T> Parameter[] getConfigParameters(final Class<T> state) {
        final InitialState annotation = state.getAnnotation(InitialState.class);
        final String[] configs = annotation.config();
        final Parameter[] parameters = new Parameter[configs.length];
        int p = 0;
        for (final String config : configs) {
            final String[] pair = config.split("=");
            parameters[p++] = new Parameter(pair[0].trim(), pair[1].trim());
        }
        return parameters;
    }

    private <T> void next(final Class<T> state) {
        advance(state, factory.createState(state));
    }

    @SuppressWarnings("unchecked")
    private <T> void advance(final Class<T> currentState, final T next) {
        final StateVerifier<T> verifier = (StateVerifier<T>) StateVerifier.verifierOf(currentState);
        verifier.verify(next);
        current = next;
    }
}
