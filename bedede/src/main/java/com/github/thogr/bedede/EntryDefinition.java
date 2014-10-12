package com.github.thogr.bedede;

@FunctionalInterface
public interface EntryDefinition<S> {
    void perform(EntryAccess<S> entry);
}
