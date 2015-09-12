package com.github.thogr.bedede.state;

import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.ActionExpression;

public interface GivenElement<T> {

    WhenElement<T> when(ActionExpression<T> expression);

    ThenExpectingElement<T> then(Expecting<?> exp);
}
