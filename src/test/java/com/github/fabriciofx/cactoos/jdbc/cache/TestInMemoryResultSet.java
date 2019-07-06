/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Fabricio Barros Cabral
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
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

import com.github.fabriciofx.cactoos.jdbc.cache.meta.IntColumn;
import com.github.fabriciofx.cactoos.jdbc.cache.meta.StringColumn;
import com.github.fabriciofx.cactoos.jdbc.cache.values.IntValue;
import com.github.fabriciofx.cactoos.jdbc.cache.values.StringValue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestInMemoryResultSet {

    private InMemoryResultSet resultSet;
    private Object[][] data;

    private void setupTest(){
        resultSet = new InMemoryResultSet(
            pair(1, "apple"),
            pair(2, "orange"),
            pair(3, "avocado"),
            pair(4, "banana")
        ).withMetadata(
            new IntColumn("key"),
            new StringColumn("value")
        );
        data = new Object[][]{
            {1, "apple"},
            {2, "orange"},
            {3, "avocado"},
            {4, "banana"}
        };
    }

    private Row pair(Integer key, String value){
        return new Row(
            new IntValue(key),
            new StringValue(value)
        );
    }

    private void assertPairAgainstData(int index, Integer key, String value){
        assertThat(
            key,
            is(data[index][0])
        );
        assertThat(
            value,
            is(data[index][1])
        );
    }

    @Test
    public void testIterator(){
        setupTest();
        int index = 0;
        for(Row row: resultSet){
            Integer key = row
                .cell("key").asInt().get();
            String value = row
                .cell("value").asString().get();
            assertPairAgainstData(index, key, value);
            index++;
        }
    }

    @Test
    public void testProceduralIteration() throws SQLException {
        setupTest();
        ResultSet rs = resultSet;
        int i = 0;
        while(rs.next()){
            Integer key = rs.getInt(1);
            String value = rs.getString(2);
            assertPairAgainstData(i, key, value);
            i++;
        }
    }

    @Test
    public void testCaptureMetadataErrors(){
        setupTest();
        resultSet.next();
        expectsException(
            () -> resultSet.getInt("value"),
            SQLException.class
        );
        expectsException(
            () -> resultSet.getString("key"),
            SQLException.class
        );
        expectsException(
            () -> resultSet.getInt("key1"),
            SQLException.class
        );
        expectsException(
            () -> resultSet.getDate("value1"),
            SQLException.class
        );
        expectsException(
            () -> resultSet.getLong("non_existing"),
            SQLException.class
        );
    }

    private static <T extends Exception> void expectsException(Callable<?> callable,
        Class<T> clazz) {
        try {
            callable.call();
        } catch (Exception ex) {
            assertThat(
                ex.getClass(),
                is((Object) clazz)
            );
        }
    }
}
