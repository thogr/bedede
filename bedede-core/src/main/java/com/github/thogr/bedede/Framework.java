package com.github.thogr.bedede;

import java.util.ServiceLoader;

import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.conditions.ConditionControllerImpl;
import com.github.thogr.bedede.conditions.ConditionProvider;
import com.github.thogr.bedede.conditions.ConditionVerifier;
import com.github.thogr.bedede.internal.InitialStateFactory;
import com.github.thogr.bedede.internal.StateFactory;

public final class Framework {
    private static final String NO_SERVICE_PROVIDED =
            "No appropriate %s provided in META-INF/services";

    private Framework() {

    }

    static BehaviorController createBehaviorController() {
        return new BehaviorController(new Framework());
    }

    static ConditionController createConditionController() {
        return new ConditionControllerImpl(new Framework());
    }

    @SuppressWarnings({ "unchecked" })
    public <V> ConditionVerifier<V> getVerifier(final Class<V> conditionClass) {
        final ServiceLoader<ConditionProvider> sl = ServiceLoader.load(ConditionProvider.class);
        for (final ConditionProvider provider : sl) {
            try {
                final ConditionVerifier<V> verifier =
                        (ConditionVerifier<V>) provider.getVerifier(conditionClass);
                if (verifier != null) {
                    return verifier;
                }
            } catch (final ClassCastException e) {
                // continue
            }
        }
        throw new IllegalArgumentException(String.format(NO_SERVICE_PROVIDED, ConditionProvider.class.getName()));
    }

    InitialStateFactory getInitialStateFactory() {
        final ServiceLoader<InitialStateFactory> sl = ServiceLoader.load(InitialStateFactory.class);
        for (final InitialStateFactory initialStateFactory : sl) {
            return initialStateFactory;
        }

        throw new IllegalArgumentException(String.format(NO_SERVICE_PROVIDED, InitialStateFactory.class.getName()));
    }

    StateFactory getStateFactory() {
        final ServiceLoader<StateFactory> sl = ServiceLoader.load(StateFactory.class);
        for (final StateFactory stateFactory : sl) {
            return stateFactory;
        }

        throw new IllegalArgumentException(String.format(NO_SERVICE_PROVIDED, StateFactory.class.getName()));
    }

}
