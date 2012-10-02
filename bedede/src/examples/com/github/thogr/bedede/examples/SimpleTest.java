package com.github.thogr.bedede.examples;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.driver.Action;
import com.github.thogr.bedede.driver.Given;
import com.github.thogr.bedede.state.Start;
import com.github.thogr.bedede.state.State;
import com.github.thogr.bedede.state.Verifyer;
import com.github.thogr.bedede.util.BehaviorDrivenTestCase;


public class SimpleTest extends BehaviorDrivenTestCase {
	
	private static String name = "0";
	
	@State(verifyer = AbstractState.StateVerifier.class)
	public static abstract class AbstractState {
		public static class StateVerifier implements Verifyer<AbstractState> {

			@Override
			public void verify(AbstractState state) {
				state.verify();
			}
		}
		
		protected abstract void verify();
	}
	
	@Before
	public void returnToStartState() {
		name = "0";
	}
	
	@Start
	public static class State0 extends AbstractState {
		public static final Given<State0> REACHED = new Given<State0>() {
			@Override
			protected void perform() {
			}			
		};
		
		public static Action<State0, State1> start() {
			return new Action<State0, State1>() {
				@Override
				protected void perform(State0 self) {
					name = "1";
					then(State1.class);
				}
			};
		}

		@Override
		protected void verify() {
			assertEquals("Wrong State!", "0", name); 
		}
	}
	
	public static class State1 extends AbstractState {
		public static final Given<State1> REACHED = new Given<State1>() {
			@Override
			protected void perform() {
				given(State0.REACHED);
				when(State0.class).perform(State0.start());
			}
		};
		
		@Override
		protected void verify() {
			assertEquals("Wrong State!", "1", name); 
		}

		public static Action<State1, State2> gotoNext() {
			return new Action<State1, State2>() {
				@Override
				protected void perform(State1 self) {
					name = "2";
					then(State2.class);
				}
			};
		}
	}
	
	public static class State2 extends AbstractState {
		public static final Given<State2> REACHED = new Given<State2>() {
			@Override
			protected void perform() {
				given(State1.REACHED);
				when(State1.class).perform(State1.gotoNext());
			}
		};
		
		
		
		@Override
		protected void verify() {
			assertEquals("Wrong State!", "2", name); 
		}

		public static Action<State2, State2> update(final String data) {
			return new Action<State2, State2>() {
				@Override
				protected void perform(State2 self) {
					if ("1".equals(data)) {
						name = data;
					}
				}
			};
		}
	}
	
	@Test
	public void shouldGotoState2WhenInState1PerformingGotoNext() throws Exception {
		given(State1.REACHED);
		when(State1.class).perform(State1.gotoNext());
		then(State2.class);
	}
	
	@Test
	public void shouldGotoState1WhenInState2PerformingGoBack() throws Exception {
		given(State2.REACHED);
		when(State2.class).perform(State2.update("1"));
		then(State1.class);
	}
	
	@Test
	public void shouldStayWhenInState2PerformingGoBackAfterUpdatingWithWrongValue() throws Exception {
		given(State2.REACHED);
		when(State2.class).perform(State2.update("1234"));
		then(State2.class); // TODO: WHY?
	}
	
	@Test
	public void shouldGotoState1WhenInState2PerformingGoBackAfterUpdatingCorrectly() throws Exception {
		given(State2.REACHED);
		when(State2.class).perform(State2.update("1"));
		then(State1.class);
	}
}
