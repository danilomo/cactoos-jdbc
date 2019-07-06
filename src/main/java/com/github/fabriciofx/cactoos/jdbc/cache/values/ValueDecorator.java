package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.sql.Date;
import java.util.Optional;

public abstract class ValueDecorator implements Value {
    public abstract Value value();

    @Override
    public Optional<String> asString() {
        return value().asString();
    }

    @Override
    public Optional<Double> asDouble() {
        return value().asDouble();
    }

    @Override
    public Optional<Integer> asInt() {
        return value().asInt();
    }

    @Override
    public Object asObject() {
        return value().asObject();
    }

    @Override
    public Optional<Boolean> asBoolean() {
        return value().asBoolean();
    }

    @Override
    public Optional<Date> asDate() {
        return value().asDate();
    }
}
