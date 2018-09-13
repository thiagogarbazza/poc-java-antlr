package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsOperationSignTest extends AbstractFunctionsTest {

    @Test
    public void testOperationSignNegativeNumber() {
        final Expression expression = new Expression("-7");
        assertExpression(expression, RESULT_7_NEGATIVE);
    }

    @Test
    public void testOperationSignNegativeExpression() {
        final Expression expression = new Expression("-(7-14)");
        assertExpression(expression, RESULT_7);
    }

    @Test
    public void testOperationSignPositiveNumber() {
        final Expression expression = new Expression("+7");
        assertExpression(expression, RESULT_7);
    }
}