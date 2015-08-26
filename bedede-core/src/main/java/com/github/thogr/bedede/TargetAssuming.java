package com.github.thogr.bedede;

public interface TargetAssuming<T, S> {

    TargetWhen<T, S> when(ActionExpression<S> action);
}
