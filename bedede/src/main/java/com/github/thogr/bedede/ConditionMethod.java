package com.github.thogr.bedede;

public interface ConditionMethod<S, V> {
    Condition<V> condition(S state);
}
