package com.github.thogr.bedede.core;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.state.ThenElement;
import com.github.thogr.bedede.state.ThenExpectingElement;

public interface WhenElement<E> {

    ThenElement<E> then();

    ThenExpectingElement<E> then(final Expecting<?> exp);

    WhenElement<E> when(final ActionExpression<E> expression);

}
