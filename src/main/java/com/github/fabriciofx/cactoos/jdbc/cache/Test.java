package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.Params;
import com.github.fabriciofx.cactoos.jdbc.Session;
import com.github.fabriciofx.cactoos.jdbc.cache.values.StringValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Value;
import com.github.fabriciofx.cactoos.jdbc.param.ParamBool;
import com.github.fabriciofx.cactoos.jdbc.param.ParamDate;
import com.github.fabriciofx.cactoos.jdbc.param.ParamDecimal;
import com.github.fabriciofx.cactoos.jdbc.param.ParamInt;
import com.github.fabriciofx.cactoos.jdbc.param.ParamText;
import com.github.fabriciofx.cactoos.jdbc.query.QueryBatch;
import com.github.fabriciofx.cactoos.jdbc.query.QuerySimple;
import com.github.fabriciofx.cactoos.jdbc.statement.StatementBatch;
import com.github.fabriciofx.cactoos.jdbc.statement.StatementSelect;
import com.github.fabriciofx.cactoos.jdbc.statement.StatementUpdate;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.cactoos.text.Joined;

public class Test {
    public static void main(String[] args) throws Exception {
        ResultSet rs = rs();
        Stream<Row> rows = StreamSupport.stream(
            new ResultSetIterable(rs).spliterator(),
            false
        );
        System.out.println(rows.collect(Collectors.toList()));
    }

    public static Value value(String str) {
        return new StringValue(str);
    }

    public static ResultSet rs() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        Session session = () -> DriverManager.getConnection(
            "jdbc:hsqldb:mem:employees",
            "abc",
            "abc"
        );
        new StatementUpdate(
            session,
            new QuerySimple(
                new Joined(
                    " ",
                    "CREATE TABLE employee (id INT,",
                    "name VARCHAR(50), birthday DATE,",
                    "address VARCHAR(100),",
                    "married BOOLEAN, salary FLOAT,",
                    "PRIMARY KEY (id))"
                )
            )
        ).result();
        new StatementBatch(
            session,
            new QueryBatch(
                new Joined(
                    " ",
                    "INSERT INTO employee",
                    "(id, name, birthday, address, married, salary)",
                    "VALUES (:id, :name, :birthday, :address,",
                    ":married, :salary)"
                ),
                new Params(
                    new ParamInt("id", 1),
                    new ParamText("name", "John Wick"),
                    new ParamDate("birthday", "1980-08-15"),
                    new ParamText("address", "Boulevard Street, 34"),
                    new ParamBool("married", false),
                    new ParamDecimal("salary", "13456.00")
                ),
                new Params(
                    new ParamInt("id", 2),
                    new ParamText("name", "Adam Park"),
                    new ParamDate("birthday", "1985-07-09"),
                    new ParamText("address", "Sunset Place, 14"),
                    new ParamBool("married", true),
                    new ParamDecimal("salary", "12345.00")
                )
            )
        ).result();
        return new StatementSelect(
            session,
            new QuerySimple(
                "select * from employee"
            )
        ).result();
    }
}
