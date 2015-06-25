package com.github.thogr.bedede;

public abstract class GivenBehaviorExpression<T> extends BehaviorExpression<T> {

    GivenBehaviorExpression(final T obj) {
        super(obj);
    }

    GivenBehaviorExpression(BehaviorExpression<T> expr) {
        this(expr.obj);
    }

    public final <T2> SecondBehaviorExpression<T, T2> given(final T2 other)  {
        return new SecondBehaviorExpression<T, T2>(obj, other);
    }

    public final <T2> SecondBehaviorExpression<T, T2> and(final T2 other)  {
        return given(other);
    }

}
