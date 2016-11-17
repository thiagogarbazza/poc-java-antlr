package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsFunctionTanTest extends AbstractFunctionsTest {

    private static final BigDecimal TAN_90 = BigDecimal.valueOf(-1.995200412208242);

    @Test
    public void testMathematicsTanBy5() {
        final Expression expression = new Expression("tan(90)");
        final Result expected = new Result(TAN_90);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsTanByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(90));
        final Expression expression = new Expression("tan(X)");
        final Result expected = new Result(TAN_90);
        assertExpression(expression, expected);
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