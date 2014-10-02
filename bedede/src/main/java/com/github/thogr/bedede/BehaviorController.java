package com.github.thogr.bedede;

final class BehaviorController {

    private final StateMachine machine;

    BehaviorController() {
        this(Framework.getStateFactory());
    }

    BehaviorController(final StateFactory factory, final InitialStateFactory initialStateFactory) {
        this(new StateMachine(factory, initialStateFactory));
    }

    BehaviorController(final StateFactory factory) {
        this(factory, Framework.getInitialStateFactory());
    }

    BehaviorController(final StateMachine machine) {
        this.machine = machine;
    }

    <T> GivenResult<T> given(final Class<T> target) {
        if (!was(target)) {
            return given(EntryUtil.getDefaultEntry(target));
        } else {
            return assuming(target);
        }
    }

    <T> GivenResult<T> given(final Entry<T> entry) {
        final Class<T> target = target(entry);
        if (!machine.was(target)) {
            entry.perform(this);
        }
        return assuming(target);
    }

    <T> GivenResult<T> assuming(final Class<T> state) {
        return new GivenResult<T>(this, state);
    }

    <T> void when(final Action<? super T> action) {
        go(target(action));
        action.perform(this);
    }

    <T> void when(final ActionMethod<T> action) {
        action.perform(go(target(action)));
    }

    <T> void then(final Class<T> state) {
        go(state);
    }

    <S, V> void then(final Class<S> state, final ConditionMethod<S, V> condition) {
        then(state, condition, verifier(condition));
    }

    <V> void then(final Condition<V> condition) {
        then(condition, verifier(condition));
    }

    <T> T go(final Class<T> state) {
        return machine.go(state);
    }

    private <T, V> void then(final Class<T> state, final ConditionMethod<T, V> condition, final ConditionVerifier<V> verifier) {
        condition.condition(go(state)).verify(verifier);
    }

    private <T> void then(final Condition<T> condition, final ConditionVerifier<T> verifier) {
        condition.verify(verifier);
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

    private <V> ConditionVerifier<V> verifier(final Condition<V> condition) {
        return verifier(condition.getConditionClass());
    }

    private <S, V> ConditionVerifier<V> verifier(final ConditionMethod<S, V> condition) {
        return verifier(TypeArguments.typeArgument(condition, 1));
    }

    private <V> ConditionVerifier<V> verifier(final Class<V> conditionClass) {
        return Framework.getVerifier(conditionClass);
    }

}
