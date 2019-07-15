package com.github.fabriciofx.cactoos.jdbc.cache.values;

import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import java.util.function.Supplier;

public interface BooleanExpression extends Supplier<Boolean> {
    default boolean evaluate() {
        return this.get();
    }

    default BooleanExpression withContext(Supplier<Row> context) {
        return this;
    }
}
