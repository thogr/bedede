package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.GivenElement;
import com.github.thogr.bedede.ThenElement;
import com.github.thogr.bedede.conditions.Expecting;

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
