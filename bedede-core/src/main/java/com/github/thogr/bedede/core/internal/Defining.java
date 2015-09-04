package com.github.thogr.bedede.core.internal;

import java.util.ArrayList;
import java.util.List;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.Entry;

public final class Defining<T> {

    private final Class<T> target;

    private Defining(final Class<T> state) {
        this.target = state;
    }

    // CHECKSTYLE:OFF MethodLength
    private DefiningEntryAs<T> entryAs() {
        return new DefiningEntryAs<T>() {

            @Override
            public <S> DefiningAssuming<T, S> given(final Class<S> state) {
                return new DefiningAssuming<T, S>() {
                    private final List<ActionExpression<S>> actions = new ArrayList<>();
                    private final Entry<T> entry = new Entry<T>(target) {

                        @Override
                        protected void perform() {
                            given(state);
                            for (final ActionExpression<S> actionExpression : actions) {
                                assuming(state).when(actionExpression);
                            }
                            then(target);
                        }
                    };

                    private final DefiningWhen<T, S> self = new DefiningWhen<T, S>() {
                        @Override
                        public Entry<T> then(final Class<T> targetState) {
                            return entry;
                        }

                        @Override
                        public DefiningWhen<T, S> when(final ActionExpression<S> action) {
                            actions.add(action);
                            return self;
                        }
                    };

                    @Override
                    public DefiningWhen<T, S> when(final ActionExpression<S> action) {
                        return self.when(action);
                    }
                };
            }
        };
    }
    // CHECKSTYLE:ON

    static EntryBuilder building() {
        return new EntryBuilder();
    }

    private static <T> DefiningEntryAs<T> entryAs(final Class<T> target) {
        return new Defining<T>(target).entryAs();
    }

    static final class EntryBuilder {
        private EntryBuilder() {}

        public <T> DefiningEntry<T> entry(final Class<T> target) {

            return new DefiningEntry<T>() {
                @Override
                public DefiningEntryAs<T> as() {
                    return entryAs(target);
                }
            };
        }
    }

    @Internal
    public interface DefiningEntry<T> {
        @Internal
        DefiningEntryAs<T> as();
    }

    @Internal
    public interface DefiningEntryAs<T> {
        @Internal
        <S> DefiningAssuming<T, S> given(final Class<S> state);
    }

    @Internal
    public interface DefiningAssuming<T, S> {
        @Internal
        DefiningWhen<T, S> when(final ActionExpression<S> action);
    }

    @Internal
    public interface DefiningWhen<T, S> {
        @Internal
        DefiningWhen<T, S> when(final ActionExpression<S> action);

        @Internal
        Entry<T> then(Class<T> target);
    }
}
