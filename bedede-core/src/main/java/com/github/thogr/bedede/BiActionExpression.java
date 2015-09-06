package com.github.thogr.bedede;

@FunctionalInterface
public interface BiActionExpression<T1, T2> {

    /**
     * Performs the action on two objects in focus
     * @param first the first object in focus
     * @param second the second object in focus
     */
    void perform(T1 first, T2 second);
}
