package com.github.thogr.bedede;


public interface SecondBehaviorExpression<T1, T2>
extends ContinuedBehaviorExpression<T1, T2>,
        SecondWithBehaviorExpression<T1, T2> {
    ContinuedBehaviorExpression<T1, T2> with(BiActionExpression<? super T1, ? super T2> action);

}
