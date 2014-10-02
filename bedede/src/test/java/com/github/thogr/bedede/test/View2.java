package com.github.thogr.bedede.test;

import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.GivenTest.AbstractTestBaseState;
import com.github.thogr.bedede.GivenTest.View1;

public class View2 extends AbstractTestBaseState {

    public static final Entry<View2> REACHED = new Entry<View2>() {
        @Override
        protected void perform() {
            given(View1.class)
            .when().openingView2();
            then(View2.class);
        }
    };
}
