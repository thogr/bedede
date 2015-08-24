package com.github.thogr.bedede;

import static org.junit.Assert.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.mocks.Mocked;

class TransformedBehaviorExpressionImpl<T, S> implements TransformedBehaviorExpression<T, S> {

    private T obj;
    private S result;
    private BehaviorExpression<S> impl;

    TransformedBehaviorExpressionImpl(T obj, S result) {
        this.obj = obj;
        this.result = result;
        impl = new BasicBehaviorExpressionImpl<>(result);
    }

    @Override
    public TransformedBehavior<T, S> then(
            BiFunction<? super T, ? super S, Matcher<? super S>> expr) {
        Matcher<? super S> is = expr.apply(obj, result);
        assertThat(result, is);
        return this;
    }

    @Override
    public <S2> TransformedBehaviorExpression<S, S2> when(
            TransformingExpression<? super S, ? extends S2> expr) {
        return impl.when(expr);
    }

    @Override
    public WhenBehaviorExpression<S> when(PerformingExpression<? super S> expr) {
        return impl.when(expr);
    }

    @Override
    public <S2> Behavior<S> then(Function<? super S, ? extends S2> it,
            Matcher<? super S2> is) {
        return impl.then(it, is);
    }

    @Override
    public Behavior<S> then(Predicate<? super S> predicate) {
        return impl.then(predicate);
    }

    @Override
    public <V> V then(Mocked<V> mocked) {
        return Expressions.then(mocked);
    }

    @Override
    public Behavior<Boolean> then(boolean expr) {
        return Expressions.then(expr);
    }

    @Override
    public Behavior<S> then(Behavior<S> behavior) {
        return impl.then(behavior);
    }
}
