package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.values.BooleanExpression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Value;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Select implements Iterable<Row> {

    private final Iterable<Row> rows;
    private final List<Expression> expressions;
    private final BooleanExpression filter;

    public Select(
        final Iterable<Row> rows,
        final List<Expression> expressions,
        final BooleanExpression filter
    ) {
        this.rows = rows;
        this.expressions = expressions;
        this.filter = filter;
    }

    public Select(
        final Iterable<Row> rows,
        final List<Expression> expressions
    ) {
        this(
            rows,
            expressions,
            () -> true
        );
    }

    @Override
    public Iterator<Row> iterator() {
        return new QueryIterator();
    }

    private class QueryIterator implements
        Iterator<Row>, Supplier<Row> {

        private final Iterator<Row> iterator;
        private final List<Expression> columns;
        private final BooleanExpression filterExpression;
        private Row currentRow;
        private boolean hasNextWasCalled;
        private boolean hasNext;

        private QueryIterator() {
            iterator = rows.iterator();
            columns = expressions
                .stream()
                .map(exp -> exp.withContext(this))
                .collect(Collectors.toList());
            filterExpression = filter.withContext(this);
            hasNextWasCalled = false;
            hasNext = true;
        }

        @Override
        public boolean hasNext() {
            if (hasNextWasCalled) {
                return hasNext;
            }
            hasNextWasCalled = true;
            if (!iterator.hasNext()) {
                hasNext = false;
                return false;
            }
            currentRow = iterator.next();
            if (!filterExpression.evaluate()) {
                hasNextWasCalled = false;
                return hasNext();
            }
            hasNext = true;
            return true;
        }

        @Override
        public Row next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            hasNextWasCalled = false;
            List<Value> list = new ArrayList<>();
            for (Expression exp : columns) {
                list.add(exp.evaluate());
            }
            return new Row(list);
        }

        @Override
        public Row get() {
            return currentRow;
        }
    }

    public List<Row> asList(){
        List<Row> list = new ArrayList<>();
        iterator().forEachRemaining(list::add);
        return list;
    }
}
