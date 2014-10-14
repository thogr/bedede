package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.Bedede.defining;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;

public class DefineTest extends BehaviorDriven {

    @InitialState
    public static class Initial {
        void prepares() {
            System.out.println("prep..");
        }
        void moves() {
            System.out.println("move..");
        }
    }

    public static class Moved {
        public static Entry<Moved> reached1() {
            return defining().given(Moved.class).as().
               given(Initial.class)
               .when(it -> it.prepares())
               .when(it -> it.moves())
               .then(Moved.class);
        }//7

        @DefaultEntry
        public static Entry<Moved> REACHED2 = new Entry<Moved>() {
            @Override
            protected void perform() {
                given(Initial.class)
                .when(it -> it.moves())
                .then(Moved.class);
            }
        };//8

        public static Entry<Moved> reached3() {
            return new Entry<Moved>() {
                @Override
                protected void perform() {
                    given(Initial.class)
                    .when(it -> it.moves())
                    .then(Moved.class);
                }
            };
        }//10

        public static final Entry<Moved> REACHED9 = defining().given(Moved.class).as()
                .given(Initial.class)
                .when(it -> it.moves())
                .then(Moved.class); // 4

    }

    @Test
    public void test0() {
        given(Moved.class);
    }

    @Test
    public void test1() {
        given(Moved.reached1());
    }

    @Test
    public void test2() {
        given(Moved.REACHED2);
    }

    @Test
    public void test3() {
        given(Moved.reached3());
    }

    @Test
    public void test7() {
        given(new Entry<Moved>() {
            @Override
            protected void perform() {
                given(Initial.class)
                .when(it -> it.moves())
                .then(Moved.class);
            }
        });
    }

    @Test
    public void test9() {
        given(Moved.REACHED9);
    }
}
