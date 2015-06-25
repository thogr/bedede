package com.github.thogr.bedede.conditions;

public interface ConditionController {

    <T> Object verify(Expecting<T> condition);

}
