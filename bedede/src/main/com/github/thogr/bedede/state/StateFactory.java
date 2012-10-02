package com.github.thogr.bedede.state;


public interface StateFactory {
	<T> T createState(Class<T> state);
}
