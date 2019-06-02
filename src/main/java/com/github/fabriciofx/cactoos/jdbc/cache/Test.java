package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.values.StringValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Value;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Test {
    public static void main(String[] args) throws SQLException{

        Function<String, Integer> mapping = str -> {
            if(str == "key"){
                return 1;
            }else if(str == "value"){
                return 2;
            }
            return -1;
        };

        List<Row> list = Arrays.asList(
            new Row(value("1"), value("pera")).withMapping(mapping),
            new Row(value("2"), value("uva")).withMapping(mapping),
            new Row(value("3"), value("maçã")).withMapping(mapping),
            new Row(value("4"), value("salada mista")).withMapping(mapping)
        );

        ResultSet rs = new ResultSetFromIterator(list.iterator());

        printResultSet(rs);
    }

    public static Value value(String str){
        return new StringValue(str);
    }

    private static void printResultSet(ResultSet rs) throws SQLException {
        int columnsNumber = 2;

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(" | ");
                System.out.print(rs.getString(i));
            }
            System.out.println("");
        }
    }
}
