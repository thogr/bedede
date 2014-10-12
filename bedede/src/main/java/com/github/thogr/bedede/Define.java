package com.github.thogr.bedede;

public class Define<S> {

    private final Class<S> target;

    Define(final Class<S> state) {
        this.target = state;
    }

    public Entry<S> entry(final EntryDefinition<S> definition) {
        return new Entry<S>(target) {
            final Entry<S> self = this;
            @Override
            protected void perform() {
                definition.perform(new EntryAccess<S>() {

                    @Override
                    public <T> TargetAssuming<S, T> given(final Class<T> state) {
                        return self.given(state);
                    }

                    @Override
                    public <T> TargetAssuming<S, T> given(final Entry<T> entry) {
                        return self.given(entry);
                    }

                    @Override
                    public <T> TargetAssuming<S, T> assuming(final Class<T> state) {
                        return self.assuming(state);
                    }

                    @Override
                    public void then(final Class<S> state) {
                        self.then(state);
                    }
                });
            }
        };
    }
}
