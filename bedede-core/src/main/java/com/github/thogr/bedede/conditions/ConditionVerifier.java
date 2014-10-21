package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.Otherwise;

public interface ConditionVerifier <T> {
    Object verify(T condition, Otherwise otherwise);
}
