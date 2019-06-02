package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.Optional;

public class StringValue implements Value{

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public Optional<String> asString() {
        return Optional.of(value);
    }

    @Override
    public Object asObject() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
