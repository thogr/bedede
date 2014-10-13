package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.Bedede.defining;
import static com.github.thogr.bedede.Bedede.expecting;
import static com.github.thogr.bedede.Bedede.otherwise;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.examples.Telephone.Tone;

public class TelephoneExampleTest extends BehaviorDriven {

    // System under test
    static Telephone phone = new Telephone();

    @Before
    public void setUp() {
        phone = new Telephone();
    }

    public static abstract class PhoneState {
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

    public static abstract class OffHook extends PhoneState {

        void hangingUp() {
            phone.hangUp();
        }

        Expecting<BooleanCondition> hasTone(final Tone tone) {
            return expecting(phone.getTone() == tone,
                    otherwise("Unexpected tone: " + phone.getTone()));
        }
    }

    public static class WaitingForFirstDigit extends OffHook {
        public static Entry<WaitingForFirstDigit> REACHED =
                defining(WaitingForFirstDigit.class).entry(entry -> {entry.
                    given(OnHook.class)
                    .when(user -> user.pickingUpPhone())
                    .then(WaitingForFirstDigit.class);
                });

        @OnEntry
        Expecting<BooleanCondition> hasDialTone() {
            return hasTone(Tone.DIAL_TONE);
        }
    }

    public static class WaitingForMoreDigits extends OffHook  {

        public static Entry<WaitingForMoreDigits> afterPressingOneKey(final int key) {
            return defining(WaitingForMoreDigits.class).entry(entry -> {entry.

                    given(WaitingForFirstDigit.class)
                    .when(user -> user.pressingKeys(key))
                    .then(WaitingForMoreDigits.class);
            });
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
            return expecting(dialedNumber.equals(number),
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
