package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.util.Optional;

public class DoubleValue implements Value {
    private final double value;

    public DoubleValue(double value) {
        this.value = value;
    }

    @Override
    public Optional<Double> asDouble() {
        return Optional.of(value);
    }

    @Override
    public Object asObject() {
        return Double.valueOf(value);
    }
}
