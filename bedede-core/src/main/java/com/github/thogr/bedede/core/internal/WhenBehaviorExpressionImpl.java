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

    private AbstractPerformer<T> expr;

    WhenBehaviorExpressionImpl(T obj, AbstractPerformer<T> expr) {
        super(obj);
        this.expr = expr;
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.WhenBehaviorExpression#times(int)
     */
    @Override
    public Times<T> times(int n) {
        for (int i = 1; i < n; i++) {
            expr.perform(obj);
        }
        return new BasicBehaviorExpressionImpl<T>(obj);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.WhenBehaviorExpression#twice()
     */
    @Override
    public Times<T> twice() {
        return times(2);
    }

}
