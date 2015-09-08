package com.github.thogr.bedede.test;

import com.github.thogr.bedede.state.Entry;
import com.github.thogr.bedede.state.internal.EntryTest.View1;

public class View2 {

    public static final Entry<View2> REACHED = new Entry<View2>() {
        @Override
        protected void perform() {
            given(View1.class)
            .when(it -> it.opensView2());
            then(View2.class);
        }
    };

    /**  */
    public void doesSomething() {}
}
