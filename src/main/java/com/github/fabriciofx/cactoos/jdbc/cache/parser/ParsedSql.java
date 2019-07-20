package com.github.fabriciofx.cactoos.jdbc.cache.parser;

import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteLexer;
import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteParser;
import com.github.fabriciofx.cactoos.jdbc.cache.Select;
import java.util.function.Supplier;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class ParsedSql implements Supplier<Select> {

    private final String query;

    public ParsedSql(final String query) {
        this.query = query;
    }

    public Select select(){
        return get();
    }

    @Override
    public Select get() {
        CharStream stream = CharStreams.fromString(query);
        SQLiteLexer lexer = new SQLiteLexer(stream);
        SQLiteParser parser = new SQLiteParser(
            new CommonTokenStream(lexer)
        );
        SQLiteParser.ParseContext root = parser.parse();

        if(root.sql_stmt_list().isEmpty() ){
            throw new RuntimeException("Empty SQL statement");
        }

        if(root.sql_stmt_list().isEmpty() ){
            throw new RuntimeException("Sorry mate, we only acept a single " +
                "statement per query");
        }

        return new SelectParser(
            root.sql_stmt_list().get(0).sql_stmt(0)
        ).select();
    }
}
