package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.function.Supplier;

public interface BooleanExpression extends Supplier<Boolean> {
    default boolean evaluate(){
        return this.get();
    }
}
