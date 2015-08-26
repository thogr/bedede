package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public interface StateBasedWhen<S> {

    <T> ThenExpecting<T> then(Class<T> target);

    StateBasedWhen<S> when(ActionExpression<S> action);

    <V> ThenExpecting<S> then(final ExpectingExpression<S, V> epression);
}
