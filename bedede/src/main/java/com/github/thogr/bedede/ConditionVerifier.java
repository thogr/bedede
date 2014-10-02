package com.github.thogr.bedede;

public interface ConditionVerifier <T> {
    void verify(T condition, Otherwise otherwise);
}
