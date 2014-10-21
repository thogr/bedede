package com.github.thogr.bedede;

import static com.github.thogr.bedede.Framework.createConditionController;

import com.github.thogr.bedede.conditions.Expecting;
public final class GivenElement<T> {

    private final T elem;

    GivenElement(final T elem) {
        this.elem = elem;
    }

    public WhenElement<T> when(final ActionExpression<T> expression) {
        expression.perform(elem);
        return new WhenElement<T>(elem);
    }

    static <E> GivenElement<E> given(
            final Expecting<?> expecting) {
        @SuppressWarnings("unchecked")
        final E elem = (E) createConditionController().verify(expecting);
        return new GivenElement<E>(elem);
    }

    public ThenExpectingElement<T> then(final Expecting<?> exp) {
        return new ThenExpectingElement<>(exp);
    }
}
