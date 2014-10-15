package com.github.thogr.bedede;

import java.util.ArrayList;
import java.util.List;


public class Defining<T> {

    private final Class<T> target;

    private Defining(final Class<T> state) {
        this.target = state;
    }

    DefiningEntry<T> entry() {
        return new DefiningEntry<T>() {

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

    private static <T> DefiningEntry<T> entryAs(final Class<T> target) {
        final Defining<T> defining = new Defining<T>(target);
        return defining.entry();
    }

    static EntryBuilder builder() {
        return new EntryBuilder() {

            @Override
            public <T> DefiningGiven<T> given(final Class<T> target) {

                return new DefiningGiven<T>() {
                    @Override
                    public DefiningEntry<T> as() {
                        return entryAs(target);
                    }
                };
            }
        };
    }

    public interface EntryBuilder {
        <T> DefiningGiven<T> given(Class<T> target);
    }

    public interface DefiningGiven<T> {
        public DefiningEntry<T> as();
    }

    public interface DefiningEntry<T> {
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
