package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.state.Bedede.expecting;
import static com.github.thogr.bedede.state.Bedede.otherwise;

import org.junit.Test;

import com.github.thogr.bedede.state.ConditionVerifier;
import com.github.thogr.bedede.util.BehaviorDrivenTestCase;


public class InitialTest extends BehaviorDrivenTestCase {

	@Test
	public void testName() throws Exception {
		given(Initial.STATE);
		then(expecting(itsTrue(), otherwise("it's false")));
	}

	private MyCondition<Boolean> itsTrue() {
		return () -> true;
	}

	@Override
	protected ConditionVerifier<MyCondition<Boolean>> verifier() {
		return new MyConditionVerifier();
	}

}
