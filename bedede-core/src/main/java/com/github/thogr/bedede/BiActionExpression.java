package com.github.thogr.bedede;

@FunctionalInterface
public interface BiActionExpression<T1, T2> {
    void perform(T1 first, T2 second);
}
