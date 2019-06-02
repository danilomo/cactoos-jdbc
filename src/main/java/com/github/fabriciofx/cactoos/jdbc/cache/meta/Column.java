package com.github.fabriciofx.cactoos.jdbc.cache.meta;

public interface Column {

    String label();

    String name();

    String catalogName();

    int type();

    String typeName();

    String className();
}
