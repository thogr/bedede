package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.conditions.ExpectingExpression;
import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.state.Assuming;
import com.github.thogr.bedede.state.Entry;
import com.github.thogr.bedede.state.ThenExpecting;

public final class BehaviorController {

    private final StateMachine machine;
    private final ConditionController conditionController;

    BehaviorController(final Framework framework) {
        this(framework, framework.getStateFactory());
    }

    BehaviorController(final StateFactory factory,
            final InitialStateFactory initialStateFactory,
            final ConditionController conditionController) {
        this(new StateMachineImpl(
                factory, initialStateFactory, conditionController), conditionController);
    }

    BehaviorController(final Framework framework, final StateFactory factory) {
        this(factory, framework.getInitialStateFactory(), Framework.createConditionController());
    }

    BehaviorController(final StateMachine machine, final ConditionController conditionController) {
        this.conditionController = conditionController;
        this.machine = machine;
    }

    <T> Assuming<T> given(final Class<T> target) {
        if (!was(target)) {
            return given(EntryFinder.getDefaultEntry(target), target);
        } else {
            return assuming(target);
        }
    }

    <T> Assuming<T> given(final Entry<T> entry) {
        return givenEntry(entry);
    }

    private <T> Assuming<T> givenEntry(final AbstractInternalEntry<T> entry) {
        return given(entry, entry.getTarget());
    }

    private <T> Assuming<T> given(final AbstractInternalEntry<T> entry, final Class<T> target) {
        if (!machine.was(target)) {
            perform(entry);
            if (!machine.was(target)) {
                go(target);
            }
        }
        return assuming(target);
    }

    private <T> void perform(final StateBehavior<T> entry) {
        if (entry != null) {
            entry.perform(this);
        }
    }

    <T> Assuming<T> assuming(final Class<T> state) {
        return new AssumingImpl<T>(state, this);
    }

    <T> StateBasedWhenImpl<T> when(final ActionExpression<T> action, final Class<T> target) {
        perform(action, target);
        return new StateBasedWhenImpl<>(target, this);
    }

    <T> ThenExpecting<T> thenState(final Class<T> state) {
        next(state);
        return new ThenExpectingImpl<>(state, this);
    }

    <S, T> ThenExpecting<S> thenExpecting(
            final Class<S> state, final ExpectingExpression <S, T> expression) {
        expect(expression.apply(go(state)));
        return new ThenExpectingImpl<>(state, this);
    }

    <V> Object expect(final Expecting<V> condition) {
        return conditionController.verify(condition);
    }

    <T> T go(final Class<T> state) {
        return machine.go(state);
    }

    <T> T next(final Class<T> state) {
        return machine.next(state);
    }

    private <T> boolean was(final Class<T> target) {
        return machine.was(target);
    }

    private <T> void perform(final ActionExpression<T> action, final Class<T> target) {
        action.perform(go(target));
    }
}
