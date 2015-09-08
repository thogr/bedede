package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.state.ThenElement;
import com.github.thogr.bedede.state.ThenExpectingElement;

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
