package com.github.fabriciofx.cactoos.jdbc.cache.values;

import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import java.util.Arrays;
import java.util.function.Supplier;

public class ComparisonExpression implements BooleanExpression {

    private final Expression left;
    private final Expression right;
    private final Operator operator;

    public ComparisonExpression(
        final Expression left,
        final Expression right,
        final Operator operator
    ) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Boolean get() {
        switch (operator){
            case EQ:
                return equalityTest();
            case NEQ:
                return ! equalityTest();
            default:
                return comparisonTest();
        }
    }

    private boolean comparisonTest() {
        Value leftVal = left.evaluate();
        Value rightVal = right.evaluate();
        if(leftVal.isNumeric() && rightVal.isNumeric()){
            return compareOperands(
                leftVal.asDouble().get(),
                rightVal.asDouble().get()
            );
        }

        if(leftVal instanceof StringValue && rightVal instanceof StringValue){
            return compareOperands(
                leftVal.asString().get(),
                rightVal.asString().get()
            );
        }

        throw new RuntimeException("Operands aren't comparable.");
    }

    private <T extends Comparable<T>> boolean compareOperands(T l, T r){
        switch(operator){
            case LT:
                return l.compareTo(r) < 0;
            case GT:
                return l.compareTo(r) > 0;
            case LE:
                return l.compareTo(r) <= 0;
            case GE:
                return l.compareTo(r) >= 0;
            default:
                return false;
        }
    }

    private boolean equalityTest() {
        return left.evaluate().asObject()
            .equals(right.evaluate().asObject());
    }

    public enum Operator{
        EQ,
        NEQ,
        LT,
        GT,
        LE,
        GE
    }

    @Override
    public BooleanExpression withContext(final Supplier<Row> context) {
        return new ComparisonExpression(
            left.withContext(context),
            right.withContext(context),
            operator
        );
    }
}
