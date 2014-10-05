package com.github.thogr.bedede.conditions;

import java.util.function.Function;


public interface ConditionExpression<S, V> extends Function<S, Expecting<V>> {
}
