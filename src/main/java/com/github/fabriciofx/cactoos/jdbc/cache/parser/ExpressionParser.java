package com.github.fabriciofx.cactoos.jdbc.cache.parser;

import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteParser;
import com.github.fabriciofx.cactoos.jdbc.cache.values.DoubleValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.IntValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.NumericExpression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.StringValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Variable;
import java.util.Optional;

class ExpressionParser {

    Expression parseExpression(SQLiteParser.ExprContext ctx) {
        if(ctx.literal_value() != null){
            return parseLiteral(ctx.literal_value());
        }
        if(ctx.column_name() != null){
            return parseColumnName(ctx);
        }
        Optional<NumericExpression.Operator> operator = getBinaryOperator(
            ctx
        );
        return operator.
            map( op -> parseBinaryExpression(ctx, op))
            .orElseThrow( () -> new RuntimeException("Unsuported!"));
    }

    private Expression parseBinaryExpression(SQLiteParser.ExprContext ctx,
                                             NumericExpression.Operator op) {
        Expression expLeft = parseExpression(ctx.expr(0));
        Expression expRight = parseExpression(ctx.expr(1));
        return new NumericExpression(
            expLeft,
            expRight,
            op
        );
    }

    private Optional<NumericExpression.Operator> getBinaryOperator(
        SQLiteParser.ExprContext ctx
    ) {
        if(ctx.PLUS() != null){
            return Optional.of(NumericExpression.Operator.PLUS);
        }
        if(ctx.MINUS() != null){
            return Optional.of(NumericExpression.Operator.MINUS);
        }
        if(ctx.STAR() != null){
            return Optional.of(NumericExpression.Operator.TIMES);
        }
        if(ctx.DIV() != null){
            return Optional.of(NumericExpression.Operator.DIV);
        }
        return Optional.empty();
    }

    private Expression parseColumnName(final SQLiteParser.ExprContext ctx) {
        return new Variable(ctx.getText());
    }

    private Expression parseLiteral(final SQLiteParser.Literal_valueContext val) {
        if(val.STRING_LITERAL() != null){
            return () -> new StringValue(val.getText());
        }
        if(val.NUMERIC_LITERAL() != null){
            String numLiteral = val.getText();
            if(numLiteral.contains(".")){
                return () -> new DoubleValue(Double.parseDouble(numLiteral));
            }
            return () -> new IntValue(Integer.parseInt(numLiteral));
        }

        throw new RuntimeException("Unsupported literal.");
    }
}
