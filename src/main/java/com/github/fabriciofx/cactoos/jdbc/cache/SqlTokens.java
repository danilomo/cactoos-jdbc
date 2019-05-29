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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.cactoos.list.ListOf;
import org.cactoos.text.Joined;
import org.cactoos.text.UncheckedText;

public final class SqlTokens {
    private final UncheckedText query;

    public SqlTokens(final String sql) {
        this.query = new UncheckedText(
            () -> sql.trim()
        );
    }

    public String query() {
        return this.query.asString();
    }

    public String statement() {
        return this.query.asString().split("[\\s+]", 2)[0];
    }

    public List<String> columns() {
        final Pattern p = Pattern.compile(Pattern.quote("SELECT") +
            "(.*?)" + Pattern.quote("FROM"));
        final Matcher m = p.matcher(this.query.asString());
        String cols = "";
        while (m.find()) {
            cols = m.group(1);
        }
        return new ListOf<>(cols.replaceAll("\\s+", "").split(","));
    }

    public String changed() {
        final String cols = new UncheckedText(
            new Joined(
                ", ",
                this.columns()
            )
        ).asString();
        return this.query.asString().replaceFirst(cols, "*");
    }
}
