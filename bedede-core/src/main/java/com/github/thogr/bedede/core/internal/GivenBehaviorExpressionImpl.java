package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.AnObject;
import com.github.thogr.bedede.Behavior;
import com.github.thogr.bedede.BehaviorExpression;
import com.github.thogr.bedede.Given;
import com.github.thogr.bedede.SecondGiven;
import com.github.thogr.bedede.With;


class GivenBehaviorExpressionImpl<T>
    extends BehaviorExpressionImpl<T> implements Given<T>, With<T> {

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
    public final With<T> with(final ActionExpression<? super T> action) {
        action.perform(obj);
        return new GivenBehaviorExpressionImpl<T>(obj);
    }

    @Override
    public <S> Given<T> and(Behavior<S> expr) {
        return given(expr);
    }

    @Override
    public <T2> SecondGiven<T, T2> given(AnObject<T2> other) {
        return new SecondBehaviorExpressionImpl<T, T2>(obj, Wrapped.getWrapped(other));
    }

    @Override
    public <T2> SecondGiven<T, T2> and(AnObject<T2> other) {
        return given(other);
    }

    @Override
    public <S> Given<T> given(Behavior<S> expr) {
        return new GivenBehaviorExpressionImpl<>(obj);
    }
}
