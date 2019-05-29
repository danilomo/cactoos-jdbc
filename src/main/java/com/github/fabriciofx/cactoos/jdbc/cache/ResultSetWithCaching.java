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

import com.github.fabriciofx.cactoos.jdbc.rset.ResultSetEnvelope;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class ResultSetWithCaching extends ResultSetEnvelope {
    private final List<Map<String, Object>> rows;
    private final SqlTokens tokens;
    private final AtomicInteger pos;

    public ResultSetWithCaching(final ResultSet rst, final SqlTokens tks) {
        super(rst);
        System.out.println("BEGIN");
        this.rows = new LinkedList<>();
        System.out.println("OIA1");
        try {
            while (rst.next()) {
                System.out.println("OIA2");
                final Map<String, Object> flds = new LinkedHashMap<>();
                for (final String name : tks.columns()) {
                    flds.put(name, rst.getObject(name));
                }
                System.out.println("OIA3");
                this.rows.add(flds);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("END");
        this.pos = new AtomicInteger(-1);
        this.tokens = tks;
    }

    @Override
    public boolean next() throws SQLException {
        final int count = this.rows.size() - 1;
        final int cur = this.pos.get();
        final boolean result;
        if (cur < count) {
            this.pos.incrementAndGet();
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public void close() throws SQLException {
        this.beforeFirst();
    }

    @Override
    public String getString(final int columnIndex) throws SQLException {
        return (String) this.getObject(columnIndex);
    }

    @Override
    public boolean getBoolean(final int columnIndex) throws SQLException {
        return (Boolean) this.getObject(columnIndex);
    }

    @Override
    public byte getByte(final int columnIndex) throws SQLException {
        return (Byte) this.getObject(columnIndex);
    }

    @Override
    public short getShort(final int columnIndex) throws SQLException {
        return (Short) this.getObject(columnIndex);
    }

    @Override
    public int getInt(final int columnIndex) throws SQLException {
        return (Integer) this.getObject(columnIndex);
    }

    @Override
    public long getLong(final int columnIndex) throws SQLException {
        return (Long) this.getObject(columnIndex);
    }

    @Override
    public float getFloat(final int columnIndex) throws SQLException {
        return (Float) this.getObject(columnIndex);
    }

    @Override
    public double getDouble(final int columnIndex) throws SQLException {
        return (Double) this.getObject(columnIndex);
    }

    /**
     * Get a BigDecimal.
     * @deprecated It not should be used
     * @param columnIndex Column index
     * @param scale Scale
     * @throws SQLException If fails
     */
    @Deprecated
    public BigDecimal getBigDecimal(
        final int columnIndex,
        final int scale
    ) throws SQLException {
        return (BigDecimal) this.getObject(columnIndex);
    }

    @Override
    public Date getDate(final int columnIndex) throws SQLException {
        return (Date) this.getObject(columnIndex);
    }

    @Override
    public Time getTime(final int columnIndex) throws SQLException {
        return (Time) this.getObject(columnIndex);
    }

    @Override
    public Timestamp getTimestamp(final int columnIndex) throws SQLException {
        return (Timestamp) this.getObject(columnIndex);
    }

    @Override
    public String getString(final String columnLabel) throws SQLException {
        return (String) this.getObject(columnLabel);
    }

    @Override
    public boolean getBoolean(final String columnLabel) throws SQLException {
        return (Boolean) this.getObject(columnLabel);
    }

    @Override
    public byte getByte(final String columnLabel) throws SQLException {
        return (Byte) this.getObject(columnLabel);
    }

    @Override
    public short getShort(final String columnLabel) throws SQLException {
        return (Short) this.getObject(columnLabel);
    }

    @Override
    public int getInt(final String columnLabel) throws SQLException {
        return (Integer) this.getObject(columnLabel);
    }

    @Override
    public long getLong(final String columnLabel) throws SQLException {
        return (Long) this.getObject(columnLabel);
    }

    @Override
    public float getFloat(final String columnLabel) throws SQLException {
        return (Float) this.getObject(columnLabel);
    }

    @Override
    public double getDouble(final String columnLabel) throws SQLException {
        return (Double) this.getObject(columnLabel);
    }

    /**
     * Get a BigDecimal.
     * @deprecated It not should be used
     * @param columnLabel Column label
     * @param scale Scale
     * @throws SQLException If fails
     */
    @Deprecated
    public BigDecimal getBigDecimal(final String columnLabel,
                                    final int scale) throws SQLException {
        return (BigDecimal) this.getObject(columnLabel);
    }

    @Override
    public Date getDate(final String columnLabel) throws SQLException {
        return (Date) this.getObject(columnLabel);
    }

    @Override
    public Time getTime(final String columnLabel) throws SQLException {
        return (Time) this.getObject(columnLabel);
    }

    @Override
    public Timestamp getTimestamp(
        final String columnLabel
    ) throws SQLException {
        return (Timestamp) this.getObject(columnLabel);
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return new ResultSetMetadaWithCaching(
            super.getMetaData(),
            this.tokens
        );
    }

    @Override
    public Object getObject(final int columnIndex) throws SQLException {
        final Map<String, Object> row = this.rows.get(this.pos.get());
        return row.values().stream().toArray()[columnIndex - 1];
    }

    @Override
    public Object getObject(final String columnLabel) throws SQLException {
        return this.getObject(this.findColumn(columnLabel));
    }

    @Override
    public int findColumn(final String columnLabel) throws SQLException {
        int column = 1;
        for (final String key : this.rows.get(0).keySet()) {
            if (key.equals(columnLabel)) {
                break;
            }
            column++;
        }
        return column;
    }

    @Override
    public BigDecimal getBigDecimal(final int columnIndex) throws SQLException {
        return (BigDecimal) this.getObject(columnIndex);
    }

    @Override
    public BigDecimal getBigDecimal(
        final String columnLabel
    ) throws SQLException {
        return (BigDecimal) this.getObject(columnLabel);
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return this.pos.get() < 0;
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return this.rows.size() >= this.pos.get();
    }

    @Override
    public boolean isFirst() throws SQLException {
        return this.pos.get() == 0;
    }

    @Override
    public boolean isLast() throws SQLException {
        return this.pos.get() == this.rows.size();
    }

    @Override
    public void beforeFirst() throws SQLException {
        this.pos.set(-1);
    }

    @Override
    public void afterLast() throws SQLException {
        this.pos.set(this.rows.size());
    }

    @Override
    public boolean first() throws SQLException {
        this.pos.set(0);
        return true;
    }

    @Override
    public boolean last() throws SQLException {
        this.pos.set(this.rows.size() - 1);
        return true;
    }

    @Override
    public int getRow() throws SQLException {
        return this.pos.get() + 1;
    }

    @Override
    public <T> T getObject(
        final int columnIndex,
        final Class<T> type
    ) throws SQLException {
        return type.cast(this.getObject(columnIndex));
    }

    @Override
    public <T> T getObject(
        final String columnLabel,
        final Class<T> type
    ) throws SQLException {
        return type.cast(this.getObject(columnLabel));
    }
}
