package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.Bedede.expecting;
import static com.github.thogr.bedede.Bedede.otherwise;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;

public class BooleanConditionTest extends BehaviorDriven {

    @InitialState
    public static class State1 {
        private final int status = 1;

        private String name = "Kalle";

        Expecting<BooleanCondition> hasStatus(final int status) {
            return expecting(this.status == status, otherwise("Unexpected status: " + this.status));
        }

        Expecting<BooleanCondition> hasName(final String name) {
            return expecting(this.name.equals(name), otherwise("Unexpected name: " + this.name));
        }

        void changesNameTo(final String newName) {
            this.name = newName;
        }

        @OnEntry
        public Expecting<BooleanCondition> onEntry() {
            return hasStatus(1).and(hasName("Kalle"));
        }
    }

    @Test
    public void test() {
        given(State1.class)
        .when(it -> it.changesNameTo("Nisse"))
        .then(it -> it.hasStatus(1))
        .then(it -> it.hasName("Nisse"));
    }

}
