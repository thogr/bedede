package com.github.thogr.bedede.state;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.WhenElement;
import com.github.thogr.bedede.conditions.Expecting;

public interface GivenElement<T> {

    WhenElement<T> when(ActionExpression<T> expression);

    ThenExpectingElement<T> then(Expecting<?> exp);
}
