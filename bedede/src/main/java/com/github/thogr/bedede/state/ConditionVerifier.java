package com.github.thogr.bedede.state;

public interface ConditionVerifier <T> {
	void verify(T condition, Otherwise otherwise);
}
