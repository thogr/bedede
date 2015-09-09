package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.state.StateExpressions.expecting;
import static com.github.thogr.bedede.state.StateExpressions.otherwise;
import static org.junit.Assert.assertTrue;

import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.state.Entry;

public class Initial {

    private static SystemTested system = new SystemTested();

    @DefaultEntry
    public static final Entry<Initial> DEFAULT_ENTRY = new Entry<Initial>() {
        @Override
        protected void perform() {
            system.doSomeThing();
        }
    };

    public static final Entry<Initial> OTHER_ENTRY = new Entry<Initial>() {
        @Override
        protected void perform() {
            then(Initial.class);
        }
    };

    @OnEntry
    protected void onEntry() {
        assertTrue(system.getState() == 1);
    }

    public Expecting<BooleanCondition> hasState(final int s) {
        return expecting(system.getState() == s, otherwise("Something is wrong"));
    }
}
