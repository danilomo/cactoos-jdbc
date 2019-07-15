package com.github.fabriciofx.cactoos.jdbc.cache.values;

import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import java.util.function.Supplier;

public class BinaryBooleanExpression implements BooleanExpression {
    private final BooleanExpression left;
    private final BooleanExpression right;
    private final Operator operator;

    public BinaryBooleanExpression(
        BooleanExpression left,
        BooleanExpression right,
        Operator operator
    ) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }
    @Override
    public Boolean get() {
        switch (operator) {
            case AND:
                if (!left.evaluate()) {
                    return false;
                }
                return right.evaluate();
            case OR:
                if (left.evaluate()) {
                    return true;
                }
                return right.evaluate();
        }
        return false;
    }
    @Override
    public BooleanExpression withContext(final Supplier<Row> context) {
        return new BinaryBooleanExpression(
            left.withContext(context),
            right.withContext(context),
            operator
        );
    }

    public enum Operator {
        AND,
        OR
    }
}
