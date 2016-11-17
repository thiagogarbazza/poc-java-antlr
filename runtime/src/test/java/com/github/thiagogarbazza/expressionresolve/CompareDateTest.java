package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class CompareDateTest extends AbstractFunctionsTest {

    private Calendar present = null;
    private Calendar past = null;

    @Before
    public void setUp(){
        present = Calendar.getInstance();
        past = Calendar.getInstance();
        past.set(Calendar.YEAR, present.get(Calendar.YEAR) -1);
    }

    @Test
    public void testCompareToEquals() {
        final Expression expression = new Expression("compareDate(2015/03/20, 2015/03/20)");
        final Result expected = new Result(BigDecimal.valueOf(0));
        assertExpression(expression, expected);
    }

    @Test
    public void testCompareToLess() {
        final Expression expression = new Expression("compareDate(2015/01/01, 2015/10/31)");
        final Result expected = new Result(BigDecimal.valueOf(-1));
        assertExpression(expression, expected);
    }

    @Test
    public void testCompareToGreater() {
        final Expression expression = new Expression("compareDate(2015/10/31, 2015/01/01)");
        final Result expected = new Result(BigDecimal.valueOf(1));
        assertExpression(expression, expected);
    }

    @Test
    public void testCompareToIdentifiers() {
        EXPRESSION_CONTEXT.set("A", present);
        EXPRESSION_CONTEXT.set("B", past);
        final Expression expression = new Expression("compareDate(A, B)");
        final Result expected = new Result(BigDecimal.valueOf(1));
        assertExpression(expression, expected);
    }
}