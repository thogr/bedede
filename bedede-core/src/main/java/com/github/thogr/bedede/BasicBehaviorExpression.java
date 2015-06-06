package com.github.thogr.bedede;

/**
 * A BDD expression, e.g. given(...), or given(...).when(...), or given(...).when(...).then(...)
 *
 * @param <T> the type of object the actions operate on in the when() or then() expressions.
 */
final class BasicBehaviorExpression<T> extends BehaviorExpression<T> {

    BasicBehaviorExpression(final T obj) {
        super(obj);
    }
}
