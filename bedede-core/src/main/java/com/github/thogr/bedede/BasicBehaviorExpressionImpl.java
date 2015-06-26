package com.github.thogr.bedede;


final class BasicBehaviorExpressionImpl<T> extends BehaviorExpressionImpl<T> {

    BasicBehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    public BasicBehaviorExpressionImpl(Behavior<T> behavior) {
        super(behavior);
    }
}
