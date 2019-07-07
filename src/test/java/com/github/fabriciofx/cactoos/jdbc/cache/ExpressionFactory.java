package com.github.fabriciofx.cactoos.jdbc.cache;

import static com.github.fabriciofx.cactoos.jdbc.cache.values.NumericExpression.Operator.DIV;
import static com.github.fabriciofx.cactoos.jdbc.cache.values.NumericExpression.Operator.MINUS;
import static com.github.fabriciofx.cactoos.jdbc.cache.values.NumericExpression.Operator.PLUS;
import static com.github.fabriciofx.cactoos.jdbc.cache.values.NumericExpression.Operator.TIMES;

import com.github.fabriciofx.cactoos.jdbc.cache.values.BooleanExpression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.ComparisonExpression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.DoubleValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.IntValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.NumericExpression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.StringValue;

public class ExpressionFactory {
    static Expression add(Expression v1, Expression v2) {
        return new NumericExpression(v1, v2, PLUS);
    }

    static Expression minus(Expression v1, Expression v2) {
        return new NumericExpression(v1, v2, MINUS);
    }

    static Expression times(Expression v1, Expression v2) {
        return new NumericExpression(v1, v2, TIMES);
    }

    static Expression div(Expression v1, Expression v2) {
        return new NumericExpression(v1, v2, DIV);
    }

    static Expression integer(int i) {
        return () -> new IntValue(i);
    }

    static Expression floating(double i) {
        return () -> new DoubleValue(i);
    }

    static Expression string(String str){
        return () -> new StringValue(str);
    }

    static BooleanExpression eq(Expression v1, Expression v2){
        return new ComparisonExpression(v1, v2, ComparisonExpression.Operator.EQ);
    }

    static BooleanExpression neq(Expression v1, Expression v2){
        return new ComparisonExpression(v1, v2, ComparisonExpression.Operator.NEQ);
    }

    static BooleanExpression gt(Expression v1, Expression v2){
        return new ComparisonExpression(v1, v2, ComparisonExpression.Operator.GT);
    }

    static BooleanExpression lt(Expression v1, Expression v2){
        return new ComparisonExpression(v1, v2, ComparisonExpression.Operator.LT);
    }

    static BooleanExpression ge(Expression v1, Expression v2){
        return new ComparisonExpression(v1, v2, ComparisonExpression.Operator.GE);
    }

    static BooleanExpression le(Expression v1, Expression v2){
        return new ComparisonExpression(v1, v2, ComparisonExpression.Operator.LE);
    }
}
