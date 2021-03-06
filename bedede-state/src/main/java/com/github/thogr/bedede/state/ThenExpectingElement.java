package com.github.thogr.bedede.state;

import com.github.thogr.bedede.conditions.Expecting;

public interface ThenExpectingElement<E> {

    ThenExpectingElement<E> then(Expecting<?> exp);

    ThenElement<E> then();
}
