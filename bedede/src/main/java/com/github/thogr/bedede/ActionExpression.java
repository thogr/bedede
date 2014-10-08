package com.github.thogr.bedede;


@FunctionalInterface
public interface ActionExpression<T> {

    void perform(T it);
}
