package com.github.thogr.bedede;

public class Define<S> {

    private final Class<S> state;

    Define(final Class<S> state) {
        this.state = state;
    }

    public Entry<S> entry(final EntryDefinition<S> definition) {
        return new Entry<S>(state) {
            final Entry<S> self = this;
            @Override
            protected void perform() {
                definition.perform(new EntryAccess<S>() {

                    @Override
                    public <T> Assuming<T> given(final Class<T> state) {
                        return self.given(state);
                    }

                    @Override
                    public <T> Assuming<T> given(final Entry<T> entry) {
                        return self.given(entry);
                    }

                    @Override
                    public <T> Assuming<T> assuming(final Class<T> state) {
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
