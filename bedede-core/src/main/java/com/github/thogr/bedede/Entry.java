package com.github.thogr.bedede;
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
public abstract class Entry<S> extends StateBehavior<S> {

    private final Class<S> target;

    Entry(final Class<S> target) {
        this.target = target;
    }

    public Entry() {
        this.target = TypeArguments.typeArgument(this);
    }

    Class<S> getTarget() {
        return target;
    }

    protected final <T> TargetAssuming<S, T> given(final Class<T> state) {
        return new TargetAssuming<S, T>(target, getController().given(state));
    }

    protected final <T> TargetAssuming<S, T> given(final Entry<T> entry) {
        return new TargetAssuming<S, T>(target, getController().given(entry));
    }

    protected final <T> TargetAssuming<S, T> assuming(final Class<T> state) {
        return new TargetAssuming<S, T>(target, getController().assuming(state));
    }

    protected final void then(final Class<S> state) {
        getController().then(state);
    }

}
