package com.github.fabriciofx.cactoos.jdbc.cache;


import java.sql.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Select implements Supplier<ResultSet> {

    private final List<String> columns;
    private final InMemoryResultSet dataset;
    private final Predicate<Row> where;

    public Select(List<String> columns, InMemoryResultSet dataset, Predicate<Row> where) {
        this.columns = columns;
        this.dataset = dataset;
        this.where = where;
    }

    @Override
    public ResultSet get() {
        return null;
    }
}
