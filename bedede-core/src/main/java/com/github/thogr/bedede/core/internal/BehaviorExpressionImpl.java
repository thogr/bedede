package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.Behavior;
import com.github.thogr.bedede.Performing;
import com.github.thogr.bedede.Times;
import com.github.thogr.bedede.Transforming;
import com.github.thogr.bedede.When;
import com.github.thogr.bedede.WhenPerforming;
import com.github.thogr.bedede.WhenTransforming;

abstract class BehaviorExpressionImpl<T>
    extends BehaviorImpl<T> implements When<T>, Times<T> {

    BehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    public BehaviorExpressionImpl(Behavior<T> behavior) {
        super(behavior);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.BehaviorExpression#when(com.github.thogr.bedede.Transforming)
     */
    @Override
    public final <S> WhenTransforming<T, S> when(final Transforming<? super T, ? extends S> expr) {
        return whenTransforming(expr);
    }

    private <S> WhenTransforming<T, S> whenTransforming(
            final AbstractTransformer<? super T, ? extends S> expr) {
        S result = expr.getFunction().apply(obj);
        return new TransformedBehaviorExpressionImpl<T, S>(obj, result);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.BehaviorExpression#when(com.github.thogr.bedede.PerformingExpression)
     */
    @Override
    public final WhenPerforming<T> when(final Performing<T> expr) {
        return whenPerforming(expr);
    }

    private WhenPerforming<T> whenPerforming(final AbstractPerformer<T> expr) {
        expr.perform(obj);
        return new WhenBehaviorExpressionImpl<T>(obj, expr);
    }
}
