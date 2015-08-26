package com.github.thogr.bedede;

import com.github.thogr.bedede.core.internal.AbstractInternalEntry;


/**
 * The behavior needed reach a target state.
 * The method {@link #perform()} is the implementation.
 * Typically the first step is to refer to some other Entry,
 * by calling {@link #given(Entry)},
 * and then some actions, and finally a call to {@link #then(Class)}
 *
 * <br><b>Example:</b>
 * <pre>
 * public class TheState {
 *      &#64;DefaultEntry
 *      public static final Entry&lt;TheState&gt; REACHED = new Entry&lt;TheState&gt;() {
 *
 *          &#64;Override
 *          protected void perform() {
 *              given(SomeState.class)
 *              .when(it -&gt; it.doesSomeThing())
 *              .then(TheState.class);
 *          }
 *      };
 * }
 * </pre>
 * @param <S> the target state
 */
public abstract class Entry<S> extends AbstractInternalEntry<S> {

    public Entry(final Class<S> target) {
        super(target);
    }

    public Entry() {
        super();
    }

}
