package com.github.fabriciofx.cactoos.jdbc.cache.values;

import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import java.util.function.Supplier;

public class Variable extends ValueDecorator {
    private final String name;
    private final Supplier<Row> row;

    public Variable(
        final String name,
        final Supplier<Row> row
    ) {
        this.name = name;
        this.row = row;
    }

    @Override
    public Value value() {
        return row.get().cell(name);
    }
}
