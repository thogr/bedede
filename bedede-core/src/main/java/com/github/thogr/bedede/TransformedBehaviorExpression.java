package com.github.thogr.bedede;


public interface TransformedBehaviorExpression<T, S> extends
    WhenBehavior<S>, TransformedBehavior<T, S> {
}
