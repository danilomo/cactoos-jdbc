package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.Optional;

public class DoubleValue implements NumericValue {
    private final double value;

    public DoubleValue(double value) {
        this.value = value;
    }

    @Override
    public Optional<Double> asDouble() {
        return Optional.of(value);
    }

    @Override
    public Optional<String> asString() {
        return Optional.of(String.valueOf(value));
    }

    @Override
    public Object asObject() {
        return Double.valueOf(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DoubleValue that = (DoubleValue) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(value).hashCode();
    }
}
