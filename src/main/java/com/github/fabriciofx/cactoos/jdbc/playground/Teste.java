package com.github.fabriciofx.cactoos.jdbc.playground;

import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteBaseListener;
import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteLexer;
import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class Teste {
    public static void main(String[] args) {
        CharStream stream = CharStreams.fromString(
            "SELECT id, name, address FROM contact WHERE name = 'Lucas'"
        );
        final SQLiteLexer lexer = new SQLiteLexer(stream);
        final SQLiteParser parser = new SQLiteParser(new CommonTokenStream(lexer));
        final ParseTree tree = parser.parse();
        final ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new CachedListener(parser), tree);
    }
}

class CachedListener extends SQLiteBaseListener {
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
