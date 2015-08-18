package com.github.thogr.bedede;

abstract class BehaviorExpressionImpl<T>
    extends BehaviorImpl<T> implements BehaviorExpression<T> {

    BehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    public BehaviorExpressionImpl(Behavior<T> behavior) {
        super(behavior);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.BehaviorExpression#when(com.github.thogr.bedede.TransformingExpression)
     */
    @Override
    public final <S> TransformedBehaviorExpression<T, S> when(final TransformingExpression<? super T, ? extends S> expr) {
        S result = expr.function.apply(obj);
        return new TransformedBehaviorExpressionImpl<T, S>(obj, result);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.BehaviorExpression#when(com.github.thogr.bedede.PerformingExpression)
     */
    @Override
    public final WhenBehaviorExpression<T> when(final PerformingExpression<? super T> expr) {
        expr.perform(obj);
        return new WhenBehaviorExpressionImpl<T>(obj, expr);
    }
}
