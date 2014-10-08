package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.Expecting;

public class ThenElement<S> {

    ThenElement() {

    }

    public GivenElement<S> given(final Expecting<?> exp) {
        return GivenElement.<S>given(exp);
    }

    public void done() {

    }
}
