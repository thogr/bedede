package com.github.thogr.bedede.core;

public interface SecondWithBehaviorExpression<T1, T2> {

    /**
     * Similar to {@link com.github.thogr.bedede.core.SecondGiven#with(ActionExpression)}
     * but, action takes one parameters, since it will operate on the main object in focus, even
     * though we have two objects in focus.
     * @see com.github.thogr.bedede.core.WithBehaviorExpression#with(ActionExpression)
     * @param action the action to be performed
     * @return the continued behavior specification
     */
    SecondWith<T1, T2> with(ActionExpression<? super T2> action);

}
