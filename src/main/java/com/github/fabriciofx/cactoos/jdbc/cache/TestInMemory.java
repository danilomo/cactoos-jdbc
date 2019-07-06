package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.meta.IntColumn;
import com.github.fabriciofx.cactoos.jdbc.cache.meta.Metadata;
import com.github.fabriciofx.cactoos.jdbc.cache.meta.StringColumn;
import com.github.fabriciofx.cactoos.jdbc.cache.values.IntValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.StringValue;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class TestInMemory {
    public static void main(String[] args) throws SQLException {
        Metadata metadata = new Metadata(
            new IntColumn("key"),
            new StringColumn("value")
        );
        ResultSet rs = new InMemoryResultSet(
            Arrays.asList(
                row(1, "pera").withMapping(metadata),
                row(2, "uva").withMapping(metadata),
                row(2, "salada mista").withMapping(metadata)
            ),
            metadata
        );

        System.out.println("\n\n\n\n");

        Stream<Row> rows = StreamSupport.stream(
            new ResultSetIterable(rs).spliterator(),
            false
        );
        System.out.println(
            rows.collect(
                Collectors.toMap(
                    x -> x.cell("key"),
                    x -> x.cell("value")
                )
            ));
    }

    public static Row row(int key, String val) {
        return new Row(
            new IntValue(key), new StringValue(val)
        );
    }

    private static void printResultSet(ResultSet rs) throws SQLException {
        // Prepare metadata object and get the number of columns.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        // Print column names (a header).
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) System.out.print(" | ");
            System.out.print(rsmd.getColumnName(i));
        }
        System.out.println("");
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(" | ");
                System.out.print(rs.getString(i));
            }
            System.out.println("");
        }
    }
}
