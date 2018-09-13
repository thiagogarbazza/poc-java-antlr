package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class ComparisonNotEqualsTest extends AbstractFunctionsTest {

    @Test
    public void testNotEqualValue() {
        final Expression expression = new Expression("1!=0");
        assertExpression(expression, RESULT_TRUE);
    }

    @Test
    public void testNotEqualIdentifiers() {
        EXPRESSION_CONTEXT.set("A", BigDecimal.ONE);
        EXPRESSION_CONTEXT.set("B", BigDecimal.TEN);
        final Expression expression = new Expression("A!=B");
        assertExpression(expression, RESULT_TRUE);
    }

    @Test
    public void testNotEqualUseFunctions() {
        final Expression expression = new Expression("12!=month(2015/03/20)");
        assertExpression(expression, RESULT_TRUE);
    }

    @Test
    public void testNonNotEqual() {
        final Expression expression = new Expression("1!=1");
        assertExpression(expression, RESULT_FALSE);
    }

    @Test
    public void testNonNotEqualIdentifiers() {
        EXPRESSION_CONTEXT.set("A", BigDecimal.ONE);
        EXPRESSION_CONTEXT.set("B", BigDecimal.ONE);
        final Expression expression = new Expression("A!=B");
        assertExpression(expression, RESULT_FALSE);
    }

    @Test
    public void testNonNotEqualUseFunctions() {
        final Expression expression = new Expression("20!=day(2015/03/20)");
        assertExpression(expression, RESULT_FALSE);
    }
}