package com.github.thogr.bedede.core.internal;

import java.util.function.Function;

import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.AnObject;
import com.github.thogr.bedede.core.Behavior;
import com.github.thogr.bedede.core.BehaviorExpression;
import com.github.thogr.bedede.core.Given;
import com.github.thogr.bedede.core.SecondGiven;
import com.github.thogr.bedede.core.With;


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
        return performAction(action);
    }

    With<T> performAction(final ActionExpression<? super T> action) {
        action.perform(getFocusedObject());
        return new GivenBehaviorExpressionImpl<T>(getFocusedObject());
    }

    @Override
    public <S> Given<T> and(final Behavior<S> expr) {
        return given(expr);
    }

    @Override
    public <T2> SecondGiven<T, T, T2> given(final AnObject<T2> other) {
        return new SecondBehaviorExpressionImpl<T, T, T2>(
                getFocusedObject(), getFocusedObject(), Wrapped.getWrapped(other));
    }

    @Override
    public <T2> SecondGiven<T, T, T2> and(final AnObject<T2> other) {
        return given(other);
    }

    @Override
    public <S> Given<T> given(final Behavior<S> expr) {
        return new GivenBehaviorExpressionImpl<>(getFocusedObject());
    }

    @Override
    public <T2> SecondGiven<T2, T, T2> given(final Function<T, T2> expr) {
        final T2 other = expr.apply(getFocusedObject());
        return new SecondBehaviorExpressionImpl<T2, T, T2>(
                other, getFocusedObject(), other);
    }

    @Override
    public <T2> SecondGiven<T2, T, T2> and(final Function<T, T2> expr) {
        return given(expr);
    }
}
