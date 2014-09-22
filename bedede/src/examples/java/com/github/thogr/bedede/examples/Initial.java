package com.github.thogr.bedede.examples;

import com.github.thogr.bedede.driver.Given;
import com.github.thogr.bedede.util.AbstractState;

public class Initial extends AbstractState {
	
	
	public static Given<Initial> STATE = new Given<Initial> () {

		@Override
		protected void perform() {
			SystemUnderTest.state = 0;
		}
	};

	@Override
	protected void onEntry() {
		assert (SystemUnderTest.state == 0);
	}

}
