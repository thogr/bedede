package com.github.thogr.bedede.core.internal;

interface StateMachine {

    <T> T next(final Class<T> state);

    <T> boolean was(final Class<T> state);

    <T> T go(final Class<T> state);
}
