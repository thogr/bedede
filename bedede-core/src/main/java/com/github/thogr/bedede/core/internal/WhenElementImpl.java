package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.ThenElement;
import com.github.thogr.bedede.ThenExpectingElement;
import com.github.thogr.bedede.WhenElement;
import com.github.thogr.bedede.conditions.Expecting;


public final class WhenElementImpl<E> implements WhenElement<E> {

    private final E elem;

    WhenElementImpl(final E elem) {
        this.elem = elem;
    }

    @Override
    public WhenElement<E> when(final ActionExpression<E> expression) {
        return new GivenElementImpl<E>(elem).when(expression);
    }

    @Override
    public ThenExpectingElement<E> then(final Expecting<?> exp) {
        return new ThenExpectingElementImpl<>(exp);
    }

    @Override
    public ThenElement<E> then() {
        return new ThenElementImpl<E>();
    }
}
