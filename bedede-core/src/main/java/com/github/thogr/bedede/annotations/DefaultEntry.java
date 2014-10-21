package com.github.thogr.bedede.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/**
 * Marks an Entry field the default object, so that given(Class<State>) can resolve to an Entry field
 * when the State class has multiple Entry fields.
 */
public @interface DefaultEntry {

}
