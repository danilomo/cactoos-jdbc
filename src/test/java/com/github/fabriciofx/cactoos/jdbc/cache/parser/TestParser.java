package com.github.fabriciofx.cactoos.jdbc.cache.parser;

import com.github.fabriciofx.cactoos.jdbc.cache.InMemoryResultSet;
import com.github.fabriciofx.cactoos.jdbc.cache.Row;
import com.github.fabriciofx.cactoos.jdbc.cache.Select;
import com.github.fabriciofx.cactoos.jdbc.cache.meta.IntColumn;
import com.github.fabriciofx.cactoos.jdbc.cache.values.BooleanExpression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.IntValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Variable;
import static com.github.fabriciofx.cactoos.jdbc.cache.ExpressionFactory.*;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestParser {

    private InMemoryResultSet resultSet;

    private void setupTest() {
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

    private Row row(Integer column1, Integer column2, Integer column3) {
        return new Row(
            new IntValue(column1),
            new IntValue(column2),
            new IntValue(column3)
        );
    }

    @Test
    public void testProjection() {
        setupTest();
        String query = "select column1*10, column1*column2, column3 " +
            "from my_table";
        Select pj = new ParsedSql(query)
            .select()
            .withRows(this.resultSet);
        assertThat(
            pj.asList(),
            is(Arrays.asList(
                row(10, 3, 7),
                row(20, 8, 6),
                row(30, 21, 9),
                row(40, 24, 8)
            ))
        );
    }
}
