package com.github.thogr.bedede.internal;

import com.github.thogr.bedede.Expressions;
import com.github.thogr.bedede.GivenPrefix;
import com.github.thogr.bedede.core.internal.Internal;

public class ExpressionsImpl {

    @Internal
    public ExpressionsImpl(final Expressions expr) {
        if (expr == null) {
            throw new NullPointerException();
        }
    }

    @Internal
    public GivenPrefix given() {
        return new GivenPrefixImpl();
    }
}
