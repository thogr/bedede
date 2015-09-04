package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.conditions.ConditionVerifier;

public abstract class Verifiable<T> {

    abstract Object verify(ConditionVerifier<T> verifier);

    abstract Class<T> getConditionClass();

}
