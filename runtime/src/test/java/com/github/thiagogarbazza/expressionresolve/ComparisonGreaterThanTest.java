package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class ComparisonGreaterThanTest extends AbstractFunctionsTest {

    @Test
    public void testGreaterThanValue() {
        final Expression expression = new Expression("1>0");
        assertExpression(expression, RESULT_TRUE);
    }

    @Test
    public void testGreaterThanIdentifiers() {
        EXPRESSION_CONTEXT.set("A", BigDecimal.TEN);
        EXPRESSION_CONTEXT.set("B", BigDecimal.ONE);
        final Expression expression = new Expression("A>B");
        assertExpression(expression, RESULT_TRUE);
    }

    @Test
    public void testGreaterThanUseFunctions() {
        final Expression expression = new Expression("21>day(2015/03/20)");
        assertExpression(expression, RESULT_TRUE);
    }

    @Test
    public void testNonGreaterThan() {
        final Expression expression = new Expression("0>1");
        assertExpression(expression, RESULT_FALSE);
    }

    @Test
    public void testNonGreaterThanIdentifiers() {
        EXPRESSION_CONTEXT.set("A", BigDecimal.TEN);
        EXPRESSION_CONTEXT.set("B", BigDecimal.TEN);
        final Expression expression = new Expression("A>B");
        assertExpression(expression, RESULT_FALSE);
    }

    @Test
    public void testNonGreaterThanUseFunctions() {
        final Expression expression = new Expression("20>year(2015/03/20)");
        assertExpression(expression, RESULT_FALSE);
    }
}