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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class ResultSetImmutable implements ResultSet {
    private final List<List<Object>> rows;
    private final Map<String, Integer> columns;
    private final AtomicInteger pos;

    public ResultSetImmutable(final ResultSet rst) {
        this.pos = new AtomicInteger(-1);
        this.rows = new LinkedList<>();
        this.columns = new LinkedHashMap<>();
        try {
            final ResultSetMetaData rsmd = rst.getMetaData();
            final int cols = rsmd.getColumnCount();
            while (rst.next()) {
                final List<Object> fields = new ArrayList<>(cols);
                for (int idx = 1; idx <= cols; ++idx) {
                    fields.add(rst.getObject(idx));
                    this.columns.putIfAbsent(rsmd.getColumnName(idx), idx - 1);
                }
                this.rows.add(fields);
            }
        } catch (final Exception ex) {
            new RuntimeException(ex);
        }
    }

    @Override
    public boolean next() throws SQLException {
        return this.pos.getAndIncrement() < this.rows.size() - 1;
    }

    @Override
    public void close() throws SQLException {
        this.beforeFirst();
    }

    @Override
    public boolean wasNull() throws SQLException {
        throw new UnsupportedOperationException("#wasNull()");
    }

    @Override
    public String getString(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, String.class);
    }

    @Override
    public boolean getBoolean(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Boolean.class);
    }

    @Override
    public byte getByte(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Byte.class);
    }

    @Override
    public short getShort(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Short.class);
    }

    @Override
    public int getInt(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Integer.class);
    }

    @Override
    public long getLong(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Long.class);
    }

    @Override
    public float getFloat(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Float.class);
    }

    @Override
    public double getDouble(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Double.class);
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
        return this.getObject(columnIndex, BigDecimal.class);
    }

    @Override
    public byte[] getBytes(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, byte[].class);
    }

    @Override
    public Date getDate(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Date.class);
    }

    @Override
    public Time getTime(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Time.class);
    }

    @Override
    public Timestamp getTimestamp(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Timestamp.class);
    }

    @Override
    public InputStream getAsciiStream(
        final int columnIndex
    ) throws SQLException {
        return this.getObject(columnIndex, InputStream.class);
    }

    /**
     * Get a stream in Unicode.
     * @deprecated It not should be used
     * @param columnIndex Column index
     * @throws SQLException If fails
     */
    @Deprecated
    public InputStream getUnicodeStream(
        final int columnIndex
    ) throws SQLException {
        return this.getObject(columnIndex, InputStream.class);
    }

    @Override
    public InputStream getBinaryStream(
        final int columnIndex
    ) throws SQLException {
        return this.getObject(columnIndex, InputStream.class);
    }

    @Override
    public String getString(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, String.class);
    }

    @Override
    public boolean getBoolean(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Boolean.class);
    }

    @Override
    public byte getByte(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Byte.class);
    }

    @Override
    public short getShort(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Short.class);
    }

    @Override
    public int getInt(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Integer.class);
    }

    @Override
    public long getLong(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Long.class);
    }

    @Override
    public float getFloat(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Float.class);
    }

    @Override
    public double getDouble(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Double.class);
    }

    /**
     * Get a BigDecimal.
     * @deprecated It not should be used
     * @param columnLabel Column label
     * @param scale Scale
     * @throws SQLException If fails
     */
    @Deprecated
    public BigDecimal getBigDecimal(
        final String columnLabel,
        final int scale
    ) throws SQLException {
        return this.getObject(columnLabel, BigDecimal.class);
    }

    @Override
    public byte[] getBytes(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, byte[].class);
    }

    @Override
    public Date getDate(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Date.class);
    }

    @Override
    public Time getTime(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Time.class);
    }

    @Override
    public Timestamp getTimestamp(
        final String columnLabel
    ) throws SQLException {
        return this.getObject(columnLabel, Timestamp.class);
    }

    @Override
    public InputStream getAsciiStream(
        final String columnLabel
    ) throws SQLException {
        return this.getObject(columnLabel, InputStream.class);
    }

    /**
     * Get a stream in Unicode.
     * @deprecated It not should be used
     * @param columnLabel Column label
     * @throws SQLException If fails
     */
    @Deprecated
    public InputStream getUnicodeStream(
        final String columnLabel
    ) throws SQLException {
        return this.getObject(columnLabel, InputStream.class);
    }

    @Override
    public InputStream getBinaryStream(
        final String columnLabel
    ) throws SQLException {
        return this.getObject(columnLabel, InputStream.class);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new UnsupportedOperationException("#getWarnings()");
    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new UnsupportedOperationException("#clearWarnings()");
    }

    @Override
    public String getCursorName() throws SQLException {
        throw new UnsupportedOperationException("#getCursorName()");
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        final List<String> cols = new ArrayList<>();
        this.columns.forEach((k, v) -> cols.add(k));
        return new ResultSetMetadaImmutable(cols);
    }

    @Override
    public Object getObject(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Object.class);
    }

    @Override
    public Object getObject(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Object.class);
    }

    @Override
    public int findColumn(final String columnLabel) throws SQLException {
        return this.columns.get(columnLabel);
    }

    @Override
    public Reader getCharacterStream(
        final int columnIndex
    ) throws SQLException {
        return this.getObject(columnIndex, Reader.class);
    }

    @Override
    public Reader getCharacterStream(
        final String columnLabel
    ) throws SQLException {
        return this.getObject(columnLabel, Reader.class);
    }

    @Override
    public BigDecimal getBigDecimal(
        final int columnIndex
    ) throws SQLException {
        return this.getObject(columnIndex, BigDecimal.class);
    }

    @Override
    public BigDecimal getBigDecimal(
        final String columnLabel
    ) throws SQLException {
        return this.getObject(columnLabel, BigDecimal.class);
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
    public boolean absolute(final int row) throws SQLException {
        this.pos.set(row - 1);
        return true;
    }

    @Override
    public boolean relative(final int rows) throws SQLException {
        this.pos.set(this.pos.get() + rows - 1);
        return true;
    }

    @Override
    public boolean previous() throws SQLException {
        this.pos.decrementAndGet();
        return true;
    }

    @Override
    public void setFetchDirection(final int direction) throws SQLException {
        throw new UnsupportedOperationException("#setFetchDirection()");
    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new UnsupportedOperationException("#getFecthDirection()");
    }

    @Override
    public void setFetchSize(final int rows) throws SQLException {
        throw new UnsupportedOperationException("#setFetchSize()");
    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new UnsupportedOperationException("#getFetchSize()");
    }

    @Override
    public int getType() throws SQLException {
        throw new UnsupportedOperationException("#getType()");
    }

    @Override
    public int getConcurrency() throws SQLException {
        throw new UnsupportedOperationException("#getConcurrency()");
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        throw new UnsupportedOperationException("#rowUpdated()");
    }

    @Override
    public boolean rowInserted() throws SQLException {
        throw new UnsupportedOperationException("#rowInserted()");
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        throw new UnsupportedOperationException("#rowDeleted()");
    }

    @Override
    public void updateNull(final int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("#updateNull()");
    }

    @Override
    public void updateBoolean(
        final int columnIndex,
        final boolean x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBoolean()");
    }

    @Override
    public void updateByte(
        final int columnIndex,
        final byte x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateByte()");
    }

    @Override
    public void updateShort(
        final int columnIndex,
        final short x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateShort()");
    }

    @Override
    public void updateInt(
        final int columnIndex,
        final int x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateInt()");
    }

    @Override
    public void updateLong(
        final int columnIndex,
        final long x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateLong()");
    }

    @Override
    public void updateFloat(
        final int columnIndex,
        final float x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateFloat()");
    }

    @Override
    public void updateDouble(
        final int columnIndex,
        final double x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateDouble()");
    }

    @Override
    public void updateBigDecimal(
        final int columnIndex,
        final BigDecimal x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBigDecimal()");
    }

    @Override
    public void updateString(
        final int columnIndex,
        final String x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateString()");
    }

    @Override
    public void updateBytes(
        final int columnIndex,
        final byte[] x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBytes()");
    }

    @Override
    public void updateDate(
        final int columnIndex,
        final Date x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateDate()");
    }

    @Override
    public void updateTime(
        final int columnIndex,
        final Time x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateTime()");
    }

    @Override
    public void updateTimestamp(
        final int columnIndex,
        final Timestamp x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateTimestamp()");
    }

    @Override
    public void updateAsciiStream(
        final int columnIndex,
        final InputStream x,
        final int length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateAsciiStream()");
    }

    @Override
    public void updateBinaryStream(
        final int columnIndex,
        final InputStream x,
        final int length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBinaryStream()");
    }

    @Override
    public void updateCharacterStream(
        final int columnIndex,
        final Reader x,
        final int length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateCharacterStream()");
    }

    @Override
    public void updateObject(
        final int columnIndex,
        final Object x,
        final int scaleOrLength
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateObject()");
    }

    @Override
    public void updateObject(
        final int columnIndex,
        final Object x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateObject()");
    }

    @Override
    public void updateNull(final String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("#updateNull()");
    }

    @Override
    public void updateBoolean(
        final String columnLabel,
        final boolean x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBoolean()");
    }

    @Override
    public void updateByte(
        final String columnLabel,
        final byte x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateByte()");
    }

    @Override
    public void updateShort(
        final String columnLabel,
        final short x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateShort()");
    }

    @Override
    public void updateInt(
        final String columnLabel,
        final int x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateInt()");
    }

    @Override
    public void updateLong(
        final String columnLabel,
        final long x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateLong()");
    }

    @Override
    public void updateFloat(
        final String columnLabel,
        final float x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateFloat()");
    }

    @Override
    public void updateDouble(
        final String columnLabel,
        final double x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateDouble()");
    }

    @Override
    public void updateBigDecimal(
        final String columnLabel,
        final BigDecimal x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBigDecimal()");
    }

    @Override
    public void updateString(
        final String columnLabel,
        final String x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateString()");
    }

    @Override
    public void updateBytes(
        final String columnLabel,
        final byte[] x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBytes()");
    }

    @Override
    public void updateDate(
        final String columnLabel,
        final Date x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateDate()");
    }

    @Override
    public void updateTime(
        final String columnLabel,
        final Time x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateTime()");
    }

    @Override
    public void updateTimestamp(
        final String columnLabel,
        final Timestamp x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateTimestamp()");
    }

    @Override
    public void updateAsciiStream(
        final String columnLabel,
        final InputStream x,
        final int length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateAsciiStream()");
    }

    @Override
    public void updateBinaryStream(
        final String columnLabel,
        final InputStream x,
        final int length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBinaryStream()");
    }

    @Override
    public void updateCharacterStream(
        final String columnLabel,
        final Reader reader,
        final int length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateCharacterStream()");
    }

    @Override
    public void updateObject(
        final String columnLabel,
        final Object x,
        final int scaleOrLength
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateObject()");
    }

    @Override
    public void updateObject(
        final String columnLabel,
        final Object x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateObject()");
    }

    @Override
    public void insertRow() throws SQLException {
        throw new UnsupportedOperationException("#insertRow()");
    }

    @Override
    public void updateRow() throws SQLException {
        throw new UnsupportedOperationException("#updateRow()");
    }

    @Override
    public void deleteRow() throws SQLException {
        throw new UnsupportedOperationException("#deleteRow()");
    }

    @Override
    public void refreshRow() throws SQLException {
        throw new UnsupportedOperationException("#refreshRow()");
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        throw new UnsupportedOperationException("#cancelRowUpdates()");
    }

    @Override
    public void moveToInsertRow() throws SQLException {
        throw new UnsupportedOperationException("#moveToInsertRow()");
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        throw new UnsupportedOperationException("#moveToCurrentRow()");
    }

    @Override
    public Statement getStatement() throws SQLException {
        throw new UnsupportedOperationException("#getStatement()");
    }

    @Override
    public Object getObject(
        final int columnIndex,
        final Map<String, Class<?>> map
    ) throws SQLException {
        throw new UnsupportedOperationException("#getObject()");
    }

    @Override
    public Ref getRef(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Ref.class);
    }

    @Override
    public Blob getBlob(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Blob.class);
    }

    @Override
    public Clob getClob(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Clob.class);
    }

    @Override
    public Array getArray(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, Array.class);
    }

    @Override
    public Object getObject(
        final String columnLabel,
        final Map<String, Class<?>> map
    ) throws SQLException {
        throw new UnsupportedOperationException("#getObject()");
    }

    @Override
    public Ref getRef(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Ref.class);
    }

    @Override
    public Blob getBlob(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Blob.class);
    }

    @Override
    public Clob getClob(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Clob.class);
    }

    @Override
    public Array getArray(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, Array.class);
    }

    @Override
    public Date getDate(
        final int columnIndex,
        final Calendar cal
    ) throws SQLException {
        throw new UnsupportedOperationException("#getDate()");
    }

    @Override
    public Date getDate(
        final String columnLabel,
        final Calendar cal
    ) throws SQLException {
        throw new UnsupportedOperationException("#getDate()");
    }

    @Override
    public Time getTime(
        final int columnIndex,
        final Calendar cal
    ) throws SQLException {
        throw new UnsupportedOperationException("#getTime()");
    }

    @Override
    public Time getTime(
        final String columnLabel,
        final Calendar cal
    ) throws SQLException {
        throw new UnsupportedOperationException("#getTime()");
    }

    @Override
    public Timestamp getTimestamp(
        final int columnIndex,
        final Calendar cal
    ) throws SQLException {
        throw new UnsupportedOperationException("#getTimestamp()");
    }

    @Override
    public Timestamp getTimestamp(
        final String columnLabel,
        final Calendar cal
    ) throws SQLException {
        throw new UnsupportedOperationException("#getTimestamp()");
    }

    @Override
    public URL getURL(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, URL.class);
    }

    @Override
    public URL getURL(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, URL.class);
    }

    @Override
    public void updateRef(
        final int columnIndex,
        final Ref x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateRef()");
    }

    @Override
    public void updateRef(
        final String columnLabel,
        final Ref x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateRef()");
    }

    @Override
    public void updateBlob(
        final int columnIndex,
        final Blob x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBlob()");
    }

    @Override
    public void updateBlob(
        final String columnLabel,
        final Blob x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBlob()");
    }

    @Override
    public void updateClob(
        final int columnIndex,
        final Clob x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateClob()");
    }

    @Override
    public void updateClob(
        final String columnLabel,
        final Clob x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateClob()");
    }

    @Override
    public void updateArray(
        final int columnIndex,
        final Array x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateArray()");
    }

    @Override
    public void updateArray(
        final String columnLabel,
        final Array x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateArray()");
    }

    @Override
    public RowId getRowId(final int columnIndex) throws SQLException {
        throw new UnsupportedOperationException("#getRowId()");
    }

    @Override
    public RowId getRowId(final String columnLabel) throws SQLException {
        throw new UnsupportedOperationException("#getRowId()");
    }

    @Override
    public void updateRowId(
        final int columnIndex,
        final RowId x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateRowId()");
    }

    @Override
    public void updateRowId(
        final String columnLabel,
        final RowId x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateRowId()");
    }

    @Override
    public int getHoldability() throws SQLException {
        throw new UnsupportedOperationException("#getHoldability()");
    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }

    @Override
    public void updateNString(
        final int columnIndex,
        final String nString
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNString()");
    }

    @Override
    public void updateNString(
        final String columnLabel,
        final String nString
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNString()");
    }

    @Override
    public void updateNClob(
        final int columnIndex,
        final NClob nClob
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNClob()");
    }

    @Override
    public void updateNClob(
        final String columnLabel,
        final NClob nClob
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNClob()");
    }

    @Override
    public NClob getNClob(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, NClob.class);
    }

    @Override
    public NClob getNClob(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, NClob.class);
    }

    @Override
    public SQLXML getSQLXML(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, SQLXML.class);
    }

    @Override
    public SQLXML getSQLXML(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, SQLXML.class);
    }

    @Override
    public void updateSQLXML(
        final int columnIndex,
        final SQLXML xmlObject
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateSQLXML()");
    }

    @Override
    public void updateSQLXML(
        final String columnLabel,
        final SQLXML xmlObject
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateSQLXML()");
    }

    @Override
    public String getNString(final int columnIndex) throws SQLException {
        return this.getObject(columnIndex, String.class);
    }

    @Override
    public String getNString(final String columnLabel) throws SQLException {
        return this.getObject(columnLabel, String.class);
    }

    @Override
    public Reader getNCharacterStream(
        final int columnIndex
    ) throws SQLException {
        return this.getObject(columnIndex, Reader.class);
    }

    @Override
    public Reader getNCharacterStream(
        final String columnLabel
    ) throws SQLException {
        return this.getObject(columnLabel, Reader.class);
    }

    @Override
    public void updateNCharacterStream(
        final int columnIndex,
        final Reader x,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNCharacterStream()");
    }

    @Override
    public void updateNCharacterStream(
        final String columnLabel,
        final Reader reader,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNCharacterStream()");
    }

    @Override
    public void updateAsciiStream(
        final int columnIndex,
        final InputStream x,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateAsciiStream()");
    }

    @Override
    public void updateBinaryStream(
        final int columnIndex,
        final InputStream x,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBinaryStream()");
    }

    @Override
    public void updateCharacterStream(
        final int columnIndex,
        final Reader x,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateCharacterStream()");
    }

    @Override
    public void updateAsciiStream(
        final String columnLabel,
        final InputStream x,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateAsciiStream()");
    }

    @Override
    public void updateBinaryStream(
        final String columnLabel,
        final InputStream x,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBinaryStream()");
    }

    @Override
    public void updateCharacterStream(
        final String columnLabel,
        final Reader reader,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateCharacterStream()");
    }

    @Override
    public void updateBlob(
        final int columnIndex,
        final InputStream inputStream,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBlob()");
    }

    @Override
    public void updateBlob(
        final String columnLabel,
        final InputStream inputStream,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBlob()");
    }

    @Override
    public void updateClob(
        final int columnIndex,
        final Reader reader,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateClob()");
    }

    @Override
    public void updateClob(
        final String columnLabel,
        final Reader reader,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateClob()");
    }

    @Override
    public void updateNClob(
        final int columnIndex,
        final Reader reader,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNClob()");
    }

    @Override
    public void updateNClob(
        final String columnLabel,
        final Reader reader,
        final long length
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNClob()");
    }

    @Override
    public void updateNCharacterStream(
        final int columnIndex,
        final Reader x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNCharacterStream()");
    }

    @Override
    public void updateNCharacterStream(
        final String columnLabel,
        final Reader reader
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNCharacterStream()");
    }

    @Override
    public void updateAsciiStream(
        final int columnIndex,
        final InputStream x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateAsciiStream()");
    }

    @Override
    public void updateBinaryStream(
        final int columnIndex,
        final InputStream x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBinaryStream()");
    }

    @Override
    public void updateCharacterStream(
        final int columnIndex,
        final Reader x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateCharacterStream()");
    }

    @Override
    public void updateAsciiStream(
        final String columnLabel,
        final InputStream x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateAsciiStream()");
    }

    @Override
    public void updateBinaryStream(
        final String columnLabel,
        final InputStream x
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBinaryStream()");
    }

    @Override
    public void updateCharacterStream(
        final String columnLabel,
        final Reader reader
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateCharacterStream()");
    }

    @Override
    public void updateBlob(
        final int columnIndex,
        final InputStream inputStream
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBlob()");
    }

    @Override
    public void updateBlob(
        final String columnLabel,
        final InputStream inputStream
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateBlob()");
    }

    @Override
    public void updateClob(
        final int columnIndex,
        final Reader reader
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateClob()");
    }

    @Override
    public void updateClob(
        final String columnLabel,
        final Reader reader
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateClob()");
    }

    @Override
    public void updateNClob(
        final int columnIndex,
        final Reader reader
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNClob()");
    }

    @Override
    public void updateNClob(
        final String columnLabel,
        final Reader reader
    ) throws SQLException {
        throw new UnsupportedOperationException("#updateNClob()");
    }

    @Override
    public <T> T getObject(
        final int columnIndex,
        final Class<T> type
    ) throws SQLException {
        return type.cast(this.rows.get(this.pos.get()).get(columnIndex - 1));
    }

    @Override
    public <T> T getObject(
        final String columnLabel,
        final Class<T> type
    ) throws SQLException {
        return type.cast(
            this.rows.get(
                this.pos.get()
            ).get(
                this.columns.get(columnLabel)
            )
        );
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
