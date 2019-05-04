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
package com.github.fabriciofx.cactoos.jdbc.param;

import com.github.fabriciofx.cactoos.jdbc.Param;
import java.sql.PreparedStatement;
import org.cactoos.Text;
import org.cactoos.text.TextOf;
import org.cactoos.text.UncheckedText;

/**
 * String param.
 *
 * @since 0.2
 */
public final class ParamText implements Param {
    /**
     * Name.
     */
    private final Text id;

    /**
     * Value.
     */
    private final Text value;

    /**
     * Ctor.
     */
    public ParamText(final String name, final String value) {
        this(new TextOf(name), new TextOf(value));
    }

    /**
     * Ctor.
     */
    public ParamText(final String name, final Text value) {
        this(new TextOf(name), value);
    }

    /**
     * Ctor.
     */
    public ParamText(final Text name, final String value) {
        this(name, new TextOf(value));
    }

    /**
     * Ctor.
     * @param name The id
     * @param value The data
     */
    public ParamText(final Text name, final Text value) {
        this.id = name;
        this.value = value;
    }

    @Override
    public String name() {
        return new UncheckedText(this.id).asString();
    }

    @Override
    public void prepare(
        final PreparedStatement stmt,
        final int index
    ) throws Exception {
        stmt.setString(index, this.value.asString());
    }

    @Override
    public String asString() throws Exception {
        return this.value.asString();
    }
}
