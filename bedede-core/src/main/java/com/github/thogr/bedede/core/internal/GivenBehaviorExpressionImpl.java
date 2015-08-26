package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.AnObject;
import com.github.thogr.bedede.Behavior;
import com.github.thogr.bedede.BehaviorExpression;
import com.github.thogr.bedede.GivenBehaviorExpression;
import com.github.thogr.bedede.SecondBehaviorExpression;


class GivenBehaviorExpressionImpl<T>
    extends BehaviorExpressionImpl<T> implements GivenBehaviorExpression<T> {

    GivenBehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    GivenBehaviorExpressionImpl(BehaviorExpression<T> expr) {
        super(expr);
    }

    GivenBehaviorExpressionImpl(Behavior<T> expr) {
        super(expr);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.BehaviorExpression#when(com.github.thogr.bedede.ActionExpression)
     */
    @Override
    public final GivenBehaviorExpression<T> with(final ActionExpression<? super T> action) {
        action.perform(obj);
        return new GivenBehaviorExpressionImpl<T>(obj);
    }

    @Override
    public <S> GivenBehaviorExpression<T> and(Behavior<S> expr) {
        return given(expr);
    }

    @Override
    public <T2> SecondBehaviorExpression<T, T2> given(AnObject<T2> other) {
        return new SecondBehaviorExpressionImpl<T, T2>(obj, Wrapped.getWrapped(other));
    }

    @Override
    public <T2> SecondBehaviorExpression<T, T2> and(AnObject<T2> other) {
        return given(other);
    }

    @Override
    public <S> GivenBehaviorExpression<T> given(Behavior<S> expr) {
        return new GivenBehaviorExpressionImpl<>(obj);
    }
}
