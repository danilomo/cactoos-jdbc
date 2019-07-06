package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.values.Expression;
import org.junit.Test;
import static com.github.fabriciofx.cactoos.jdbc.cache.MiscMatchers.expectsException;
import static com.github.fabriciofx.cactoos.jdbc.cache.ExpressionFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestExpression {

    @Test
    public void testAddition() {
        assertExpression(
            add(integer(1), integer(2)),
            3
        );
        assertExpression(
            add(floating(0.3), floating(0.2)),
            0.3 + 0.2
        );
    }

    @Test
    public void testSubtraction() {
        assertExpression(
            minus(integer(1), integer(2)),
            -1
        );
        assertExpression(
            minus(floating(0.3), floating(0.2)),
            0.3 - 0.2
        );
    }

    @Test
    public void testMultiplication() {
        assertExpression(
            times(integer(10), integer(2)),
            20
        );
        assertExpression(
            times(floating(0.3), floating(0.2)),
            0.3 * 0.2
        );
    }

    @Test
    public void testDivision() {
        assertExpression(
            div(integer(10), integer(2)),
            5
        );
        assertExpression(
            div(floating(0.3), floating(0.2)),
            0.3 / 0.2
        );
    }

    @Test
    public void testDivisionRaisesError() {
        expectsException(
            () -> div(integer(10), integer(0)).evaluate().asObject(),
            RuntimeException.class
        );
    }

    @Test
    public void testMoreComplexExpression() {
        assertExpression(
            add(
                times(integer(2), integer(3)),
                times(integer(4), integer(5))
            ),
            ((2 * 3) + (4 * 5))
        );
    }

    @Test
    public void testMixedDoubleAndInt() {
        assertExpression(
            add(
                times(integer(2), integer(3)),
                times(floating(4.0), integer(5))
            ),
            ((2.0 * 3.0) + (4.0 * 5.0))
        );
    }

    private void assertExpression(Expression v, int val) {
        assertThat(
            v.evaluate().asInt().get(),
            is(val)
        );
    }

    private void assertExpression(Expression v, double val) {
        assertThat(
            v.evaluate().asDouble().get(),
            is(val)
        );
    }
}
