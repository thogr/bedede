package com.github.thogr.bedede.core;

import com.github.thogr.bedede.core.internal.Internal;
import com.github.thogr.bedede.core.internal.WrappedPerformable;

public final class AnObject<T> extends WrappedPerformable<T> {

    AnObject(final T object) {
        super(object);
    }

    @Internal
    public static <T> AnObject<T> a(final T object) {
        return new AnObject<T>(object);
    }

    /**
     * When performing an action on the current object. Intended to be more for initializing
     * the object than actually performing the actual main action of the test.
     * The expression given(an(object)).with(action) though, will behave exactly the same as
     * given(an(object)).when(performing(action)).
     *
     * <p>
     * Example:
     * </p>
     * <pre>
     *   given(a(new Person()).with(it -&gt; {
     *       it.setFirstName("John");
     *   }), (person -&gt;
     *   given(a("Smith"))
     *   .when(performing(it -&gt; person.setFamilyName(it)))
     *   .when(retrieving(the -&gt; person.getFullName()))
     *   .then(it(), is("John Smith"))));
     * </pre>
     * @param action the action to be performed
     * @return the current object
     */
    public AnObject<T> with(final ActionExpression<? super T> action) {
        perform(action);
        return this;
    }
}
