package com.github.thogr.bedede;


@FunctionalInterface
public interface ActionExpression<T> {
    /**
     * The action to be performed
     * @param it the object in focus
     */
    void perform(T it);
}
