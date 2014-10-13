package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.Bedede.defining;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.EntryDefinition;
import com.github.thogr.bedede.annotations.InitialState;

public class DefineTest extends BehaviorDriven {

    @InitialState
    public static class Initial {
        void moves() {

        }
    }

    public static class Moved {
        public static Entry<Moved> reached1() {
            return defining(Moved.class).entry(e -> {e.
               given(Initial.class)
               .when(it -> it.moves())
               .then(Moved.class);
            });
        }//7

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

        public static EntryDefinition<Moved> reached4() {return e->e.
            given(Initial.class)
            .when(it -> it.moves())
            .then(Moved.class);
        }//5-6

        public static final EntryDefinition<Moved> REACHED5 = e -> {e.
            given(Initial.class)
            .when(it -> it.moves())
            .then(Moved.class);
        };//5

        public static final Entry<Moved> REACHED8 = defining(Moved.class).entry(e -> {e.
            given(Initial.class)
            .when(it -> it.moves())
            .then(Moved.class);
        });//5

        public static final Entry<Moved> REACHED9 = defining(Moved.class).entry()
                .given(Initial.class)
                .when(it -> it.moves())
                .then(Moved.class);

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
    public void test4() {
        given(defining(Moved.class).entry(Moved.reached4()));
    }

    @Test
    public void test5() {
        given(defining(Moved.class).entry(Moved.REACHED5));
    }

    @Test
    public void test6() {
        given(defining(Moved.class).entry(e -> {e.
            given(Initial.class)
            .when(it -> it.moves())
            .then(Moved.class);
        }));
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
}
