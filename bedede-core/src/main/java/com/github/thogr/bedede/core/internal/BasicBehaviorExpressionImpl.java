package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.core.Behavior;


final class BasicBehaviorExpressionImpl<T> extends BehaviorExpressionImpl<T> {

    BasicBehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    public BasicBehaviorExpressionImpl(final Behavior<T> behavior) {
        super(behavior);
    }
}
