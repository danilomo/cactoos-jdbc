package com.github.fabriciofx.cactoos.jdbc.cache;

import com.github.fabriciofx.cactoos.jdbc.cache.values.BooleanExpression;
import org.junit.Test;
import static com.github.fabriciofx.cactoos.jdbc.cache.ExpressionFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ComparisonExpressionTest {

    @Test
    public void testEq(){
        BooleanExpression eq1 = eq(
            integer(10),
            integer(10)
        );
        BooleanExpression eq2 = eq(
            string("AAAA"),
            string("AAAA")
        );
        BooleanExpression eq3 = eq(
            integer(10),
            integer(11)
        );
        BooleanExpression eq4 = eq(
            string("AAAA"),
            string("AAAAB")
        );
        assertThat(eq1.evaluate(), is(true));
        assertThat(eq2.evaluate(), is(true));
        assertThat(eq3.evaluate(), is(false));
        assertThat(eq4.evaluate(), is(false));
    }

    @Test
    public void testNeq(){
        BooleanExpression eq1 = neq(
            integer(10),
            integer(10)
        );
        BooleanExpression eq2 = neq(
            string("AAAA"),
            string("AAAA")
        );
        BooleanExpression eq3 = neq(
            integer(10),
            integer(11)
        );
        BooleanExpression eq4 = neq(
            string("AAAA"),
            string("AAAAB")
        );
        assertThat(eq1.evaluate(), is(false));
        assertThat(eq2.evaluate(), is(false));
        assertThat(eq3.evaluate(), is(true));
        assertThat(eq4.evaluate(), is(true));
    }

    @Test
    public void testGE(){
        BooleanExpression ge1 = ge(
            integer(10),
            integer(10)
        );
        BooleanExpression ge2 = ge(
            integer(12),
            integer(10)
        );
        BooleanExpression ge3 = ge(
            string("ABCD"),
            string("AACD")
        );
        assertThat(ge1.evaluate(),is(true));
        assertThat(ge2.evaluate(),is(true));
        assertThat(ge3.evaluate(),is(true));

        BooleanExpression ge4 = ge(
            integer(10),
            integer(12)
        );
        BooleanExpression ge5 = ge(
            string("AACD"),
            string("ABCD")
        );
        assertThat(ge4.evaluate(),is(false));
        assertThat(ge5.evaluate(),is(false));
    }

    @Test
    public void testLE(){
        BooleanExpression le1 = le(
            integer(10),
            integer(10)
        );
        BooleanExpression le2 = le(
            integer(10),
            integer(12)
        );
        BooleanExpression le3 = le(
            string("AACD"),
            string("ABCD")
        );
        assertThat(le1.evaluate(),is(true));
        assertThat(le2.evaluate(),is(true));
        assertThat(le3.evaluate(),is(true));

        BooleanExpression le4 = le(
            integer(12),
            integer(10)
        );
        BooleanExpression le5 = le(
            string("ABCD"),
            string("AACD")
        );
        assertThat(le4.evaluate(),is(false));
        assertThat(le5.evaluate(),is(false));
    }

    @Test
    public void testGT(){
        BooleanExpression gt1 = gt(
            integer(10),
            integer(10)
        );
        BooleanExpression gt2 = gt(
            integer(12),
            integer(10)
        );
        BooleanExpression gt3 = gt(
            string("ABCD"),
            string("AACD")
        );
        assertThat(gt1.evaluate(),is(false));
        assertThat(gt2.evaluate(),is(true));
        assertThat(gt3.evaluate(),is(true));

        BooleanExpression gt4 = gt(
            integer(10),
            integer(12)
        );
        BooleanExpression gt5 = gt(
            string("AACD"),
            string("ABCD")
        );
        assertThat(gt4.evaluate(),is(false));
        assertThat(gt5.evaluate(),is(false));
    }

    @Test
    public void testLT(){
        BooleanExpression lt1 = lt(
            integer(10),
            integer(10)
        );
        BooleanExpression lt2 = lt(
            integer(10),
            integer(12)
        );
        BooleanExpression lt3 = lt(
            string("AACD"),
            string("ABCD")
        );
        assertThat(lt1.evaluate(),is(false));
        assertThat(lt2.evaluate(),is(true));
        assertThat(lt3.evaluate(),is(true));

        BooleanExpression lt4 = lt(
            integer(12),
            integer(10)
        );
        BooleanExpression lt5 = lt(
            string("ABCD"),
            string("AACD")
        );
        assertThat(lt4.evaluate(),is(false));
        assertThat(lt5.evaluate(),is(false));
    }

}
