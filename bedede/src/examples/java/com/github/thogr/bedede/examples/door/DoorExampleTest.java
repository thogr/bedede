package com.github.thogr.bedede.examples.door;

import static com.github.thogr.bedede.Bedede.entry;
import static com.github.thogr.bedede.Bedede.expecting;
import static com.github.thogr.bedede.Bedede.otherwise;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;

public class DoorExampleTest extends BehaviorDriven {

    static final int CODE = 123;
    static final Key CORRECT_KEY = new Key(CODE);
    static final Key WRONG_KEY = new Key(999);

    protected static Door door;

    public static abstract class DoorState {

        void turnsKey(final Key key) {
            door.turnKey(key);
        }

        public void opens() {
            door.open();
        }

        public void close() {
            door.close();
        }
    }

    @Before
    public void setUp() {
        door = new Door(CODE);
    }

    @InitialState
    public static class DoorLocked extends DoorState {

        @OnEntry
        public Expecting<BooleanCondition> shouldBeLocked() {
            return expecting(door.isLocked(), otherwise("Unexpected unlocked door"));
        }

        public static Entry<DoorLocked> byLockingWith(final Key key) {
            return entry(DoorLocked.class).as()
                    .given(DoorUnlocked.class)
                    .when(it -> it.turnsKey(key))
                    .then(DoorLocked.class);
        }
    }

    public static class DoorUnlocked extends DoorState {

        @DefaultEntry
        public static Entry<DoorUnlocked> byUnlockingWithCorrectKey() {
            return byUnlockingWith(CORRECT_KEY);
        }

        public static final Entry<DoorUnlocked> byUnlockingWith(final Key key) {
            return entry(DoorUnlocked.class).as()
                    .given(DoorLocked.class)
                    .when(it -> it.turnsKey(key))
                    .then(DoorUnlocked.class);
        }

        @OnEntry
        public Expecting<BooleanCondition> shouldBeUnlocked() {
            return expecting(!door.isLocked(), otherwise("Unexpected locked door"));
        }
    }

    public static class DoorOpen extends DoorState {

        @DefaultEntry
        public static Entry<DoorOpen> byUnlockingFirst() {
            return entry(DoorOpen.class).as()
                    .given(DoorUnlocked.class)
                    .when(it -> it.opens())
                    .then(DoorOpen.class);
        }

        @OnEntry
        public Expecting<BooleanCondition> shouldBeOpen() {
            return expecting(door.isOpen(), otherwise("Unexpected closed door"));
        }
    }


    @Test
    public void shouldNotLockWithWrongKey() {
        given(DoorUnlocked.class)
        .when(it -> it.turnsKey(WRONG_KEY))
        .then(DoorUnlocked.class);
    }

    @Test
    public void shouldNotUnlockWithWrongKey() {
        given(DoorLocked.class)
        .when(it -> it.turnsKey(WRONG_KEY))
        .then(DoorLocked.class);
    }

    @Test
    public void shouldNotUnlockWithWrongKeyAgain() {
        given(DoorLocked.byLockingWith(CORRECT_KEY))
        .when(it -> it.turnsKey(WRONG_KEY))
        .then(DoorLocked.class);
    }

    @Test
    public void shouldNotCloseWhenLocked() throws Exception {
        given(DoorOpen.class)
        .when(it -> it.turnsKey(CORRECT_KEY))
        .when(it -> it.close())
        .then(DoorOpen.class);
    }

    @Test
    public void shouldCloseWhenWrongKey() throws Exception {
        given(DoorOpen.class)
        .when(it -> it.turnsKey(WRONG_KEY))
        .when(it -> it.close())
        .then(DoorUnlocked.class);
    }
}
