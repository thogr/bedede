package com.github.thogr.bedede;

import static com.github.thogr.bedede.EntryFinder.getDefaultEntry;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;

public class EntryFinderTest {

    @InitialState
    public static class InitialStateWithNoEntry {
    }

    public static class StateWithNoEntry {
        public static final Integer OTHER_FIELD = 0;
    }

    public static class StateWithOneEntry {
        public static Entry<StateWithOneEntry> REACHED =
                new Entry<StateWithOneEntry>() {
            @Override
            protected void perform() {

            }
        };
    }

    public static class StateWithOneEntryMethod {
        public static Entry<StateWithOneEntryMethod> reached() {
            return new Entry<StateWithOneEntryMethod>() {
                @Override
                protected void perform() {
                    System.out.println("Hej");
                }
            };
        }
    }

    public static class StateWithNoDefaultEntry {
        public static Entry<StateWithNoDefaultEntry> REACHED =
                new Entry<StateWithNoDefaultEntry>() {
            @Override
            protected void perform() {

            }
        };

        public static Entry<StateWithNoDefaultEntry> REACHED2 =
                new Entry<StateWithNoDefaultEntry>() {
            @Override
            protected void perform() {

            }
        };
    }

    public static class StateWithWrongEntryType {
        public static Entry<Integer> WRONG_TYPE_ENTRY =
                new Entry<Integer>() {
            @Override
            protected void perform() {

            }
        };
    }

    public static class StateWithFirstAnnotatedDefaultEntry {
        @DefaultEntry
        public static Entry<StateWithFirstAnnotatedDefaultEntry> DEFAULT =
                new Entry<StateWithFirstAnnotatedDefaultEntry>() {
            @Override
            protected void perform() {

            }
        };

        public static Entry<StateWithFirstAnnotatedDefaultEntry> OTHER =
                new Entry<StateWithFirstAnnotatedDefaultEntry>() {
            @Override
            protected void perform() {

            }
        };
    }

    public static class StateWithLastAnnotatedDefaultEntry {
        public static Entry<StateWithLastAnnotatedDefaultEntry> OTHER =
                new Entry<StateWithLastAnnotatedDefaultEntry>() {
            @Override
            protected void perform() {

            }
        };

        @DefaultEntry
        public static Entry<StateWithLastAnnotatedDefaultEntry> DEFAULT =
                new Entry<StateWithLastAnnotatedDefaultEntry>() {
            @Override
            protected void perform() {

            }
        };
    }


    public static class StateWithParametrizedMethodFirstAndLastAnnotatedDefaultEntry {
        public static Entry<StateWithParametrizedMethodFirstAndLastAnnotatedDefaultEntry> other(final int i) {
            return new Entry<StateWithParametrizedMethodFirstAndLastAnnotatedDefaultEntry>() {
                @Override
                protected void perform() {}
            };
        };

        @DefaultEntry
        public static Entry<StateWithParametrizedMethodFirstAndLastAnnotatedDefaultEntry> DEFAULT =
                new Entry<StateWithParametrizedMethodFirstAndLastAnnotatedDefaultEntry>() {
            @Override
            protected void perform() {}
        };
    }

    @Test
    public void shouldNotFindDefaultEntryWhenInitalStateWithNoEntry() {
        assertNull(getDefaultEntry(InitialStateWithNoEntry.class));
    }

    @Test
    public void shouldFindEntryWhenOnlyOneEntry() {
        assertThat(getDefaultEntry(StateWithOneEntry.class), is(StateWithOneEntry.REACHED));
    }

    @Test
    public void shouldFindEntryWhenOnlyOneEntryMethod() {
        assertNotNull(getDefaultEntry(StateWithOneEntryMethod.class));
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowWhenNoEntry() throws Exception {
        getDefaultEntry(StateWithNoEntry.class);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowWhenNoDefaultEntry() throws Exception {
        getDefaultEntry(StateWithNoDefaultEntry.class);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowWhenWrongEntryType() throws Exception {
        getDefaultEntry(StateWithWrongEntryType.class);
    }

    @Test
    public void shouldFindEntryWhenFirstAnnotatedDefaultEntry() {
        assertThat(getDefaultEntry(StateWithFirstAnnotatedDefaultEntry.class), is(StateWithFirstAnnotatedDefaultEntry.DEFAULT));
    }

    @Test
    public void shouldFindEntryWhenLastAnnotatedDefaultEntry() {
        assertThat(getDefaultEntry(StateWithLastAnnotatedDefaultEntry.class), is(StateWithLastAnnotatedDefaultEntry.DEFAULT));
    }

    @Test
    public void shouldFindEntryWhenLastAnnotatedDefaultEntryAndFirstIsParametrized() {
        assertThat(getDefaultEntry(StateWithParametrizedMethodFirstAndLastAnnotatedDefaultEntry.class),
                is(StateWithParametrizedMethodFirstAndLastAnnotatedDefaultEntry.DEFAULT));
    }

}
