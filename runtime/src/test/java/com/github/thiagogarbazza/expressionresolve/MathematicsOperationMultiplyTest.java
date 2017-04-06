package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsOperationMultiplyTest extends AbstractFunctionsTest {

    @Test
    public void testOperationMultiplyMany() {
        final Expression expression = new Expression("5 * 4 * 3 * 2 * 1");
        assertExpression(expression, RESULT_120);
    }

    @Test
    public void testOperationMultiplySingle() {
        final Expression expression = new Expression("5 * 5");
        assertExpression(expression, RESULT_25);
    }

    @Test
    public void testOperationMultiplyAndDivideAndSumUseParen() {
        final Expression expression = new Expression("(6/2) * (1+2)");
        assertExpression(expression, RESULT_9);
    }

    @Test
    public void testOperationMultiplyAndSumUseParen() {
        final Expression expression = new Expression("(1+2) * 3");
        assertExpression(expression, RESULT_9);
    }
}