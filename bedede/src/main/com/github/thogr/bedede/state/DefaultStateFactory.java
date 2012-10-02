package com.github.thogr.bedede.state;


public class DefaultStateFactory implements StateFactory {

	@Override
	public <T> T createState(Class<T> state) {
		try {
			return state.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalStateException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}