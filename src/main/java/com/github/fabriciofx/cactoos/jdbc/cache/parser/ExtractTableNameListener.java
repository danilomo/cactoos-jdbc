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

import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteListener;
import com.github.fabriciofx.cactoos.jdbc.cache.SQLiteParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public final class ExtractTableNameListener implements SQLiteListener {
    private final SQLiteParser parser;
    private final List<String> commands;
    private final List<String> tables;
    private final List<String> columns;
    private final List<String> filters;

    public ExtractTableNameListener(final SQLiteParser prsr) {
        this.parser = prsr;
        this.commands = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.columns = new ArrayList<>();
        this.filters = new ArrayList<>();
    }
    @Override
    public void enterParse(final SQLiteParser.ParseContext ctx) {
        // Aqui é o primeiro método chamado (seria como o init())
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitParse(final SQLiteParser.ParseContext ctx) {
        // Aqui é último método chamado (seria o finalize())
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
        System.out.println("Commands: " + Arrays.toString(this.commands.toArray(new String[0])));
        System.out.println("  Tables: " + Arrays.toString(this.tables.toArray(new String[0])));
        System.out.println(" Columns: " + Arrays.toString(this.columns.toArray(new String[0])));
        System.out.println(" Filters: " + Arrays.toString(this.filters.toArray(new String[0])));
    }
    @Override
    public void enterError(final SQLiteParser.ErrorContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitError(final SQLiteParser.ErrorContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSql_stmt_list(final SQLiteParser.Sql_stmt_listContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName()
        );
    }
    @Override
    public void exitSql_stmt_list(final SQLiteParser.Sql_stmt_listContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSql_stmt(final SQLiteParser.Sql_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
//        System.out.println("  Text: " + ctx.getText());
//        TokenStream tokens = this.parser.getTokenStream();
//        String sql = tokens.getText(ctx);
//
//        System.out.println("SQL: " + sql);
    }
    @Override
    public void exitSql_stmt(final SQLiteParser.Sql_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterAlter_table_stmt(final SQLiteParser.Alter_table_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitAlter_table_stmt(final SQLiteParser.Alter_table_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterAnalyze_stmt(final SQLiteParser.Analyze_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitAnalyze_stmt(final SQLiteParser.Analyze_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterAttach_stmt(final SQLiteParser.Attach_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitAttach_stmt(final SQLiteParser.Attach_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterBegin_stmt(final SQLiteParser.Begin_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitBegin_stmt(final SQLiteParser.Begin_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCommit_stmt(final SQLiteParser.Commit_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCommit_stmt(final SQLiteParser.Commit_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCompound_select_stmt(final SQLiteParser.Compound_select_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCompound_select_stmt(final SQLiteParser.Compound_select_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCreate_index_stmt(final SQLiteParser.Create_index_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCreate_index_stmt(final SQLiteParser.Create_index_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCreate_table_stmt(final SQLiteParser.Create_table_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCreate_table_stmt(final SQLiteParser.Create_table_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCreate_trigger_stmt(final SQLiteParser.Create_trigger_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCreate_trigger_stmt(final SQLiteParser.Create_trigger_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCreate_view_stmt(final SQLiteParser.Create_view_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCreate_view_stmt(final SQLiteParser.Create_view_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCreate_virtual_table_stmt(final SQLiteParser.Create_virtual_table_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCreate_virtual_table_stmt(final SQLiteParser.Create_virtual_table_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterDelete_stmt(final SQLiteParser.Delete_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitDelete_stmt(final SQLiteParser.Delete_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterDelete_stmt_limited(final SQLiteParser.Delete_stmt_limitedContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitDelete_stmt_limited(final SQLiteParser.Delete_stmt_limitedContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterDetach_stmt(final SQLiteParser.Detach_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitDetach_stmt(final SQLiteParser.Detach_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterDrop_index_stmt(final SQLiteParser.Drop_index_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitDrop_index_stmt(final SQLiteParser.Drop_index_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterDrop_table_stmt(final SQLiteParser.Drop_table_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitDrop_table_stmt(final SQLiteParser.Drop_table_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterDrop_trigger_stmt(final SQLiteParser.Drop_trigger_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitDrop_trigger_stmt(final SQLiteParser.Drop_trigger_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterDrop_view_stmt(final SQLiteParser.Drop_view_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitDrop_view_stmt(final SQLiteParser.Drop_view_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterFactored_select_stmt(final SQLiteParser.Factored_select_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitFactored_select_stmt(final SQLiteParser.Factored_select_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterInsert_stmt(final SQLiteParser.Insert_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitInsert_stmt(final SQLiteParser.Insert_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterPragma_stmt(final SQLiteParser.Pragma_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitPragma_stmt(final SQLiteParser.Pragma_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterReindex_stmt(final SQLiteParser.Reindex_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitReindex_stmt(final SQLiteParser.Reindex_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterRelease_stmt(final SQLiteParser.Release_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitRelease_stmt(final SQLiteParser.Release_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterRollback_stmt(final SQLiteParser.Rollback_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitRollback_stmt(final SQLiteParser.Rollback_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSavepoint_stmt(final SQLiteParser.Savepoint_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitSavepoint_stmt(final SQLiteParser.Savepoint_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSimple_select_stmt(final SQLiteParser.Simple_select_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitSimple_select_stmt(final SQLiteParser.Simple_select_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSelect_stmt(final SQLiteParser.Select_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
        System.out.println("  Text: " + ctx.getText());
        TokenStream tokens = this.parser.getTokenStream();
        String sql = tokens.getText(ctx);

        System.out.println("Select SQL: " + sql);
    }
    @Override
    public void exitSelect_stmt(final SQLiteParser.Select_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSelect_or_values(final SQLiteParser.Select_or_valuesContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitSelect_or_values(final SQLiteParser.Select_or_valuesContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterUpdate_stmt(final SQLiteParser.Update_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitUpdate_stmt(final SQLiteParser.Update_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterUpdate_stmt_limited(final SQLiteParser.Update_stmt_limitedContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitUpdate_stmt_limited(final SQLiteParser.Update_stmt_limitedContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterVacuum_stmt(final SQLiteParser.Vacuum_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitVacuum_stmt(final SQLiteParser.Vacuum_stmtContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterColumn_def(final SQLiteParser.Column_defContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitColumn_def(final SQLiteParser.Column_defContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterType_name(final SQLiteParser.Type_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitType_name(final SQLiteParser.Type_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterColumn_constraint(final SQLiteParser.Column_constraintContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitColumn_constraint(final SQLiteParser.Column_constraintContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterConflict_clause(final SQLiteParser.Conflict_clauseContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitConflict_clause(final SQLiteParser.Conflict_clauseContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterExpr(final SQLiteParser.ExprContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitExpr(final SQLiteParser.ExprContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterForeign_key_clause(final SQLiteParser.Foreign_key_clauseContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitForeign_key_clause(final SQLiteParser.Foreign_key_clauseContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterRaise_function(final SQLiteParser.Raise_functionContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitRaise_function(final SQLiteParser.Raise_functionContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterIndexed_column(final SQLiteParser.Indexed_columnContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitIndexed_column(final SQLiteParser.Indexed_columnContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterTable_constraint(final SQLiteParser.Table_constraintContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitTable_constraint(final SQLiteParser.Table_constraintContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterWith_clause(final SQLiteParser.With_clauseContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitWith_clause(final SQLiteParser.With_clauseContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterQualified_table_name(final SQLiteParser.Qualified_table_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitQualified_table_name(final SQLiteParser.Qualified_table_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterOrdering_term(final SQLiteParser.Ordering_termContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitOrdering_term(final SQLiteParser.Ordering_termContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterPragma_value(final SQLiteParser.Pragma_valueContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitPragma_value(final SQLiteParser.Pragma_valueContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCommon_table_expression(final SQLiteParser.Common_table_expressionContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCommon_table_expression(final SQLiteParser.Common_table_expressionContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterResult_column(final SQLiteParser.Result_columnContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitResult_column(final SQLiteParser.Result_columnContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterTable_or_subquery(final SQLiteParser.Table_or_subqueryContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitTable_or_subquery(final SQLiteParser.Table_or_subqueryContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterJoin_clause(final SQLiteParser.Join_clauseContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitJoin_clause(final SQLiteParser.Join_clauseContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterJoin_operator(final SQLiteParser.Join_operatorContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitJoin_operator(final SQLiteParser.Join_operatorContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterJoin_constraint(final SQLiteParser.Join_constraintContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitJoin_constraint(final SQLiteParser.Join_constraintContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSelect_core(final SQLiteParser.Select_coreContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
        TokenStream tokens = this.parser.getTokenStream();
        String sql = tokens.getText(ctx);

        System.out.println("Core SQL: " + sql);
        this.commands.add(ctx.getText());
    }
    @Override
    public void exitSelect_core(final SQLiteParser.Select_coreContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCompound_operator(final SQLiteParser.Compound_operatorContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCompound_operator(final SQLiteParser.Compound_operatorContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCte_table_name(final SQLiteParser.Cte_table_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCte_table_name(final SQLiteParser.Cte_table_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSigned_number(final SQLiteParser.Signed_numberContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitSigned_number(final SQLiteParser.Signed_numberContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterLiteral_value(final SQLiteParser.Literal_valueContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitLiteral_value(final SQLiteParser.Literal_valueContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterUnary_operator(final SQLiteParser.Unary_operatorContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitUnary_operator(final SQLiteParser.Unary_operatorContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterError_message(final SQLiteParser.Error_messageContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitError_message(final SQLiteParser.Error_messageContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterModule_argument(final SQLiteParser.Module_argumentContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitModule_argument(final SQLiteParser.Module_argumentContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterColumn_alias(final SQLiteParser.Column_aliasContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitColumn_alias(final SQLiteParser.Column_aliasContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterKeyword(final SQLiteParser.KeywordContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitKeyword(final SQLiteParser.KeywordContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterName(final SQLiteParser.NameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitName(final SQLiteParser.NameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterFunction_name(final SQLiteParser.Function_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitFunction_name(final SQLiteParser.Function_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterDatabase_name(final SQLiteParser.Database_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitDatabase_name(final SQLiteParser.Database_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterTable_name(final SQLiteParser.Table_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
        System.out.println("  Text: " + ctx.getText());
        this.tables.add(ctx.getText());
    }
    @Override
    public void exitTable_name(final SQLiteParser.Table_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterTable_or_index_name(final SQLiteParser.Table_or_index_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitTable_or_index_name(final SQLiteParser.Table_or_index_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterNew_table_name(final SQLiteParser.New_table_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitNew_table_name(final SQLiteParser.New_table_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterColumn_name(final SQLiteParser.Column_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
        System.out.println("  Text: " + ctx.getText());
        this.columns.add(ctx.getText());
    }
    @Override
    public void exitColumn_name(final SQLiteParser.Column_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterCollation_name(final SQLiteParser.Collation_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitCollation_name(final SQLiteParser.Collation_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterForeign_table(final SQLiteParser.Foreign_tableContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitForeign_table(final SQLiteParser.Foreign_tableContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterIndex_name(final SQLiteParser.Index_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitIndex_name(final SQLiteParser.Index_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterTrigger_name(final SQLiteParser.Trigger_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitTrigger_name(final SQLiteParser.Trigger_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterView_name(final SQLiteParser.View_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitView_name(final SQLiteParser.View_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterModule_name(final SQLiteParser.Module_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitModule_name(final SQLiteParser.Module_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterPragma_name(final SQLiteParser.Pragma_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitPragma_name(final SQLiteParser.Pragma_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterSavepoint_name(final SQLiteParser.Savepoint_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitSavepoint_name(final SQLiteParser.Savepoint_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterTable_alias(final SQLiteParser.Table_aliasContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitTable_alias(final SQLiteParser.Table_aliasContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterTransaction_name(final SQLiteParser.Transaction_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitTransaction_name(final SQLiteParser.Transaction_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterAny_name(final SQLiteParser.Any_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitAny_name(final SQLiteParser.Any_nameContext ctx) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void visitTerminal(final TerminalNode terminalNode) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void visitErrorNode(final ErrorNode errorNode) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void enterEveryRule(final ParserRuleContext parserRuleContext) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
    @Override
    public void exitEveryRule(final ParserRuleContext parserRuleContext) {
        System.out.println(
            "Method: " +
                new Object() {}
                    .getClass()
                    .getEnclosingMethod()
                    .getName()
        );
    }
}
