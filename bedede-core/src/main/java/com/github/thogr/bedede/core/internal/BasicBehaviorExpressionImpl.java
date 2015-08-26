package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.Behavior;


final class BasicBehaviorExpressionImpl<T> extends BehaviorExpressionImpl<T> {

    BasicBehaviorExpressionImpl(final T obj) {
        super(obj);
    }

    public BasicBehaviorExpressionImpl(Behavior<T> behavior) {
        super(behavior);
    }
}
