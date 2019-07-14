package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.meta.IntColumn;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.IntValue;
import static com.github.fabriciofx.cactoos.jdbc.cache.ExpressionFactory.*;

import com.github.fabriciofx.cactoos.jdbc.cache.values.Variable;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class TestSelect {

    private InMemoryResultSet resultSet;

    private void setupTest(){
        resultSet = new InMemoryResultSet(
            row(1, 3, 7),
            row(2, 4, 6),
            row(3, 7, 9),
            row(4, 6, 8)
        ).withMetadata(
            new IntColumn("column1"),
            new IntColumn("column2"),
            new IntColumn("column3")
        );
    }

    private Row row(Integer column1, Integer column2, Integer column3 ){
        return new Row(
            new IntValue(column1),
            new IntValue(column2),
            new IntValue(column3)
        );
    }

    @Test
    public void testProjection(){
        setupTest();
        Expression exp1 = times(
            new Variable("column1"),
            () -> new IntValue(10)
        );
        Expression exp2 = times(
            new Variable("column1"),
            new Variable("column2")
        );
        Expression exp3 = new Variable("column3");
        List<Expression> expressions = Arrays.asList(
            exp1,
            exp2,
            exp3
        );
        Select pj = new Select(
            resultSet,
            expressions
        );
        for(Row row: pj){
            System.out.println(row);
        }
    }
}
