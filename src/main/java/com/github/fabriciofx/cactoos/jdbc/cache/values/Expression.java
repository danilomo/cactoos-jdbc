package com.github.fabriciofx.cactoos.jdbc.cache.values;

import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import java.util.function.Supplier;

public interface Expression extends Supplier<Value> {
    default Value evaluate() {
        return get();
    }

    default Expression withContext(Supplier<Row> context) {
        return this;
    }
}
