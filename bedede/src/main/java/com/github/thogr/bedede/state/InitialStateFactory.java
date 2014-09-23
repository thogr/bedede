package com.github.thogr.bedede.state;


public interface InitialStateFactory {

	<T> T createStartState(Class<T> stateClass, Parameter... params);

}
