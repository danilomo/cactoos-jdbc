package com.github.fabriciofx.cactoos.jdbc.cache.values;

public class NumericExpression implements Expression {

    public enum Operator{
        PLUS,
        MINUS,
        TIMES,
        DIV,
        MOD
    }

    private final Expression left;
    private final Expression right;
    private final Operator operator;

    public NumericExpression(
        final Expression left,
        final Expression right,
        final Operator operator
    ) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Value get(){
        Object l = left.get().asObject();
        Object r = right.get().asObject();

        if(l instanceof Integer && r instanceof Integer){
            return new IntValue(
                (int) evaluateExpr((Integer) l,(Integer) r)
            );
        }

        double ld = left.get().asDouble().get();
        double rd = right.get().asDouble().get();

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

}
