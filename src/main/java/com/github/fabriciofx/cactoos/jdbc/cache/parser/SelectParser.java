package com.github.fabriciofx.cactoos.jdbc.cache.parser;

import com.github.fabriciofx.cactoos.jdbc.cache.Select;
import com.github.fabriciofx.cactoos.jdbc.cache.values.BooleanExpression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import com.github.fabriciofx.cactoos.jdbc.cache.values.Variable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteLexer;
import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteParser;
import static com.github.fabriciofx.cactoos.jdbc.cache.SQLiteParser.*;

class SelectParser {

    private final Sql_stmtContext ctx;
    private final ExpressionParser expressionParser;
    private final SelectContext selectContext;

    SelectParser(final Sql_stmtContext ctx) {
        this.ctx = ctx;
        this.expressionParser = new ExpressionParser();
        this.selectContext = selectContextFromSqlObject(ctx);
    }

    Select select(){
        List<Select_coreContext> selectCtxs = selectContext
            .selectStatements();
        if(selectCtxs.size() > 1){
            throw new RuntimeException("UNION/EXCEPT/INTERSECT " +
                "operators not supported yet");
        }
        Select_coreContext coreContext = selectCtxs.get(0);
        List<Expression> projectedColumns = extractColumns(coreContext);
        BooleanExpression whereFilter = extractWhereFilter(coreContext);
        return new Select(
            projectedColumns,
            whereFilter
        );
    }


    private SelectContext selectContextFromSqlObject(final Sql_stmtContext ctx) {
        if(ctx.factored_select_stmt() != null){
            return () -> ctx.factored_select_stmt().select_core();
        }
        if(ctx.simple_select_stmt() != null){
            return () -> Arrays.asList(
                ctx.simple_select_stmt().select_core()
            );
        }
        if(ctx.compound_select_stmt() != null){
            return () -> ctx.compound_select_stmt().select_core();
        }
        if(ctx.select_stmt() != null){
            return () -> ctx.select_stmt().select_core();
        }
        return () -> new ArrayList<>();
    }

    private BooleanExpression extractWhereFilter(Select_coreContext coreCtx) {
        return () -> true;
    }

    private List<Expression> extractColumns(Select_coreContext coreCtx) {
        return coreCtx
            .result_column()
            .stream()
            .map(this::resultColumnToExpr)
            .collect(Collectors.toList());
    }

    private Expression resultColumnToExpr(final Result_columnContext colCtx) {
        if(colCtx.table_name() != null){
            throw new RuntimeException("Operation not supported[1]");
        }
        if(colCtx.STAR() != null){
            throw new RuntimeException("Operation not supported[2]");
        }
        return expressionParser.parseExpression(colCtx.expr());
    }

    private interface SelectContext{
        List<Select_coreContext> selectStatements();
    }
}
