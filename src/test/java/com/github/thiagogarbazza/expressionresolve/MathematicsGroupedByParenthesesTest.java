package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsGroupedByParenthesesTest extends AbstractFunctionsTest {

    @Test
    public void testGroupedByParentheses() {
        final Expression expression = new Expression("(6/2) * (1+2)");
        assertExpression(expression, RESULT_9);
    }
}