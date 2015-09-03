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

    GivenBehaviorExpressionImpl(final BehaviorExpression<T> expr) {
        super(expr);
    }

    GivenBehaviorExpressionImpl(final Behavior<T> expr) {
        super(expr);
    }

    @Override
    public final With<T> with(final ActionExpression<? super T> action) {
        action.perform(getFocusedObject());
        return new GivenBehaviorExpressionImpl<T>(getFocusedObject());
    }

    @Override
    public <S> Given<T> and(final Behavior<S> expr) {
        return given(expr);
    }

    @Override
    public <T2> SecondGiven<T, T2> given(final AnObject<T2> other) {
        return new SecondBehaviorExpressionImpl<T, T2>(
                getFocusedObject(), Wrapped.getWrapped(other));
    }

    @Override
    public <T2> SecondGiven<T, T2> and(final AnObject<T2> other) {
        return given(other);
    }

    @Override
    public <S> Given<T> given(final Behavior<S> expr) {
        return new GivenBehaviorExpressionImpl<>(getFocusedObject());
    }
}
