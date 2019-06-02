package com.github.fabriciofx.cactoos.jdbc.cache.meta;

import java.sql.Types;

public class IntColumn extends AbstractColumn {

    public IntColumn(String name) {
        super(name);
    }

    @Override
    public int type() {
        return Types.INTEGER;
    }

    @Override
    public String typeName() {
        return "INTEGER";
    }

    @Override
    public String className() {
        return Integer.class.toString();
    }
}
