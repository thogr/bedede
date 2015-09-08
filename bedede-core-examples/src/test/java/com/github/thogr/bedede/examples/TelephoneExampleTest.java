package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.core.CoreExpressions.otherwise;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.examples.Telephone.Tone;
import com.github.thogr.bedede.state.Entry;
import com.github.thogr.bedede.state.StateExpressions;

public class TelephoneExampleTest extends BehaviorDriven {

    // System under test
    private static Telephone phone = new Telephone();

    @Before
    public void setUp() {
        phone = new Telephone();
    }

    public abstract static class PhoneState {
        void pressingKeys(final int ... key) {
            for (final int i : key) {
                phone.pressKey(i);
            }
        }
    }

    @InitialState
    public static class OnHook extends PhoneState {
        void pickingUpPhone() {
            phone.pickUp();
        }
    }

    public abstract static class OffHook extends PhoneState {

        void hangingUp() {
            phone.hangUp();
        }

        Expecting<BooleanCondition> hasTone(final Tone tone) {
            return StateExpressions.expecting(phone.getTone() == tone,
                    otherwise("Unexpected tone: " + phone.getTone()));
        }
    }

    public static class WaitingForFirstDigit extends OffHook {
        public static final Entry<WaitingForFirstDigit> REACHED =
                StateExpressions.entry(WaitingForFirstDigit.class).as().
                    given(OnHook.class)
                    .when(user -> user.pickingUpPhone())
                    .then(WaitingForFirstDigit.class);

        @OnEntry
        Expecting<BooleanCondition> hasDialTone() {
            return hasTone(Tone.DIAL_TONE);
        }
    }

    public static class WaitingForMoreDigits extends OffHook  {

        public static Entry<WaitingForMoreDigits> afterPressingOneKey(final int key) {
            return StateExpressions.entry(WaitingForMoreDigits.class).as().
                    given(WaitingForFirstDigit.class)
                    .when(user -> user.pressingKeys(key))
                    .then(WaitingForMoreDigits.class);
        }

        @OnEntry
        Expecting<BooleanCondition> hasNoTone() {
            return hasTone(Tone.SILENT);
        }
    }

    public static class Calling extends OffHook  {

        @OnEntry
        Expecting<BooleanCondition> hasCallTone() {
            return hasTone(Tone.CALL_TONE);
        }

        Expecting<BooleanCondition> hasDialed(final String number) {
            final String dialedNumber = phone.getDialedNumber();
            return StateExpressions.expecting(dialedNumber.equals(number),
                    otherwise("Unexpected dialed number: " + dialedNumber));
        }
    }

    @Test
    public void shouldSoundCallToneWhenCallingEmergencyNumber() {
        given(WaitingForMoreDigits.afterPressingOneKey(1))
        .when(user -> user.pressingKeys(1, 2))
        .then(Calling.class)
        .then(user -> user.hasDialed("112"));
    }
}
