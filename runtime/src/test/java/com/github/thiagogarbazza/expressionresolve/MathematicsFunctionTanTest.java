package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsFunctionTanTest extends AbstractFunctionsTest {

    @Test
    public void testMathematicsTanBy5() {
        final Expression expression = new Expression("tan(90)");
        assertExpression(expression, RESULT_TAN_90);
    }

    @Test
    public void testMathematicsTanByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(90));
        final Expression expression = new Expression("tan(X)");
        assertExpression(expression, RESULT_TAN_90);
    }

    @Test
    public void testMathematicsTanByIdentifierNotInstanceValid() {
        EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
        final Expression expression = new Expression("tan(Z)");
        assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
    }

    @Test
    public void testMathematicsTanByIdentifierNotPresentInContext() {
        final Expression expression = new Expression("tan(a)");
        assertException(expression, "Variable 'a' not present in context.");
    }

    @Test
    public void testMathematicsTanNoArguments() {
        final Expression expression = new Expression("tan()");
        assertException(expression, "In position 1:4 no viable alternative at input 'tan()'.");
    }

    @Test
    public void testMathematicsTanNoParentheses() {
        final Expression expression = new Expression("tan");
        assertException(expression, "In position 1:3 no viable alternative at input 'tan'.");
    }
}