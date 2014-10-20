package com.github.thogr.bedede;

import java.util.ArrayList;
import java.util.List;


public class Defining<T> {

    private final Class<T> target;

    private Defining(final Class<T> state) {
        this.target = state;
    }

    private DefiningEntryAs<T> entryAs() {
        return new DefiningEntryAs<T>() {

            @Override
            public <S> DefiningAssuming<T, S> given(final Class<S> state) {
                return new DefiningAssuming<T, S>() {
                    List<ActionExpression<S>> actions = new ArrayList<>();
                    final Entry<T> entry = new Entry<T>(target) {

                        @Override
                        protected void perform() {
                            given(state);
                            for (final ActionExpression<S> actionExpression : actions) {
                                assuming(state).when(actionExpression);
                            }
                            then(target);
                        }
                    };

                    DefiningWhen<T, S> self = new DefiningWhen<T, S>() {
                        @Override
                        public Entry<T> then(final Class<T> target) {
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

    static EntryBuilder building() {
        return new EntryBuilder();
    }

    private static <T> DefiningEntryAs<T> entryAs(final Class<T> target) {
        return new Defining<T>(target).entryAs();
    }

    static class EntryBuilder {
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

    public interface DefiningEntry<T> {
        public DefiningEntryAs<T> as();
    }

    public interface DefiningEntryAs<T> {
        <S> DefiningAssuming<T, S> given(final Class<S> state);
    }

    public interface DefiningAssuming<T, S> {
        DefiningWhen<T, S> when(final ActionExpression<S> action);
    }

    public interface DefiningWhen<T, S> {
        DefiningWhen<T, S> when(final ActionExpression<S> action);
        Entry<T> then(Class<T> target);
    }

}
