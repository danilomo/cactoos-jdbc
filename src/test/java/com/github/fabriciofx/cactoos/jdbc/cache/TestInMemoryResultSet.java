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
import java.util.Optional;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestInMemoryResultSet {
    @Test
    public void testIterator(){
        InMemoryResultSet resultSet = new InMemoryResultSet(
            pair(1, "apple"),
            pair(2, "orange"),
            pair(3, "avocado"),
            pair(4, "banana")
        ).withMetadata(
            new IntColumn("key"),
            new StringColumn("value")
        );
        Object[][] array = {
            {1, "apple"},
            {2, "orange"},
            {3, "avocado"},
            {4, "banana"}
        };
        int index = 0;
        for(Row row: resultSet){
            Integer key = row
                .cell("key").asInt().get();
            String value = row
                .cell("value").asString().get();
            assertThat(
                key,
                is(array[index][0])
            );
            assertThat(
                value,
                is(array[index][1])
            );
            index++;
        }
    }

    public Row pair(Integer key, String value){
        return new Row(
            new IntValue(key),
            new StringValue(value)
        );
    }
}
