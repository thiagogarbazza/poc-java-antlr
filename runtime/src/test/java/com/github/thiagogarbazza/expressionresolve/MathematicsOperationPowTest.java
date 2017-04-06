package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsOperationPowTest extends AbstractFunctionsTest {

    @Test
    public void testOperationPow8_3() {
        final Expression expression = new Expression("8 ^ 3");
        assertExpression(expression, RESULT_512);
    }

    @Test
    public void testOperationPow12_2() {
        final Expression expression = new Expression("12 ^ 2");
        assertExpression(expression, RESULT_144);
    }
}