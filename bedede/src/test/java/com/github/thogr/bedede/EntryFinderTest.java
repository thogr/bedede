package com.github.thogr.bedede;

import static com.github.thogr.bedede.EntryFinder.getDefaultEntry;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

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

    @Test
    public void shouldNotFindDefaultEntryWhenInitalStateWithNoEntry() {
        assertNull(getDefaultEntry(InitialStateWithNoEntry.class));
    }

    @Test
    public void shouldFindEntryWhenOnlyOneEntry() {
        assertThat(getDefaultEntry(StateWithOneEntry.class), is(StateWithOneEntry.REACHED));
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

}
