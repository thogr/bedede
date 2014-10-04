package com.github.thogr.bedede;

import com.github.thogr.bedede.annotations.InitialState;

final class StateMachine {

    private Object current;
    private final StateFactory factory;
    private final InitialStateFactory initialStateFactory;

    StateMachine(final StateFactory factory,
            final InitialStateFactory initialStateFactory) {
        this.factory = factory;
        this.initialStateFactory = initialStateFactory;
    }

    @SuppressWarnings("unchecked")
    <T> T go(final Class<T> state) {
        if (current == null) {
            initial(state);
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

    private <T> void initial(final Class<T> state) {
        final T initialState = initialStateFactory.createInitialState(factory,
                state, getConfigParameters(state));
        advance(state, initialState);
    }

    private static <T> Parameter[] getConfigParameters(final Class<T> state) {
        final InitialState annotation = state.getAnnotation(InitialState.class);
        if (annotation != null) {
            final String[] configs = annotation.config();
            final Parameter[] parameters = new Parameter[configs.length];
            int p = 0;
            for (final String config : configs) {
                final String[] pair = config.split("=");
                parameters[p++] = new Parameter(pair[0].trim(), pair[1].trim());
            }
            return parameters;
        } else {
            return new Parameter[]{};
        }
    }

    private <T> void next(final Class<T> state) {
        advance(state, factory.createState(state));
    }

    private <T> void advance(final Class<T> currentState, final T next) {
        final StateVerifier<T> verifier = new AnnotatedStateVerifier<>(currentState);
        if (verifier != null) {
            verifier.verify(next);
        }
        current = next;
    }
}
