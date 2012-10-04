package com.github.thogr.bedede.examples;

import com.github.thogr.bedede.driver.Action;
import com.github.thogr.bedede.driver.Given;
import com.github.thogr.bedede.state.InternalState;
import com.github.thogr.bedede.state.State;
import com.github.thogr.bedede.state.Verifyer;
import com.github.thogr.bedede.util.AbstractState;
import com.github.thogr.bedede.util.BehaviorDrivenTestCase;

public class InternalStateTest extends BehaviorDrivenTestCase {

	public static class Outer extends AbstractState {
		
		@State(verifyer = Age.AgeVerifyer.class)
		public abstract static class Age implements InternalState<Outer>{

			protected abstract boolean isRight();
			
			public static class AgeVerifyer implements Verifyer<Age> {

				@Override
				public void verify(Age age) {
					for (int i = 0; !age.isRight() && i < 200; i++) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		@Override
		protected void verify() {
			// TODO Auto-generated method stub
			
		}

		public void something() {
			// TODO Auto-generated method stub
			
		}; 
		
		public static Given<Outer> REACHED = new Given<Outer>() {
			@Override
			protected void perform() {
				
			}
		};
		
		public Action<Outer> someAction() {
			return new Action<Outer>() {

				@Override
				protected void perform(Outer state) {
					when(age(12)).perform().something();
				}				
			};
		}
		
		private Age age(final long i) {
			return new Age(){
				
				@Override
				protected boolean isRight() {
					long time = System.currentTimeMillis();
					return ((time / 1000) % i) == 0;
				}
			};
		}
	}
}
