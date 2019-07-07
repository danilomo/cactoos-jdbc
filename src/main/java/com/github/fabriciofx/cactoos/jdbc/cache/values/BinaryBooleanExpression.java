package com.github.fabriciofx.cactoos.jdbc.cache.values;

public class BinaryBooleanExpression implements BooleanExpression{
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

    public enum Operator{
        AND,
        OR
    }

    @Override
    public Boolean get() {
        switch(operator){
            case AND:
                if(!left.evaluate()){
                    return false;
                }

                return right.evaluate();
            case OR:
                if(left.evaluate()){
                    return true;
                }
                return right.evaluate();
        }

        return false;
    }
}
