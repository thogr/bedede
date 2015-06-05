package com.github.thogr.bedede;


public abstract class BehaviorDriven {

    private final BehaviorController controller;

    public BehaviorDriven() {
        this(Framework.createBehaviorController());
    }

    BehaviorDriven(final BehaviorController controller) {
        this.controller = controller;
    }

    /**
     * Sets the starting point for the further actions. If the state is
     * not the current assumed state it will possibly perform the actions needed to get
     * there, if the target state class has a declared default Entry.
     * The default Entry must be a public static field of type Entry&lt;T&gt; in the target
     * class, where T is the target class type.
     * If the class has more than one such field, one (and only one) must be annotated
     * with &#64;DefaultEntry.
     * @see Entry
     * @param target starting point for the coming actions
     * @return an Assuming which has methods to further actions.
     */
    protected final <T> Assuming<T> given(final Class<T> target) {
        return controller.given(target);
    }

    /**
     * Sets the starting point for the further actions. If the state is
     * not the current assumed state it will perform the actions needed to get
     * there, as specified by the entry.
     * @param entry the entry that should be performed
     * @return an Assuming which has methods to further actions
     */
    protected final <T> Assuming<T> given(final Entry<T> entry) {
        return controller.given(entry);
    }

    /**
     * Sets the starting environment for a state-less test, for a more traditional unit test
     * but with behavior driven syntax - given().when()...then();
     * The starting environment is any object, which further when() and then() expressions will
     * operate on.
     * @param obj initial value
     * @return the continued behavior expression
     */
    public <T> BehaviorExpression<T> given(final T obj) {
        return Expressions.given(obj);
    }

    /**
     * Sets the starting environment for a state-less test, for a more traditional unit test
     * but with behavior driven syntax - given().when()...then();
     * In this case the starting environment is represented by a behavior expression, typically
     * returned by a method (perhaps extracted by a previous refactoring).
     * @param obj initial value
     * @return the continued behavior expression
     */
    public <T> BehaviorExpression<T> given(final BehaviorExpression<T> obj) {
        return Expressions.given(obj);
    }

    /**
     * Sets the starting point for the further actions. This sets the assumed current state.
     * The validity of this assumption is verified before when any action is performed,
     * but not until then, by execution the method annotated with &#64;OnEntry in the target
     * class, if any.
     * @param target starting point for the coming actions.
     * @return an Assuming which has methods to further actions.
     */
    protected final <T> Assuming<T> assuming(final Class<T> target) {
        return controller.assuming(target);
    }

    /**
     * Sets the end point for the previous actions. This sets the assumed current state.
     * The validity of this assumption is verified immediately, by execution the method
     * annotated with &#64;OnEntry in the target class, if any.
     * @param target end point for the previous actions
     * @return an Assuming which has methods to further actions.
     */
    protected final <T> Then<T> then(final Class<T> target) {
        return controller.then(target);
    }
}
