package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.Optional;

public class NumericExpression implements Value{

    public enum Operator{
        PLUS,
        MINUS,
        TIMES,
        DIV,
        MOD
    }

    private final Value left;
    private final Value right;
    private final Operator operator;

    public NumericExpression(
        final Value left,
        final Value right,
        final Operator operator
    ) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    private Value evaluate(){
        Object l = left.asObject();
        Object r = right.asObject();

        if(l instanceof Integer && r instanceof Integer){
            return new IntValue(
                (int) evaluateExpr((Integer) l,(Integer) r)
            );
        }

        double ld = left.asDouble().get();
        double rd = right.asDouble().get();

        return new DoubleValue(evaluateExpr(ld,rd));
    }

    private double evaluateExpr(double l, double r) {
        switch(operator){
            case PLUS:
                return l + r;
            case MINUS:
                return l - r;
            case TIMES:
                return l * r;
            case DIV:
                if(r == 0.0){
                    throw new RuntimeException("Invalid arithmetic operation: division by zero");
                }

                return l / r;
            case MOD:
                if(r == 0.0){
                    throw new RuntimeException("Invalid arithmetic operation: division by zero");
                }
                return (int) l % (int) r;
        }

        return 0.0; // unreachable code, hopefully will be fixed with the new switch expression
    }

    @Override
    public Object asObject() {
        return evaluate().asObject();
    }

    @Override
    public Optional<Integer> asInt() {
        return evaluate().asInt();
    }

    @Override
    public Optional<Double> asDouble() {
        return evaluate().asDouble();
    }

    @Override
    public Optional<String> asString() {
        return evaluate().asString();
    }
}
