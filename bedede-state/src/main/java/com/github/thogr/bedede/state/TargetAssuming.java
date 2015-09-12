package com.github.thogr.bedede.state;

import com.github.thogr.bedede.core.ActionExpression;

public interface TargetAssuming<T, S> {

    TargetWhen<T, S> when(ActionExpression<S> action);
}
