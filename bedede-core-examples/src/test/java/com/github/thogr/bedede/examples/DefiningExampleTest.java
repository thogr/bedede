// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.core.CoreExpressions.entry;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;

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
            return entry(Moved.class).as().
               given(Initial.class)
               .when(it -> it.prepares(i))
               .when(it -> it.moves())
               .then(Moved.class);
        }

        @DefaultEntry
        public static final Entry<Moved> REACHED = entry(Moved.class).as().
                given(Initial.class)
                .when(it -> it.moves())
                .then(Moved.class);
    }

    @Test
    public void test0() {
        given(Moved.class);
    }

    @Test
    public void test1() {
        given(Moved.afterPreparation(4));
    }
}
