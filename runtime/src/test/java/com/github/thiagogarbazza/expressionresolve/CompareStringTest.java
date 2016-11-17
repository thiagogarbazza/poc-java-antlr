package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class CompareStringTest extends AbstractFunctionsTest {

    @Test
    public void testCompareToEquals() {
        final Expression expression = new Expression("compareString('BRAZIL', 'BRAZIL')");
        final Result expected = new Result(BigDecimal.valueOf(0));
        assertExpression(expression, expected);
    }

    @Test
    public void testCompareToLess() {
        final Expression expression = new Expression("compareString('AUSTRALIA', 'BRAZIL')");
        final Result expected = new Result(BigDecimal.valueOf(-1));
        assertExpression(expression, expected);
    }

    @Test
    public void testCompareToGreater() {
        final Expression expression = new Expression("compareString('BRAZIL', 'AUSTRALIA')");
        final Result expected = new Result(BigDecimal.valueOf(1));
        assertExpression(expression, expected);
    }

    @Test
    public void testCompareToIdentifiers() {
        EXPRESSION_CONTEXT.set("A", "BRAZIL");
        EXPRESSION_CONTEXT.set("B", "AUSTRALIA");
        final Expression expression = new Expression("compareString(A, B)");
        final Result expected = new Result(BigDecimal.valueOf(1));
        assertExpression(expression, expected);
    }
}