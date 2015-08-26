package com.github.thogr.bedede.core.internal;

import java.util.HashMap;
import java.util.Map;

import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.conditions.ConditionController;

final class StateMachineImpl implements StateMachine {

    private Object current;
    private final StateFactory factory;
    private final InitialStateFactory initialStateFactory;
    private final ConditionController conditionController;

    StateMachineImpl(final StateFactory factory,
            final InitialStateFactory initialStateFactory,
            final ConditionController conditionController) {
        this.factory = factory;
        this.initialStateFactory = initialStateFactory;
        this.conditionController = conditionController;
    }

    @Override
    @SuppressWarnings("unchecked")
    public
    <T> T go(final Class<T> state) {
        if (current == null) {
            initial(state);
        } else if (!was(state)) {
            next(state);
        }

        return (T) current;
    }

    @Override
    public <T> boolean was(final Class<T> state) {
        return current != null && state.isAssignableFrom(current.getClass());
    }

    private <T> void initial(final Class<T> state) {
        final T initialState = initialStateFactory.createInitialState(factory,
                state, getConfigParameters(state));
        advance(state, initialState);
    }

    private static <T> Map<String, String> getConfigParameters(final Class<T> state) {
        final InitialState annotation = state.getAnnotation(InitialState.class);
        final Map<String, String> parameters = new HashMap<>();
        if (annotation != null) {
            final String[] configs = annotation.config();
            for (final String config : configs) {
                final String[] pair = config.split("=");
                parameters.put(pair[0].trim(), pair[1].trim());
            }
        }
        return parameters;
    }

    @Override
    public <T> T next(final Class<T> state) {
        return advance(state, factory.createState(state));
    }

    private <T> T advance(final Class<T> state, final T next) {
        final StateVerifier<T> verifier = new AnnotatedStateVerifier<>(state, conditionController);
        verifier.verify(next);
        current = next;
        return next;
    }
}
