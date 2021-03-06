package com.github.thogr.bedede.support

import com.github.thogr.bedede.state.Entry;

class EntryStaticExtension {

    static <T> Entry<T> entry(@DelegatesTo(strategy = Closure.DELEGATE_FIRST, value = Entry) Closure closure) {
        Entry<T> entry = new Entry() {
                    protected void perform() {
                        closure();
                    }
                }
        closure.delegate = entry;
        closure.resolveStrategy = Closure.DELEGATE_FIRST

        return entry;
    }
}
