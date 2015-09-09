// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.test;

import static com.github.thogr.bedede.CoreExpressions.a;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.github.thogr.bedede.core.BehaviorDriven;

public class MixedTest extends BehaviorDriven {

    @Test
    public void shouldHaveAccessToBehaviorExpressions() throws Exception {
        given(a("A String"))
        .then(the->the.length(), is(8));
    }

}
