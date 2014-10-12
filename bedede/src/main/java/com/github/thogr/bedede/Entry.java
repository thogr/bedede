package com.github.thogr.bedede;
/**
 * The behavior needed reach a a target state.
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
 *              .when(it -> it.doesSomeThing())
 *              .then(TheState.class);
 *          }
 *      };
 * }
 * </pre>
 * @param <S> the target state
 */
public abstract class Entry<S> extends Behavior<S> {

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

    protected final <T> Assuming<T> given(final Class<T> state) {
        return getController().given(state);
    }

    protected final <T> Assuming<T> given(final Entry<T> entry) {
        return getController().given(entry);
    }

    protected final <T> Assuming<T> assuming(final Class<T> state) {
        return getController().assuming(state);
    }

    protected final <T> void then(final Class<T> state) {
        getController().then(state);
    }

}
