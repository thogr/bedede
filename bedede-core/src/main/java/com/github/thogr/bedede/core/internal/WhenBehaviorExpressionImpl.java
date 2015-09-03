package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.Times;
import com.github.thogr.bedede.WhenPerforming;

/**
 * The result of a non-transforming when() expression.
 *
 * @param <T> the type of the current object
 */
final class WhenBehaviorExpressionImpl<T>
    extends BehaviorExpressionImpl<T> implements WhenPerforming<T> {

    private final AbstractPerformer<T> expr;

    WhenBehaviorExpressionImpl(final T obj, final AbstractPerformer<T> expr) {
        super(obj);
        this.expr = expr;
    }

    @Override
    public Times<T> times(final int n) {
        for (int i = 1; i < n; i++) {
            expr.perform(getFocusedObject());
        }
        return new BasicBehaviorExpressionImpl<T>(getFocusedObject());
    }

    @Override
    public Times<T> twice() {
        return times(2);
    }
}
