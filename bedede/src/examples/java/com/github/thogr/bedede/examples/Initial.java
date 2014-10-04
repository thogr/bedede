package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.BehaviorDriven.expecting;
import static com.github.thogr.bedede.BehaviorDriven.otherwise;
import static org.junit.Assert.assertTrue;

import com.github.thogr.bedede.AbstractState;
import com.github.thogr.bedede.Condition;
import com.github.thogr.bedede.Entry;

public class Initial extends AbstractState {

    public static Entry<Initial> DEFAULT_ENTRY = new Entry<Initial> () {
        @Override
        protected void perform() {
            assertTrue(SystemTested.getState() == 0);
            SystemTested.doSomeThing();
            then(Initial.class);
        }
    };

    @Override
    protected void onEntry() {
        System.out.println("on entry");
        assertTrue(SystemTested.getState() == 1);
    }

    public Condition<SimpleCondition<Boolean>> expectState(final int s) {
        return expecting(() -> SystemTested.getState() == s,
                otherwise("Something is wrong"));
    }
}
