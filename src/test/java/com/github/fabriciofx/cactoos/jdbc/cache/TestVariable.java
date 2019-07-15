package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.meta.IntColumn;
import com.github.fabriciofx.cactoos.jdbc.cache.meta.StringColumn;
import com.github.fabriciofx.cactoos.jdbc.cache.values.IntValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.StringValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Variable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestVariable {

    private InMemoryResultSet resultSet;

    private void setupTest() {
        Iterable<Row> iterable = () -> Stream.of(
            Arrays.asList("1", "Canada", "Ottawa"),
            Arrays.asList("2", "Brazil", "Brasilia"),
            Arrays.asList("3", "Germany", "Berlin"),
            Arrays.asList("4", "Japan", "Tokyo")
        ).map(this::listToRow).iterator();
        resultSet = new InMemoryResultSet(
            iterable
        ).withMetadata(
            new IntColumn("key"),
            new StringColumn("country"),
            new StringColumn("capital")
        );
    }

    private Row listToRow(List<String> list) {
        return new Row(
            new IntValue(Integer.parseInt(list.get(0))),
            new StringValue(list.get(1)),
            new StringValue(list.get(2))
        );
    }

    @Test
    public void testCountryVariable() throws SQLException {
        setupTest();
        Iterator<Row> iterator = resultSet.iterator();
        Supplier<Row> supplier = () -> iterator.next();
        Variable countryVal = new Variable(
            "country",
            supplier
        );
        while (resultSet.next()) {
            String country = resultSet.getString(2);
            assertThat(
                country,
                is(countryVal.get().asString().get())
            );
        }
    }

    @Test
    public void testCapitalVariable() throws SQLException {
        setupTest();
        Iterator<Row> iterator = resultSet.iterator();
        Supplier<Row> supplier = () -> iterator.next();
        Variable capitalVal = new Variable(
            "capital",
            supplier
        );
        while (resultSet.next()) {
            String capital = resultSet.getString(3);
            assertThat(
                capital,
                is(capitalVal.get().asString().get())
            );
        }
    }

    @Test
    public void testKeyVariable() throws SQLException {
        setupTest();
        Iterator<Row> iterator = resultSet.iterator();
        Supplier<Row> supplier = () -> iterator.next();
        Variable keyVal = new Variable(
            "key",
            supplier
        );
        while (resultSet.next()) {
            int key = resultSet.getInt(1);
            assertThat(
                key,
                is(keyVal.get().asInt().get())
            );
        }
    }
}
