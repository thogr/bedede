package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.Expecting;


public class WhenElement<E> {

    private final E elem;

    WhenElement(final E elem) {
        this.elem = elem;
    }

    public WhenElement<E> when(final ActionExpression<E> expression) {
        return new GivenElement<E>(elem).when(expression);
    }

    public ThenExpectingElement<E> then(final Expecting<?> exp) {
        return new ThenExpectingElement<>(exp);
    }

    public ThenElement<E> then() {
        return new ThenElement<E>();
    }
}
