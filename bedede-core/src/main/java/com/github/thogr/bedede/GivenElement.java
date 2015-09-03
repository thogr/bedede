package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.Expecting;

public interface GivenElement<T> {

    WhenElement<T> when(ActionExpression<T> expression);

    ThenExpectingElement<T> then(Expecting<?> exp);
}
