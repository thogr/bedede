package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.Behavior;
import com.github.thogr.bedede.Performing;
import com.github.thogr.bedede.Times;
import com.github.thogr.bedede.Transforming;
import com.github.thogr.bedede.WhenBiTransforming;
import com.github.thogr.bedede.WhenPerforming;
import com.github.thogr.bedede.WhenTransforming;

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

    private <S> WhenTransforming<T, S> whenTransforming(
            final AbstractTransformer<? super T, ? extends S> expr) {
        final S result = expr.getFunction().apply(getFocusedObject());
        return new TransformedBehaviorExpressionImpl<T, S>(getFocusedObject(), result);
    }

    @Override
    public final WhenPerforming<T> when(final Performing<T> expr) {
        return whenPerforming(expr);
    }

    private WhenPerforming<T> whenPerforming(final AbstractPerformer<T> expr) {
        expr.perform(getFocusedObject());
        return new WhenBehaviorExpressionImpl<T>(getFocusedObject(), expr);
    }
}
