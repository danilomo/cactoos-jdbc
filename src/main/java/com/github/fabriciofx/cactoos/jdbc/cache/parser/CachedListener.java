/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2018 Fabrício Barros Cabral
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
package com.github.fabriciofx.cactoos.jdbc.cache.parser;

import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteBaseListener;
import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteParser;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.antlr.v4.runtime.TokenStream;

public final class CachedListener extends SQLiteBaseListener {
    private final SQLiteParser parser;
    private final Set<String> commands;
    private final Set<String> tables;
    private final Set<String> columns;
    private final Set<String> filters;

    public CachedListener(final SQLiteParser prsr) {
        this.parser = prsr;
        this.commands = new HashSet<>();
        this.tables = new HashSet<>();
        this.columns = new HashSet<>();
        this.filters = new HashSet<>();
    }

    @Override
    public void exitParse(final SQLiteParser.ParseContext ctx) {
        System.out.println("Commands: " + Arrays.toString(this.commands.toArray(new String[0])));
        System.out.println("  Tables: " + Arrays.toString(this.tables.toArray(new String[0])));
        System.out.println(" Columns: " + Arrays.toString(this.columns.toArray(new String[0])));
        System.out.println(" Filters: " + Arrays.toString(this.filters.toArray(new String[0])));
    }

    @Override
    public void enterSelect_core(final SQLiteParser.Select_coreContext ctx) {
        final TokenStream tokens = this.parser.getTokenStream();
        this.commands.add(tokens.getText(ctx).split("\\s+")[0]);
    }

    @Override
    public void enterTable_name(final SQLiteParser.Table_nameContext ctx) {
        this.tables.add(ctx.getText());
    }

    @Override
    public void enterColumn_name(final SQLiteParser.Column_nameContext ctx) {
        this.columns.add(ctx.getText());
    }
}
