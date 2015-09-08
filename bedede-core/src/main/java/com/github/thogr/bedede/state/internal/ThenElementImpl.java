package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.state.GivenElement;
import com.github.thogr.bedede.state.ThenElement;

public final class ThenElementImpl<S> implements ThenElement<S> {

    ThenElementImpl() {

    }

    @Override
    public GivenElement<S> given(final Expecting<?> exp) {
        return GivenElementImpl.<S>given(exp);
    }

    @Override
    public void done() {

    }
}
