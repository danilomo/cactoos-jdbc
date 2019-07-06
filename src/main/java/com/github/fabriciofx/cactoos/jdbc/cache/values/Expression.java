package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.function.Supplier;

public interface Expression extends Supplier<Value> {
    default Value evaluate(){
        return get();
    }
}
