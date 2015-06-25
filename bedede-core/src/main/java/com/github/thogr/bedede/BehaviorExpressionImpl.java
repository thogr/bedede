package com.github.thogr.bedede;

abstract class BehaviorExpressionImpl<T>
    extends BehaviorImpl<T> implements BehaviorExpression<T> {

    BehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    BehaviorExpressionImpl(BehaviorExpression<T> expr) {
        super(expr);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.BehaviorExpression#when(com.github.thogr.bedede.TransformingExpression)
     */
    @Override
    public final <S> BehaviorExpression<S> when(final TransformingExpression<? super T, ? extends S> expr) {
        S result = expr.function.apply(obj);
        return new BasicBehaviorExpressionImpl<S>(result);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.BehaviorExpression#when(com.github.thogr.bedede.PerformingExpression)
     */
    @Override
    public final WhenBehaviorExpression<T> when(final PerformingExpression<? super T> expr) {
        expr.perform(obj);
        return new WhenBehaviorExpressionImpl<T>(obj, expr);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.BehaviorExpression#when(com.github.thogr.bedede.ActionExpression)
     */
    @Override
    public final WhenBehaviorExpression<T> when(final ActionExpression<? super T> action) {
        action.perform(obj);
        return new WhenBehaviorExpressionImpl<T>(obj, Expressions.performing(action));
    }

}
