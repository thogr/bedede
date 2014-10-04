package com.github.thogr.bedede.conditions;


public interface ConditionMethod<S, V> {
    Condition<V> condition(S state);
}
