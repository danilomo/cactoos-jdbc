package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.sql.Date;
import java.util.Optional;

public class DateValue implements Value {
    private final Date value;

    public DateValue(Date value) {
        this.value = value;
    }

    @Override
    public Optional<Date> asDate() {
        return Optional.of(value);
    }

    @Override
    public Object asObject() {
        return value;
    }
}
