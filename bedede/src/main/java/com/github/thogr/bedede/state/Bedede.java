package com.github.thogr.bedede.state;

import com.github.thogr.bedede.state.internal.Expecting;

public class Bedede {
	
	public static <T> Condition<T> expecting(T condition, Otherwise otherwise) {
		return Expecting.expecting(condition, otherwise);
	}
	
    public static Otherwise otherwise(String message) {
    	return Otherwise.otherwise(message);
    }

}
