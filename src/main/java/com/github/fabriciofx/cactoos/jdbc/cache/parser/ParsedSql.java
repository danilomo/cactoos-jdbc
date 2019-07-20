package com.github.fabriciofx.cactoos.jdbc.cache.parser;

import com.github.fabriciofx.cactoos.jdbc.cache.Select;
import java.util.function.Supplier;

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
        return null;
    }
}
