package com.github.thogr.bedede;

public class Defining<T> {

    private final Class<T> target;

    Defining(final Class<T> state) {
        this.target = state;
    }

    public Entry<T> entry(final EntryDefinition<T> definition) {
        return new Entry<T>(target) {
            final Entry<T> self = this;
            @Override
            protected void perform() {
                definition.perform(new EntryAccess<T>() {

                    @Override
                    public <S> TargetAssuming<T, S> given(final Class<S> state) {
                        return self.given(state);
                    }

                    @Override
                    public <S> TargetAssuming<T, S> given(final Entry<S> entry) {
                        return self.given(entry);
                    }

                    @Override
                    public <S> TargetAssuming<T, S> assuming(final Class<S> state) {
                        return self.assuming(state);
                    }

                    @Override
                    public void then(final Class<T> state) {
                        self.then(state);
                    }
                });
            }
        };
    }

    public DefiningEntry<T> entry() {
        return new DefiningEntry<T>() {

            @Override
            public <S> DefiningAssuming<T, S> given(final Class<S> state) {
                return new DefiningAssuming<T, S>() {

                    @Override
                    public DefiningWhen<T, S> when(final ActionExpression<S> action) {
                        return new DefiningWhen<T, S>() {

                            @Override
                            public Entry<T> then(final Class<T> target) {
                                return new Entry<T>() {

                                    @Override
                                    protected void perform() {
                                        given(state)
                                        .when(action)
                                        .then(target);
                                    }
                                };
                            }
                        };
                    }
                };
            }
        };
    }

    public interface DefiningEntry<T> {
        <S> DefiningAssuming<T, S> given(final Class<S> state);
    }

    public interface DefiningAssuming<T, S> {
        DefiningWhen<T, S> when(final ActionExpression<S> action);
    }

    public interface DefiningWhen<T, S> {
        Entry<T> then(Class<T> target);
    }
}
