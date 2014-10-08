package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.Expecting;

public class ThenExpectingElement<E> {

    ThenExpectingElement(final Expecting<?> exp) {
        GivenElement.<E>given(exp);
    }

    public ThenExpectingElement<E> then(final Expecting<?> exp) {
        return new ThenExpectingElement<>(exp);
    }

    public ThenElement<E> then() {
        return new ThenElement<E>();
    }
}
