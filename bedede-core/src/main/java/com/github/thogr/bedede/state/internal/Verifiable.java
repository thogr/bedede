package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.ConditionVerifier;

public abstract class Verifiable<T> {

    abstract Object verify(ConditionVerifier<T> verifier);

    abstract Class<T> getConditionClass();

}
