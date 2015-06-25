package com.github.thogr.bedede;

final class BasicGivenBehaviorExpression<T> extends GivenBehaviorExpression<T> {

    BasicGivenBehaviorExpression(final T obj) {
        super(obj);
    }

    public BasicGivenBehaviorExpression(BehaviorExpression<T> expr) {
        super(expr);
    }

}
