package com.github.fabriciofx.cactoos.jdbc.cache.meta;

import java.sql.Types;

public class StringColumn extends AbstractColumn {

    public StringColumn(String name) {
        super(name);
    }

    @Override
    public int type() {
        return Types.VARCHAR;
    }

    @Override
    public String typeName() {
        return "VARCHAR";
    }

    @Override
    public String className() {
        return String.class.toString();
    }
}