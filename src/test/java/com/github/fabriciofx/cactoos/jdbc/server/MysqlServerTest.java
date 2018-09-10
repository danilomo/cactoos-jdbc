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
package com.github.fabriciofx.cactoos.jdbc.server;

import com.github.fabriciofx.cactoos.jdbc.Server;
import com.github.fabriciofx.cactoos.jdbc.script.OldSqlScriptFromInput;
import org.cactoos.io.ResourceOf;
import org.cactoos.text.JoinedText;
import org.junit.Test;

/**
 * MySQL Server test.
 *
 * @since 0.2
 * @checkstyle JavadocMethodCheck (500 lines)
 */
public final class MysqlServerTest {
    @Test
    public void startAndStop() throws Exception {
        final Server mysql = new MysqlServer(
            new OldSqlScriptFromInput(
                new ResourceOf(
                    new JoinedText(
                        "/",
                        "com/github/fabriciofx/cactoos/jdbc/agenda",
                        "agendadb-mysql.sql"
                    )
                )
            )
        );
        mysql.start();
        mysql.stop();
    }
}
