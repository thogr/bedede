package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.conditions.ExpectingExpression;
import com.github.thogr.bedede.conditions.Expecting;

final class BehaviorController {

    private final StateMachine machine;
    private final ConditionController conditionController;

    BehaviorController(final Framework framework) {
        this(framework, framework.getStateFactory());
    }

    BehaviorController(final StateFactory factory,
            final InitialStateFactory initialStateFactory,
            final ConditionController conditionController) {
        this(new StateMachine(factory, initialStateFactory, conditionController), conditionController);
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
        return given(entry, target(entry));
    }

    private <T> Assuming<T> given(final Entry<T> entry, final Class<T> target) {
        if (!machine.was(target)) {
            perform(entry);
            if (!machine.was(target)) {
                go(target);
            }
        }
        return assuming(target);
    }

    private <T> void perform(final Entry<T> entry) {
        if (entry != null) {
            entry.perform(this);
        }
    }

    <T> Assuming<T> assuming(final Class<T> state) {
        return new Assuming<T>(state, this);
    }
/*
    <T> void when(final Action<? super T> action) {
        go(target(action));
        action.perform(this);
    }
    */

    <T> When<T> when(final ActionExpression<T> action, final Class<T> target) {
        action.perform(go(target));
        return new When<>(target, this);
    }

    <T> Then<T> then(final Class<T> state) {
        go(state);
        return new Then<>(state, this);
    }

    <S, T> Then<S> expect(final Class<S> state, final ExpectingExpression <S, T> expression) {
        expect(expression.apply(go(state)));
        return new Then<>(state, this);
    }

    <V> Object expect(final Expecting<V> condition) {
        return conditionController.verify(condition);
    }

    <T> T go(final Class<T> state) {
        return machine.go(state);
    }
/*
    private <T> Class<T> target(final Action<? super T> action) {
        return TypeArguments.typeArgument(action);
    }
*/
    private <T> Class<T> target(final Entry<? super T> entry) {
        return TypeArguments.typeArgument(entry);
    }

    private <T> boolean was(final Class<T> target) {
        return machine.was(target);
    }
}
