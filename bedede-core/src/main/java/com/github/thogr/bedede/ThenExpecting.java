package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public interface ThenExpecting<T> {

    <V> ThenExpecting<T> then(ExpectingExpression<T, V> epression);

}
