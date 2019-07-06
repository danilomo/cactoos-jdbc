package com.github.fabriciofx.cactoos.jdbc.cache.algebra;

import com.github.fabriciofx.cactoos.jdbc.cache.ResultSetIterable;
import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Value;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Projection implements Iterable<Row>{

    private final Iterable<Row> rows;
    private final List<Expression> expressions;

    public Projection(
        final Iterable<Row> rows,
        final List<Expression> expressions
    ) {
        this.rows = rows;
        this.expressions = expressions;
    }

    @Override
    public Iterator<Row> iterator() {
        return new ProjectionIterator();
    }

    private class ProjectionIterator implements
        Iterator<Row>, Supplier<Row> {

        private final Iterator<Row> iterator;
        private Row currentRow;
        private final List<Expression> columns;

        private ProjectionIterator(){
            iterator = rows.iterator();
            columns = expressions
                .stream()
                .map(exp -> exp.withContext(this))
                .collect(Collectors.toList());
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Row next() {
            currentRow = iterator.next();
            List<Value> list = new ArrayList<>();
            for(Expression exp: columns){
                list.add(exp.evaluate());
            }
            return new Row(list);
        }

        @Override
        public Row get() {
            return currentRow;
        }
    }
}
