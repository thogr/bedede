package com.github.thogr.bedede.state;

import com.github.thogr.bedede.state.internal.AbstractInternalEntry;


/**
 * The behavior needed reach a target state.
 * The method perform() is the implementation.
 * Typically the first step is to refer to some other Entry,
 * by calling given(Entry),
 * and then some actions, and finally a call to then(Class)
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

    protected Entry(final Class<S> target) {
        super(target);
    }

    protected Entry() {
        super();
    }

}
