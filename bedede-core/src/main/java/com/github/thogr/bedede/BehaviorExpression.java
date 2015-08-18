package com.github.thogr.bedede;

/**
 * A BDD expression, e.g. given(...), or given(...).when(...), or given(...).when(...).then(...)
 *
 * @param <T> the type of object the actions operate on in the when() or then() expressions.
 */
public interface BehaviorExpression<T> extends Behavior<T>, WhenBehavior<T> {

}
