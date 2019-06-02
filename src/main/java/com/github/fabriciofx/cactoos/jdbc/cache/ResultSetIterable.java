package com.github.fabriciofx.cactoos.jdbc.cache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultSetIterable implements Iterable<Row> {

    private final ResultSet resultSet;
    private boolean used;

    public ResultSetIterable(ResultSet resultSet) {
        this.resultSet = resultSet;
        this.used = false;
    }

    @Override
    public Iterator<Row> iterator() {
        if (!used) {
            return new ResultSetIterator();
        }
        throw new RuntimeException("Iterable already used");
    }

    private class ResultSetIterator implements Iterator<Row> {

        private Row row;
        private boolean hasNext;
        private RowSupplier supplier;

        ResultSetIterator() {
            supplier = new RowSupplier(resultSet);
            try {
                hasNext = resultSet.next();
            } catch (SQLException ex) {
                hasNext = false;
            }
            if (hasNext) {
                row = supplier.get();
            }
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Row next() {
            if (!hasNext) {
                throw new RuntimeException("");
            }
            Row result = row;
            try {
                hasNext = resultSet.next();
            } catch (SQLException ex) {
                throw new RuntimeException("");
            }
            if (hasNext) {
                row = supplier.get();
            }
            return result;
        }
    }
}
