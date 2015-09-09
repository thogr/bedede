package com.github.thogr.bedede.state.internal;

import static com.github.thogr.bedede.state.internal.Framework.createConditionController;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.WhenElement;
import com.github.thogr.bedede.core.internal.Internal;
import com.github.thogr.bedede.state.GivenElement;
import com.github.thogr.bedede.state.ThenExpectingElement;

public final class GivenElementImpl<T> implements GivenElement<T> {

    private final T elem;

    GivenElementImpl(final T elem) {
        this.elem = elem;
    }

    @Override
    public WhenElement<T> when(final ActionExpression<T> expression) {
        expression.perform(elem);
        return new WhenElementImpl<T>(elem);
    }

    @Internal
    public static <E> GivenElement<E> given(final Expecting<?> expecting) {
        @SuppressWarnings("unchecked")
        final E elem = (E) createConditionController().verify(expecting);
        return new GivenElementImpl<E>(elem);
    }

    @Override
    public ThenExpectingElement<T> then(final Expecting<?> exp) {
        return new ThenExpectingElementImpl<>(exp);
    }
}
