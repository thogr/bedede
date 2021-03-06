package com.github.thogr.bedede.state;

import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.Expecting;


public abstract class AbstractState<T> {
    @OnEntry
    protected abstract Expecting<T> onEntry();
}
