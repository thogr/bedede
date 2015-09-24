package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.Behavior;
import com.github.thogr.bedede.core.Performing;
import com.github.thogr.bedede.core.Times;
import com.github.thogr.bedede.core.Transforming;
import com.github.thogr.bedede.core.WhenBiTransforming;
import com.github.thogr.bedede.core.WhenPerforming;
import com.github.thogr.bedede.core.WhenTransforming;
import com.github.thogr.bedede.core.With;

abstract class BehaviorExpressionImpl<T>
    extends BehaviorImpl<T> implements WhenBiTransforming<T>, Times<T> {

    BehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    public BehaviorExpressionImpl(final Behavior<T> behavior) {
        super(behavior);
    }

    @Override
    public final <S> WhenTransforming<T, S> when(final Transforming<? super T, ? extends S> expr) {
        return whenTransforming(expr);
    }

    <S> WhenTransforming<T, S> whenTransforming(
            final AbstractTransformer<? super T, ? extends S> expr) {
        final S result = expr.getFunction().apply(getFocusedObject());
        return new TransformedBehaviorExpressionImpl<T, S>(getFocusedObject(), result);
    }

    @Override
    public final WhenPerforming<T> when(final Performing<T> expr) {
        return whenPerforming(expr);
    }

    WhenPerforming<T> whenPerforming(final AbstractPerformer<T> expr) {
        expr.perform(getFocusedObject());
        return new WhenBehaviorExpressionImpl<T>(getFocusedObject(), expr);
    }

    With<T> performAction(final ActionExpression<? super T> action) {
        action.perform(getFocusedObject());
        return new GivenBehaviorExpressionImpl<T>(getFocusedObject());
    }
}
