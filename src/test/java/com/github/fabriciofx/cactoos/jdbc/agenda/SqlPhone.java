/**
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
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.fabriciofx.cactoos.jdbc.agenda;

import com.github.fabriciofx.cactoos.jdbc.Result;
import com.github.fabriciofx.cactoos.jdbc.Session;
import com.github.fabriciofx.cactoos.jdbc.adapter.ResultSetToType;
import com.github.fabriciofx.cactoos.jdbc.stmt.Select;
import com.github.fabriciofx.cactoos.jdbc.stmt.Update;
import com.github.fabriciofx.cactoos.jdbc.value.AnyValue;
import com.github.fabriciofx.cactoos.jdbc.value.IntValue;
import com.github.fabriciofx.cactoos.jdbc.value.TextValue;
import java.util.UUID;

/**
 * @author Fabricio Cabral (fabriciofx@gmail.com)
 * @version Id
 * @since
 */
public final class SqlPhone implements Phone {
    private final Session session;
    private final UUID contact;
    private final int seq;

    public SqlPhone(final Session sssn, final UUID contact, final int seq) {
        this.session = sssn;
        this.contact = contact;
        this.seq = seq;
    }

    @Override
    public String number() throws Exception {
        return new ResultSetToType<>(
            new Result<>(
                this.session,
                new Select(
                    "SELECT number FROM phone WHERE (contact = ?) AND (seq = ?)",
                    new AnyValue("contact", this.contact),
                    new IntValue("seq", this.seq)
                )
            ),
            String.class
        ).value();
    }

    @Override
    public String operator() throws Exception {
        return new ResultSetToType<>(
            new Result<>(
                this.session,
                new Select(
                    "SELECT operator FROM phone WHERE (contact = ?) AND (seq = ?)",
                    new AnyValue("contact", this.contact),
                    new IntValue("seq", this.seq)
                )
            ),
            String.class
        ).value();
    }

    @Override
    public void delete() throws Exception {
        new Result<>(
            this.session,
            new Update(
                "DELETE FROM phone WHERE (contact = ?) AND (seq = ?)",
                new AnyValue("contact", this.contact),
                new IntValue("seq", this.seq)
            )
        ).value();
    }

    @Override
    public void change(
        final String number,
        final String operator
    ) throws Exception {
        new Result<>(
            this.session,
            new Update(
                "UPDATE phone SET number = '?', operator = '?' WHERE (contact = ?) AND (seq = ?)",
                new TextValue("number", number),
                new TextValue("operator", operator),
                new AnyValue("contact", this.contact),
                new IntValue("seq", this.seq)
            )
        ).value();
    }
}