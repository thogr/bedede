package com.github.thogr.bedede.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.github.thogr.bedede.StateVerifier;

@Retention(RetentionPolicy.RUNTIME)
public @interface State {
    Class<? extends StateVerifier<?>> verifier() default StateVerifier.Default.class;
}
