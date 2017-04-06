package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsOperationDivideTest extends AbstractFunctionsTest {

    @Test
    public void testOperationDivideSingle() {
        final Expression expression = new Expression("25 / 5");
        assertExpression(expression, RESULT_5);
    }
}