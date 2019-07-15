package com.github.fabriciofx.cactoos.jdbc.cache.values;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.util.Optional;

public interface Value {

    static Value of(Object obj) {
        Class<? extends Object> clz = obj.getClass();
        if (clz == Integer.class) {
            return new IntValue((Integer) obj);
        } else if (clz == Boolean.class) {
            return new BooleanValue((Boolean) obj);
        } else if (clz == Double.class) {
            return new DoubleValue((Double) obj);
        } else if (clz == String.class) {
            return new StringValue((String) obj);
        } else if (clz == Date.class) {
            return new DateValue((Date) obj);
        }
        throw new InvalidParameterException(clz + " cannot be wrapped to " +
            "Value");
    }

    default Optional<Integer> asInt() {
        return Optional.empty();
    }

    default Optional<Double> asDouble() {
        return Optional.empty();
    }

    default Optional<String> asString() {
        return Optional.empty();
    }

    default Optional<Date> asDate() {
        return Optional.empty();
    }

    default Optional<Boolean> asBoolean() {
        return Optional.empty();
    }

    default boolean isNumeric() {
        return false;
    }

    Object asObject();
}
