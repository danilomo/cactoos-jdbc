package com.github.fabriciofx.cactoos.jdbc.cache.algebra;

import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import java.sql.ResultSet;
import java.util.Iterator;

public class Projection implements Iterable<Row>{

    private ResultSet resultSet;

    @Override
    public Iterator<Row> iterator() {
        return null;
    }

    private class ProjectionIterator implements Iterator<Row>{
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Row next() {
            return null;
        }
    }
}
