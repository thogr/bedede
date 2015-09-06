package com.github.thogr.bedede.core.internal;


public interface ConditionController {

    @Internal
    <T> Object verify(Verifiable<T> condition);

}
