package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.values.Value;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

class RowSupplier implements Supplier<Row> {
    private final ResultSet resultSet;
    private final Function<String, Integer> mapping;

    public RowSupplier(ResultSet resultSet) {
        this.resultSet = resultSet;
        this.mapping = mappingFromResultSet(resultSet);
    }

    private Function<String, Integer> mappingFromResultSet(ResultSet resultSet) {
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            Map<String, Integer> map = new HashMap<>();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                map.put(rsmd.getColumnName(i), i);
            }
            return str -> map.getOrDefault(str, -1);
        } catch (Exception ex) {
            return str -> -1;
        }
    }

    @Override
    public Row get() {
        List<Value> list = new ArrayList<>();
        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                list.add(Value.of(resultSet.getObject(i)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return new Row(list, mapping);
    }
}
