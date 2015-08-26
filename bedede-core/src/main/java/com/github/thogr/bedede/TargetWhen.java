package com.github.thogr.bedede;

public interface TargetWhen<T, S> {

    ThenExpecting<T> then(Class<T> target);

}
