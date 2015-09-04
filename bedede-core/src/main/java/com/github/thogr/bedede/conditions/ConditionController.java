package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.core.internal.Verifiable;

public interface ConditionController {

    <T> Object verify(Verifiable<T> condition);

}
