package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.Expecting;

public interface ThenElement<S> {

	GivenElement<S> given(Expecting<?> exp);

	void done();

}