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

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public final class ResultSetMetadaImmutable implements ResultSetMetaData {
    private final List<String> columns;

    public ResultSetMetadaImmutable(final List<String> cols) {
        this.columns = cols;
    }

    @Override
    public int getColumnCount() throws SQLException {
        return this.columns.size();
    }

    @Override
    public boolean isAutoIncrement(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isAutoIncrement()");
    }

    @Override
    public boolean isCaseSensitive(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isCaseSensitive()");
    }

    @Override
    public boolean isSearchable(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isSearchable()");
    }

    @Override
    public boolean isCurrency(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isCurrency()");
    }

    @Override
    public int isNullable(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isNullable()");
    }

    @Override
    public boolean isSigned(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isSigned()");
    }

    @Override
    public int getColumnDisplaySize(final int column) throws SQLException {
        return this.columns.get(column - 1).length();
    }

    @Override
    public String getColumnLabel(final int column) throws SQLException {
        return this.columns.get(column - 1);
    }

    @Override
    public String getColumnName(final int column) throws SQLException {
        return this.columns.get(column - 1);
    }

    @Override
    public String getSchemaName(final int column) throws SQLException {
        throw new UnsupportedOperationException("#getSchemaName()");
    }

    @Override
    public int getPrecision(final int column) throws SQLException {
        throw new UnsupportedOperationException("#getPrecision()");
    }

    @Override
    public int getScale(final int column) throws SQLException {
        throw new UnsupportedOperationException("#getScale()");
    }

    @Override
    public String getTableName(final int column) throws SQLException {
        throw new UnsupportedOperationException("#getTableName()");
    }

    @Override
    public String getCatalogName(final int column) throws SQLException {
        throw new UnsupportedOperationException("#getCatalogName()");
    }

    @Override
    public int getColumnType(final int column) throws SQLException {
        throw new UnsupportedOperationException("#getColumnType()");
    }

    @Override
    public String getColumnTypeName(final int column) throws SQLException {
        throw new UnsupportedOperationException("#getColumnTypeName()");
    }

    @Override
    public boolean isReadOnly(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isReadOnly()");
    }

    @Override
    public boolean isWritable(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isWritable()");
    }

    @Override
    public boolean isDefinitelyWritable(final int column) throws SQLException {
        throw new UnsupportedOperationException("#isDefinitelyWritable()");
    }

    @Override
    public String getColumnClassName(final int column) throws SQLException {
        throw new UnsupportedOperationException("#getColumnClassName()");
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        throw new UnsupportedOperationException("#unwrap()");
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        throw new UnsupportedOperationException("#isWrapperFor()");
    }
}
