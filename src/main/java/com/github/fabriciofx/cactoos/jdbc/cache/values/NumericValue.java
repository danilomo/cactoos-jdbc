package com.github.fabriciofx.cactoos.jdbc.cache.values;

public interface NumericValue extends Value {

    @Override
    default boolean isNumeric() {
        return true;
    }

}
