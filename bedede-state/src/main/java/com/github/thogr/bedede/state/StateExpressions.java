package com.github.thogr.bedede.state;

import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.conditions.Otherwise;
import com.github.thogr.bedede.core.internal.Proxy;
import com.github.thogr.bedede.state.internal.Defining.DefiningEntry;
import com.github.thogr.bedede.state.internal.StateExpressionsImpl;

public class StateExpressions {

    protected StateExpressions() {

    }

    private static StateExpressionsImpl impl = new StateExpressionsImpl(new StateExpressions());

    /**
     * Creates an "expecting" using a boolean condition. Typically in an &#64;OnEntry method.
     * <p>Example:<p>
     * <pre>
     *   &#64;OnEntry
     *   public Expecting<BooleanCondition> shouldBeLocked() {
     *       return expecting(door.isLocked(), otherwise("Unexpected unlocked door"));
     *   }
     * </pre>
     * @param condition the boolean condition
     * @param otherwise description of otherwise
     * @return the expecting
     */
    public static Expecting<BooleanCondition> expecting(
            final Boolean condition, final Otherwise otherwise) {
        return impl.expecting(condition, otherwise);
    }

    /**
     * Start defining an entry.
     * @param state the state class, where the entry should be
     * @param <T> the type of the state class
     * @return the start (to be continued) defining the entry
     */
    public static <T> DefiningEntry<T> entry(final Class<T> state) {
        return impl.entry(state);
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
     * @param <T> the type of state
     * @param state starting point for the coming actions
     * @return an Assuming which has methods to further actions.
     */
    public static <T> Assuming<T> given(final Class<T> state) {
        return impl.given(state);
    }

    /**
     * Sets the starting point for the further actions. If the state is
     * not the current assumed state it will perform the actions needed to get
     * there, as specified by the entry.
     * @param entry the entry that should be performed
     * @param <T> the type of state
     * @return an Assuming which has methods to further actions
     */
    public static <T> Assuming<T> given(final Entry<T> entry) {
        return impl.given(entry);
    }

    /**
     * @see com.github.thogr.bedede.conditions.#otherwise(String)
     */
    @Proxy
    public static Otherwise otherwise(final String message) {
        return Otherwise.otherwise(message);
    }
}
