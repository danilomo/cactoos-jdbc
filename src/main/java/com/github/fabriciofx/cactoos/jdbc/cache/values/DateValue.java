package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.sql.Date;
import java.util.Objects;
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
    public Optional<String> asString() {
        return Optional.of(value.toString());
    }

    @Override
    public Object asObject() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final DateValue dateValue = (DateValue) o;
        return Objects.equals(value, dateValue.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
