package com.github.thogr.bedede.driver;


/**
 * An action that will change state
 * @author thogr
 *
 * @param <Before> current state class
 * @param <After> state class after transition
 */
public abstract class TransitionAction<Before, After> 
extends Action<Before> {
	
	protected void then(Class<After> next) {
		getDriver().then(next);
	}
}
