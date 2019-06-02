package com.github.fabriciofx.cactoos.jdbc.cache.meta;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Metadata implements ResultSetMetaData, Function<String, Integer> {

    private final Map<String, Integer> columnMapping;
    private final List<Column> columns;

    public Metadata(List<Column> columns){
        this.columnMapping = initColumnMapping(columns);
        this.columns = columns;
    }

    public Metadata(Column ...columns){
        this(Arrays.asList(columns));
    }

    @Override
    public Integer apply(String s) {
        return columnMapping.getOrDefault(s, -1);
    }

    private Map<String, Integer> initColumnMapping(List<Column> columns) {
        Map<String, Integer> mapping = new HashMap<>();
        for(int i = 1; i <= columns.size(); i++){
            mapping.put(columns.get(i-1).name(), i);
        }
        return mapping;
    }

    @Override
    public int getColumnCount() throws SQLException {
        return columns.size();
    }

    @Override
    public boolean isAutoIncrement(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isCaseSensitive(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isSearchable(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isCurrency(int i) throws SQLException {
        return false;
    }

    @Override
    public int isNullable(int i) throws SQLException {
        return 0;
    }

    @Override
    public boolean isSigned(int i) throws SQLException {
        return false;
    }

    @Override
    public int getColumnDisplaySize(int i) throws SQLException {
        return 0;
    }

    public Column columnAt(int i) throws SQLException{
        if(i < columns.size()){
            throw new SQLException("Column index outside bounds");
        }

        return columns.get(i - 1);
    }

    @Override
    public String getColumnLabel(int i) throws SQLException {
        return columnAt(i).label();
    }

    @Override
    public String getColumnName(int i) throws SQLException {
        return columnAt(i).name();
    }

    @Override
    public String getSchemaName(int i) throws SQLException {
        return columnAt(i).name();
    }

    @Override
    public int getPrecision(int i) throws SQLException {
        return 0;
    }

    @Override
    public int getScale(int i) throws SQLException {
        return 0;
    }

    @Override
    public String getTableName(int i) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public String getCatalogName(int i) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public int getColumnType(int i) throws SQLException {
        return columnAt(i).type();
    }

    @Override
    public String getColumnTypeName(int i) throws SQLException {
        return columnAt(i).typeName();
    }

    @Override
    public boolean isReadOnly(int i) throws SQLException {
        return true;
    }

    @Override
    public boolean isWritable(int i) throws SQLException {
        return false;
    }

    @Override
    public boolean isDefinitelyWritable(int i) throws SQLException {
        return false;
    }

    @Override
    public String getColumnClassName(int i) throws SQLException {
        return columnAt(i).className();
    }

    @Override
    public <T> T unwrap(Class<T> aClass) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return false;
    }
}
