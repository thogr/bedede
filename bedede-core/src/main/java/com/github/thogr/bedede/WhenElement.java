package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.Expecting;

public interface WhenElement<E> {

    ThenElement<E> then();

    ThenExpectingElement<E> then(final Expecting<?> exp);

    WhenElement<E> when(final ActionExpression<E> expression);

}
