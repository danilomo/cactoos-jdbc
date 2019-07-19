package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.Objects;
import java.util.Optional;

public class IntValue implements NumericValue {

    private final int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public Optional<Integer> asInt() {
        return Optional.of(value);
    }

    @Override
    public Optional<Double> asDouble() {
        return Optional.of((double) value);
    }

    @Override
    public Optional<String> asString() {
        return Optional.of(String.valueOf(value));
    }

    @Override
    public Object asObject() {
        return Integer.valueOf(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final IntValue intValue = (IntValue) o;
        return value == intValue.value;
    }
    @Override
    public int hashCode() {
        return Integer.valueOf(value).hashCode();
    }
}
