package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsFunctionSqrtTest extends AbstractFunctionsTest {

    @Test
    public void testMathematicsSqrtBy25() {
        final Expression expression = new Expression("sqrt(25)");
        assertExpression(expression, RESULT_5_0);
    }

    @Test
    public void testMathematicsSqrtByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(25));
        final Expression expression = new Expression("sqrt(X)");
        assertExpression(expression, RESULT_5_0);
    }

    @Test
    public void testMathematicsSqrtByIdentifierNotInsTanceValid() {
        EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
        final Expression expression = new Expression("sqrt(Z)");
        assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
    }

    @Test
    public void testMathematicsSqrtByIdentifierNotPresentInContext() {
        final Expression expression = new Expression("sqrt(a)");
        assertException(expression, "Variable 'a' not present in context.");
    }

    @Test
    public void testMathematicsSqrtNoArguments() {
        final Expression expression = new Expression("sqrt()");
        assertException(expression, "In position 1:5 no viable alternative at input 'sqrt()'.");
    }

    @Test
    public void testMathematicsSqrtNoParentheses() {
        final Expression expression = new Expression("sqrt");
        assertException(expression, "In position 1:4 no viable alternative at input 'sqrt'.");
    }
}