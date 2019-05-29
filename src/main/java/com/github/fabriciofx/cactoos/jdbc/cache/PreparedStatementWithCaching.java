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

import com.github.fabriciofx.cactoos.jdbc.prepared.PreparedStatementEnvelope;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public final class PreparedStatementWithCaching extends
    PreparedStatementEnvelope {
    private final Map<String, ResultSet> cache;
    private final SqlTokens tokens;

    public PreparedStatementWithCaching(
        final PreparedStatement prepared,
        final Map<String, ResultSet> cch,
        final SqlTokens tks
    ) {
        super(prepared);
        this.cache = cch;
        this.tokens = tks;
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        if (!this.cache.containsKey(this.tokens.changed())) {
            System.out.println("Query: " + this.tokens.query() + " not found! Caching it...");

//            final ResultSet rset = new ResultSetImmutable(super.executeQuery());
//            this.cache.put(this.tokens.changed(), rset);
//            return rset;

            final RowSetFactory rsf = RowSetProvider.newFactory();
            final CachedRowSet crs = rsf.createCachedRowSet();
            try (ResultSet rset = super.executeQuery()) {
                crs.populate(rset);
            }
            this.cache.put(this.tokens.changed(), crs);
            return crs;

        } else {
            System.out.println("Query: " + this.tokens.query() + " found! Returning...");
            final ResultSet cached = this.cache.get(this.tokens.changed());
            cached.beforeFirst();
            return new ResultSetWithCaching(cached, this.tokens);
        }
    }

    @Override
    public ResultSet executeQuery(final String sql) throws SQLException {
        final ResultSet rset;
        if (this.cache.containsKey(sql)) {
            rset = this.cache.get(sql);
        } else {
            rset = super.executeQuery(sql);
            this.cache.put(sql, rset);
        }
        return rset;
    }
}
