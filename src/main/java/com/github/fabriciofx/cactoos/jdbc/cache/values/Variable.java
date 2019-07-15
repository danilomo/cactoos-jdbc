package com.github.fabriciofx.cactoos.jdbc.cache.values;

import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import java.util.function.Supplier;

public class Variable implements Expression {
    private final String name;
    private final Supplier<Row> row;

    public Variable(
        String name,
        Supplier<Row> row
    ) {
        this.name = name;
        this.row = row;
    }

    public Variable(String name) {
        this(name, () -> new Row());
    }

    @Override
    public Value get() {
        return row.get().cell(name);
    }

    @Override
    public Expression withContext(final Supplier<Row> context) {
        return new Variable(name, context);
    }
}
