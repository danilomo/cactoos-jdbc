package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.Optional;

public class BooleanValue implements Value {

    private final boolean value;

    public BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public Optional<Boolean> asBoolean() {
        return Optional.of(value);
    }

    @Override
    public Object asObject() {
        return  Boolean.valueOf(value);
    }
}
