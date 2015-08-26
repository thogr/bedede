package com.github.thogr.bedede;

import com.github.thogr.bedede.core.internal.AssumingImpl;


class AssumingExtension {

    static <T, S> ThenExpecting<S> then(AssumingImpl<T> self, Class<S> state) {
        self.getController().then(state)
    }
}
