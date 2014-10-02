package com.github.thogr.bedede.examples;

import com.github.thogr.bedede.AbstractState;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.annotations.DefaultEntry;

public class Initial extends AbstractState {

    @DefaultEntry
    public static Entry<Initial> DEFAULT_ENTRY = new Entry<Initial> () {

        @Override
        protected void perform() {
            SystemTested.state = 0;
        }
    };

    @Override
    protected void onEntry() {
        assert (SystemTested.state == 0);
    }

}
