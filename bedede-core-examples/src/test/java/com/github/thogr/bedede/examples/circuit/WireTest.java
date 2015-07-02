package com.github.thogr.bedede.examples.circuit;

import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.performing;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;

import javax.swing.event.ChangeListener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WireTest {

    @Mock
    private ChangeListener listener;

    @Test
    public void shouldFireStateChangedWhenAddingListener() {
        given(new Wire())
        .when(performing(it -> it.addChangeListener(listener)))
        ;then(listener).should().stateChanged(any());
    }

    @Test
    public void shouldFireStateChangedWhenSettingSignal() {
        given(new Wire()).with(it -> {
            it.addChangeListener(listener);
        })
        .when(performing(the -> the.setSignal(true)))
        ;then(listener).should(times(2)).stateChanged(any());
    }
}
