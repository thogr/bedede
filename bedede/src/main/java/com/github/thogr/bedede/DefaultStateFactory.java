package com.github.thogr.bedede;

public class DefaultStateFactory implements StateFactory {

    @Override
    public <T> T createState(final Class<T> state) {
        try {
            return state.newInstance();
        } catch (final InstantiationException e) {
            throw new IllegalStateException(e);
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

}
