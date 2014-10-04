package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.Condition;
import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.conditions.ConditionExpression;

final class BehaviorController {

    private final StateMachine machine;
    private final ConditionController conditionController;

    BehaviorController(final Framework framework) {
        this(framework, framework.getStateFactory());
    }

    BehaviorController(final StateFactory factory,
            final InitialStateFactory initialStateFactory,
            final ConditionController conditionController) {
        this(new StateMachine(factory, initialStateFactory), conditionController);
    }

    BehaviorController(final Framework framework, final StateFactory factory) {
        this(factory, framework.getInitialStateFactory(), framework.createConditionController());
    }

    BehaviorController(final StateMachine machine, final ConditionController conditionController) {
        this.conditionController = conditionController;
        this.machine = machine;
    }

    <T> Assuming<T> given(final Class<T> target) {
        if (!was(target)) {
            return given(EntryFinder.getDefaultEntry(target));
        } else {
            return assuming(target);
        }
    }

    <T> Assuming<T> given(final Entry<T> entry) {
        final Class<T> target = target(entry);
        if (!machine.was(target)) {
            entry.perform(this);
        }
        return assuming(target);
    }

    <T> Assuming<T> assuming(final Class<T> state) {
        return new Assuming<T>(state, this);
    }

    <T> void when(final Action<? super T> action) {
        go(target(action));
        action.perform(this);
    }

    <T> void when(final ActionMethod<T> action) {
        action.perform(go(target(action)));
    }

    <T> Then<T> then(final Class<T> state) {
        go(state);
        return new Then<>(state, this);
    }

    <S, T> void should(final Class<S> state, final ConditionExpression <S, T> expression) {
        should(expression.apply(go(state)));
    }

    <V> void should(final Condition<V> condition) {
        conditionController.verify(condition);
    }

    <T> T go(final Class<T> state) {
        return machine.go(state);
    }

    private <T> Class<T> target(final ActionMethod<T> action) {
        return TypeArguments.typeArgument(action);
    }

    private <T> Class<T> target(final Action<? super T> action) {
        return TypeArguments.typeArgument(action);
    }

    private <T> Class<T> target(final Entry<? super T> entry) {
        return TypeArguments.typeArgument(entry);
    }

    private <T> boolean was(final Class<T> target) {
        return machine.was(target);
    }
}
