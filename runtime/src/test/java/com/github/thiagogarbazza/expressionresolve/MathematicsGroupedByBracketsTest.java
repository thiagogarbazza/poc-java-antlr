package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsGroupedByBracketsTest extends AbstractFunctionsTest {

    @Test
    public void testGroupedByBrackets() {
        final Expression expression = new Expression("[6/2] * [1+2]");
        assertExpression(expression, V_9);
    }

    @Test
    public void testGroupedByBracketsAndParentheses() {
        final Expression expression = new Expression("[(-6) + (-1) * (+2)]");
        assertExpression(expression, V_8_NEGATIVE);
    }
}