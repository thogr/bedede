package com.github.thogr.bedede.core;

public interface SecondGiven<T1, T2>
extends SecondWith<T1, T2>,
        SecondWithBehaviorExpression<T1, T2> {

    /**
     * Similar to {@link com.github.thogr.bedede.core.WithBehaviorExpression#with(ActionExpression)}
     * but, action takes two parameters, since it will operate on the two objects in focus.
     * @param action the action to be performed
     * @return the continued behavior specification
     */
    SecondWith<T1, T2> with(BiActionExpression<? super T1, ? super T2> action);

}
