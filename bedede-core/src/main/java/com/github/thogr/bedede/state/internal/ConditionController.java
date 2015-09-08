package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.core.internal.Internal;


public interface ConditionController {

    @Internal
    <T> Object verify(Verifiable<T> condition);

}
