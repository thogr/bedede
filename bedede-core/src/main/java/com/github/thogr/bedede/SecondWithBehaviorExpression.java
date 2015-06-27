package com.github.thogr.bedede;

public interface SecondWithBehaviorExpression<T1, T2> {
    ContinuedBehaviorExpression<T1, T2> with(ActionExpression<? super T2> action);

}
