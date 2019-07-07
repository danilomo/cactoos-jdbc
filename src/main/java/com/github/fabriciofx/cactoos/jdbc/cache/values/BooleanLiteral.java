package com.github.fabriciofx.cactoos.jdbc.cache.values;

public enum BooleanLiteral implements BooleanExpression {
    TRUE(true),
    FALSE(false);
    private final boolean value;
    BooleanLiteral(boolean value) {
        this.value = value;
    }

    @Override
    public Boolean get() {
        return value;
    }
}
