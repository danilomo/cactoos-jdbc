/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2018 Fabrício Barros Cabral
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
package com.github.fabriciofx.cactoos.jdbc.stmt;

import com.github.fabriciofx.cactoos.jdbc.H2Source;
import com.github.fabriciofx.cactoos.jdbc.Session;
import com.github.fabriciofx.cactoos.jdbc.SmartDataValues;
import com.github.fabriciofx.cactoos.jdbc.query.BatchQuery;
import com.github.fabriciofx.cactoos.jdbc.query.NamedQuery;
import com.github.fabriciofx.cactoos.jdbc.result.ResultAsValues;
import com.github.fabriciofx.cactoos.jdbc.result.ResultAsXml;
import com.github.fabriciofx.cactoos.jdbc.session.NoAuthSession;
import com.github.fabriciofx.cactoos.jdbc.value.BoolValue;
import com.github.fabriciofx.cactoos.jdbc.value.DateValue;
import com.github.fabriciofx.cactoos.jdbc.value.DecimalValue;
import com.github.fabriciofx.cactoos.jdbc.value.TextValue;
import com.jcabi.matchers.XhtmlMatchers;
import java.time.LocalDate;
import org.cactoos.text.JoinedText;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Select tests.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class SelectTest {
    @Test
    public void select() throws Exception {
        final Session session = new NoAuthSession(
            new H2Source("testdb")
        );
        new Update(
            session,
            new NamedQuery(
                new JoinedText(
                    "",
                    "CREATE TABLE employee (id UUID DEFAULT RANDOM_UUID(),",
                    "name VARCHAR(50), birthday DATE, address VARCHAR(100),",
                    "married BOOLEAN, salary DECIMAL(20,2))"
                ).asString()
            )
        ).result();
        new Batch(
            session,
            new BatchQuery(
                new JoinedText(
                    "",
                    "INSERT INTO employee ",
                    "(name, birthday, address, married, salary) ",
                    "VALUES (:name, :birthday, :address, :married, :salary)"
                ).asString(),
                new SmartDataValues(
                    new TextValue("name", "John Wick"),
                    new DateValue("birthday", "1980-08-16"),
                    new TextValue("address", "Boulevard Street, 34"),
                    new BoolValue("married", false),
                    new DecimalValue("salary", "13456.00")
                ),
                new SmartDataValues(
                    new TextValue("name", "Adam Park"),
                    new DateValue("birthday", "1985-07-10"),
                    new TextValue("address", "Sunset Place, 14"),
                    new BoolValue("married", true),
                    new DecimalValue("salary", "12345.00")
                )
            )
        ).result();
        MatcherAssert.assertThat(
            XhtmlMatchers.xhtml(
                new ResultAsXml(
                    new Select(
                        session,
                        new NamedQuery(
                            "SELECT * FROM employee"
                        )
                    ),
                    "employees",
                    "employee"
                ).value()
            ),
            XhtmlMatchers.hasXPaths(
                "/employees/employee/name[text()='John Wick']",
                "/employees/employee/birthday[text()='1980-08-16']",
                "/employees/employee/address[text()='Boulevard Street, 34']",
                "/employees/employee/married[text()='false']",
                "/employees/employee/salary[text()='13456.00']",
                "/employees/employee/name[text()='Adam Park']",
                "/employees/employee/birthday[text()='1985-07-10']",
                "/employees/employee/address[text()='Sunset Place, 14']",
                "/employees/employee/married[text()='true']",
                "/employees/employee/salary[text()='12345.00']"
            )
        );
    }

    @Test
    public void any() throws Exception {
        final Session session = new NoAuthSession(
            new H2Source("testdb")
        );
        new Update(
            session,
            new NamedQuery(
                new JoinedText(
                    "",
                    "CREATE TABLE person (id UUID DEFAULT RANDOM_UUID(),",
                    "name VARCHAR(30), created_at DATE, city VARCHAR(20),",
                    "working BOOLEAN, height DECIMAL(20,2))"
                ).asString()
            )
        ).result();
        new Batch(
            session,
            new BatchQuery(
                new JoinedText(
                    "",
                    "INSERT INTO person ",
                    "(name, created_at, city, working, height) ",
                    "VALUES (:name, :created_at, :city, :working, :height)"
                ).asString(),
                new SmartDataValues(
                    new TextValue("name", "Rob Pike"),
                    new DateValue("created_at", LocalDate.now()),
                    new TextValue("city", "San Francisco"),
                    new BoolValue("working", true),
                    new DecimalValue("height", "1.86")
                ),
                new SmartDataValues(
                    new TextValue("name", "Ana Pivot"),
                    new DateValue("created_at", LocalDate.now()),
                    new TextValue("city", "Washington"),
                    new BoolValue("working", false),
                    new DecimalValue("height", "1.62")
                )
            )
        ).result();
        MatcherAssert.assertThat(
            "Can't select a person name",
            new ResultAsValues<>(
                new Select(
                    session,
                    new NamedQuery(
                        "SELECT name FROM person"
                    )
                ),
                String.class
            ).value().get(0),
            Matchers.equalTo("Rob Pike")
        );
    }
}