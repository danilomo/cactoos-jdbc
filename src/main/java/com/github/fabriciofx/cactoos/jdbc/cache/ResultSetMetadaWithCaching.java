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

import com.github.fabriciofx.cactoos.jdbc.sql.SqlTokens;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public final class ResultSetMetadaWithCaching extends ResultSetMetadaEnvelope {
    private final SqlTokens tokens;

    public ResultSetMetadaWithCaching(
        final ResultSetMetaData rsmd,
        final SqlTokens tks
    ) {
        super(rsmd);
        this.tokens = tks;
    }

    @Override
    public int getColumnCount() throws SQLException {
        return this.tokens.columns().size();
    }

    @Override
    public int getColumnDisplaySize(final int column) throws SQLException {
        return this.tokens.columns().get(column -1).length();
    }

    @Override
    public String getColumnLabel(final int column) throws SQLException {
        return this.tokens.columns().get(column -1);
    }

    @Override
    public String getColumnName(final int column) throws SQLException {
        return this.tokens.columns().get(column -1);
    }
}
