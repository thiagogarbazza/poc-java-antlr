package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class CompareStringTest extends AbstractFunctionsTest {

    @Test
    public void testCompareToEquals() {
        final Expression expression = new Expression("compareString('BRAZIL', 'BRAZIL')");
        assertExpression(expression, RESULT_0);
    }

    @Test
    public void testCompareToLess() {
        final Expression expression = new Expression("compareString('AUSTRALIA', 'BRAZIL')");
        assertExpression(expression, RESULT_1_NEGATIVE);
    }

    @Test
    public void testCompareToGreater() {
        final Expression expression = new Expression("compareString('BRAZIL', 'AUSTRALIA')");
        assertExpression(expression, RESULT_1);
    }

    @Test
    public void testCompareToIdentifiers() {
        EXPRESSION_CONTEXT.set("A", "BRAZIL");
        EXPRESSION_CONTEXT.set("B", "AUSTRALIA");
        final Expression expression = new Expression("compareString(A, B)");
        assertExpression(expression, RESULT_1);
    }
}