/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2019 Fabrício Barros Cabral
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.Params;
import com.github.fabriciofx.cactoos.jdbc.Session;
import com.github.fabriciofx.cactoos.jdbc.param.ParamBool;
import com.github.fabriciofx.cactoos.jdbc.param.ParamDate;
import com.github.fabriciofx.cactoos.jdbc.param.ParamDecimal;
import com.github.fabriciofx.cactoos.jdbc.param.ParamInt;
import com.github.fabriciofx.cactoos.jdbc.param.ParamText;
import com.github.fabriciofx.cactoos.jdbc.query.QueryBatch;
import com.github.fabriciofx.cactoos.jdbc.query.QuerySimple;
import com.github.fabriciofx.cactoos.jdbc.session.Logged;
import com.github.fabriciofx.cactoos.jdbc.source.SourceH2;
import com.github.fabriciofx.cactoos.jdbc.statement.StatementBatch;
import com.github.fabriciofx.cactoos.jdbc.statement.StatementSelect;
import com.github.fabriciofx.cactoos.jdbc.statement.StatementUpdate;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.Duration;
import java.time.Instant;
import org.cactoos.text.Joined;
import org.junit.Test;

public final class SqlCacheTest {
    @Test
    public void select() throws Exception {
        final Session session = new Logged(
            new SessionWithCaching(
                new SessionForDataSource(
                    new SourceH2("testdb")
                )
            ),
            "caching"
        );
//        final Server pgsql = new ServerPsql();
//        pgsql.start();
//        final Session session = new SessionWithLogging(
//            new SessionWithCaching(
//                pgsql.session()
//            ),
//            "caching"
//        );
//        final Session session = new SessionWithCaching(
//            pgsql.session()
//        );
//        final Session session = pgsql.session();
        new StatementUpdate(
            session,
            new QuerySimple(
                new Joined(
                    " ",
                    "CREATE TABLE employee (id INT,",
                    "name VARCHAR(50), birthday DATE,",
                    "address VARCHAR(100),",
                    "married BOOLEAN, salary DECIMAL(20,2),",
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
        // https://github.com/amanteaux/jooq-cache
        new StatementSelect(
            session,
            new QuerySimple(
                "SELECT id FROM employee WHERE name = 'Adam Park'"
            )
        ).result();
        final Instant start = Instant.now();
        for (int count = 0; count < 10; count++) {
            final ResultSet rset = new StatementSelect(
                session,
                new QuerySimple(
                    "SELECT id FROM employee WHERE name = 'Adam Park'"
                )
            ).result();
            final ResultSetMetaData rsmd = rset.getMetaData();
            final int cols = rsmd.getColumnCount();
            System.out.println("Num. Columns: " + cols);
            while (rset.next()) {
                for (int idx = 1; idx <= cols; ++idx) {
                    final String name = rsmd.getColumnName(idx);
                    final Object value = rset.getObject(idx);
                    System.out.println(
                        String.format(
                            "Column Name: %s, Value: %s",
                            name,
                            value.toString()
                        )
                    );
                }
            }
            rset.close();
        }
        final Instant end = Instant.now();
        final long millis = Duration.between(start, end).toMillis();
        System.out.println("Total time: " + millis + " ms");
//        pgsql.stop();
    }
}
