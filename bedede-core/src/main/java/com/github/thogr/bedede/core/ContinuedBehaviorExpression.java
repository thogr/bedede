package com.github.thogr.bedede.core;


public interface ContinuedBehaviorExpression<T1, T2>
    extends BehaviorExpression<T1>, ContinuedBehavior<T1, T2> {

    /**
     * When performing an action on the current two objects. The action is non-functional,
     * i.e. does not return anything, and operates on two objects.
     * Further behavior operates on the same current objects.
     * <p>
     * Example:
     * </p>
     * <pre>
     * given(firstObject).and(secondObject)
     * .when(performing((first, second) -&gt; first.operation(second)))
     * </pre>
     * @param expr an action wrapped like: <code>performing(action)</code>
     * @return a new expression where the current two objects in focus are the same
     */
    WhenBiPerforming<T1, T2> when(BiPerforming<? super T1, ? super T2> expr);

    /**
     * When transforming the current two objects into one object. The action is functional, i.e.
     * with a return value, and operates on the current two objects.
     * Further behavior operates on that returned object, which will be the
     * new current object.
     * <p>
     * Example:
     * </p>
     * <pre>
     * given(firstObject).and(secondObject)
     * .when(transforming((first, second) -&gt; first.combinedWith(second)))
     * </pre>
     * @param expr an action wrapped like: <code>performing(action)</code>
     * @param <S> the type of object returned by the action
     * @return a new expression where the object in focus is the result of the transformation
     */
    <S> WhenBiTransforming<S> when(BiTransforming<? super T1, ? super T2, ? extends S> expr);

}
