package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.Objects;
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
    public Optional<String> asString() {
        return Optional.of(String.valueOf(value));
    }

    @Override
    public Object asObject() {
        return Boolean.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BooleanValue that = (BooleanValue) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
