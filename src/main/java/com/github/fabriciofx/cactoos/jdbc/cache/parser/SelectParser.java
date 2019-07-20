package com.github.fabriciofx.cactoos.jdbc.cache.parser;

import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteBaseListener;
import com.github.fabriciofx.cactoos.jdbc.cache.Select;
import com.github.fabriciofx.cactoos.jdbc.cache.values.BooleanExpression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import java.util.Optional;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteLexer;
import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteParser;

class SelectParser extends SQLiteBaseListener {

    private Optional<Expression> expression = Optional.empty();
    private Optional<BooleanExpression> booleanExpression = Optional.empty();
    private Optional<Select> select = Optional.empty();

    private Optional<Expression> expression(){
        Optional<Expression> temp = expression;
        expression = Optional.empty();
        return temp;
    }

    private Optional<BooleanExpression> booleanExpression(){
        Optional<BooleanExpression> temp = booleanExpression;
        booleanExpression = Optional.empty();
        return temp;
    }

    public static void main(String[] args) {
        CharStream stream = CharStreams.fromString(
            "SELECT id, name, address FROM contact WHERE name = 'Lucas'"
        );
        final SQLiteLexer lexer = new SQLiteLexer(stream);
        final SQLiteParser parser = new SQLiteParser(new CommonTokenStream(lexer));
        final ParseTree tree = parser.parse();
        final ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new SelectParser(), tree);
    }
}
