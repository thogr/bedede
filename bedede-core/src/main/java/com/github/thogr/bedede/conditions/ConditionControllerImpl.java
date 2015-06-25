package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.Framework;

public class ConditionControllerImpl implements ConditionController {

    private final Framework framework;

    public ConditionControllerImpl(final Framework framework) {
        this.framework = framework;
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.conditions.ConditionController#verify(com.github.thogr.bedede.conditions.Expecting)
     */
    @Override
    public final <T> Object verify(final Expecting<T> condition) {
        return condition.verify(framework.getVerifier(condition.getConditionClass()));
    }
}
