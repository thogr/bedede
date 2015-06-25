package com.github.thogr.bedede;

class GivenBehaviorExpressionImpl<T>
    extends BehaviorExpressionImpl<T> implements GivenBehaviorExpression<T> {

    GivenBehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    GivenBehaviorExpressionImpl(BehaviorExpression<T> expr) {
        super(expr);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenBehaviorExpression#given(T2)
     */
    @Override
    public final <T2> SecondBehaviorExpression<T, T2> given(final T2 other)  {
        return new SecondBehaviorExpressionImpl<T, T2>(obj, other);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenBehaviorExpression#and(T2)
     */
    @Override
    public final <T2> SecondBehaviorExpression<T, T2> and(final T2 other)  {
        return given(other);
    }

}
