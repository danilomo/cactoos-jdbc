package com.github.fabriciofx.cactoos.jdbc.cache;

import java.util.concurrent.Callable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class MiscMatchers {
    static <T extends Exception> void expectsException(
        Callable<?> callable,
        Class<T> clazz) {
        try {
            callable.call();
        } catch (Exception ex) {
            assertThat(
                ex.getClass(),
                is((Object) clazz)
            );
        }
    }
}
