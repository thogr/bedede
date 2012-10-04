package com.github.thogr.bedede.driver;


public interface StateFactory {
	<T> T createState(Class<T> state);
}
