// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.StateExpressions.at;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.StateExpressions;
import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.state.Entry;

public class DefiningExampleTest extends BehaviorDriven {

    @InitialState
    public static class Initial {
        void prepares(final int i) {
        }

        void moves() {
        }
    }

    public static class Moved {
        public static Entry<Moved> afterPreparation(final int i) {
            return StateExpressions.entry(Moved.class).as().
               given(Initial.class)
               .when(it -> it.prepares(i))
               .when(it -> it.moves())
               .then(Moved.class);
        }

        @DefaultEntry
        public static final Entry<Moved> REACHED = StateExpressions.entry(Moved.class).as().
                given(Initial.class)
                .when(it -> it.moves())
                .then(Moved.class);
    }

    @Test
    public void test0() {
        given(at(Moved.class));
    }

    @Test
    public void test1() {
        given(Moved.afterPreparation(4));
    }
}
