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

import com.github.fabriciofx.cactoos.jdbc.connection.ConnectionEnvelope;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public final class ConnectionWithCaching extends ConnectionEnvelope {
    private final Map<String, ResultSet> cache;

    public ConnectionWithCaching(
        final Connection connection,
        final Map<String, ResultSet> cch
    ) {
        super(connection);
        this.cache = cch;
    }

    @Override
    public PreparedStatement prepareStatement(final String sql)
        throws SQLException {
        final PreparedStatement prepared;
        final SqlTokens tokens = new SqlTokens(sql);
        if (tokens.statement().equals("SELECT")) {
            System.out.println("Query: " + tokens.query());
            System.out.println("Cols: " + tokens.columns());
            System.out.println("Changed: " + tokens.changed());
            prepared = new PreparedStatementWithCaching(
                super.prepareStatement(tokens.changed()),
                this.cache,
                tokens
            );
        } else {
            prepared = super.prepareStatement(tokens.query());
        }
        return prepared;
    }
}
