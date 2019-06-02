package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.values.Value;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Row {
    private final List<Value> cells;
    private final Function<String, Integer> mapping;

    public Row(List<Value> cells, Function<String, Integer> mapping) {
        this.cells = cells;
        this.mapping = mapping;
    }

    public Row(Value... cells) {
        this(Arrays.asList(cells), str -> -1);
    }

    public Row withMapping(Function<String, Integer> mapping) {
        return new Row(cells, mapping);
    }

    public Value cell(int i) {
        if (outOfBounds(i)) {
            throw new InvalidParameterException("Index " + i + " out of " +
                "bounds");
        }
        return cells.get(i - 1);
    }

    public Value cell(String column) {
        int i = mapping.apply(column);
        if (outOfBounds(i)) {
            throw new InvalidParameterException("Column " + column + " does " +
                "not exist");
        }
        return cell(i);
    }

    public boolean outOfBounds(int i) {
        return i <= 0 || i > cells.size();
    }

    @Override
    public String toString() {
        return cells.toString();
    }
}
