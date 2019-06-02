package com.github.fabriciofx.cactoos.jdbc.cache.meta;

public abstract class AbstractColumn implements Column {
    private String name;

    public AbstractColumn(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String label() {
        return name;
    }

    @Override
    public String catalogName() {
        return name;
    }
}
