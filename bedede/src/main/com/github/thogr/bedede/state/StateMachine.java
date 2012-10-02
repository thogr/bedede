package com.github.thogr.bedede.state;


public class StateMachine {

	private Object current;
	private StateFactory factory;
	
	public StateMachine(StateFactory factory) {
		this.factory = factory;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T verify(Class<T> state) {
		if (current == null) {
			if (isStartState(state)) {
				next(state);
			} else {
				throw new IllegalStateException(state + " not a start state");
			}
		} else {
			if (!isInState(state)) {
				next(state);			
			}
		}
		
		return (T) current;
	}

	private boolean isStartState(Class<?> state) {
		return state.isAnnotationPresent(Start.class);
	}
	
	private <T> boolean isInState(Class<T> state) {
		return state.isAssignableFrom(current.getClass());
	}

	public <T> void next(Class<T> state) {
		current = check(state);
	}

	private <T> T check(Class<T> state) {
		T next = factory.createState(state);
		@SuppressWarnings("unchecked")
		Verifyer<T> verifyer = (Verifyer<T>) verifyerOf(state);
		verifyer.verify(next);
		return next;
	}
	
	private Verifyer<?> verifyerOf(Class<?> state) {
		State stateAnnotation = state.getAnnotation(State.class);
		if (stateAnnotation != null) {
			Class<? extends Verifyer<?>> verifyer = stateAnnotation.verifyer();
			try {
				return (Verifyer<?>) verifyer.newInstance();
			} catch (InstantiationException e) {
				throw new IllegalStateException(e);
			} catch (IllegalAccessException e) {
				throw new IllegalStateException(e);
			}
		} else {
			Class<?> superClass = state.getSuperclass();
			if (superClass != null) {
				return verifyerOf(superClass);
			} else {
				throw new IllegalStateException("Class " + state + " is not a State class");
			}
		}
	}
}
