package com.github.thogr.bedede;

import com.github.thogr.bedede.annotations.OnEntry;


public abstract class AbstractState {
    @OnEntry
    protected abstract void onEntry();
}
