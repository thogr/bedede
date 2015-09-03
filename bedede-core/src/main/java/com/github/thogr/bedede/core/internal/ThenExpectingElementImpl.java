package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ThenElement;
import com.github.thogr.bedede.ThenExpectingElement;
import com.github.thogr.bedede.conditions.Expecting;

public final class ThenExpectingElementImpl<E> implements ThenExpectingElement<E> {

    ThenExpectingElementImpl(final Expecting<?> exp) {
        GivenElementImpl.<E>given(exp);
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
