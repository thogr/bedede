package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.state.ThenElement;
import com.github.thogr.bedede.state.ThenExpectingElement;
import com.github.thogr.bedede.state.WhenElement;


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
