package com.github.thogr.bedede;

interface StateMachine {

    <T> T next(final Class<T> state);

    <T> boolean was(final Class<T> state);

    <T> T go(final Class<T> state);
}
