package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsFunctionSinTest extends AbstractFunctionsTest {

    private static final BigDecimal SIN_90 = BigDecimal.valueOf(0.8939966636005579);

    @Test
    public void testMathematicsSinBy5() {
        final Expression expression = new Expression("sin(90)");
        final Result expected = new Result(SIN_90);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsSinByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(90));
        final Expression expression = new Expression("sin(X)");
        final Result expected = new Result(SIN_90);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsSinByIdentifierNotInstanceValid() {
        EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
        final Expression expression = new Expression("sin(Z)");
        assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
    }

    @Test
    public void testMathematicsSinByIdentifierNotPresentInContext() {
        final Expression expression = new Expression("sin(a)");
        assertException(expression, "Variable 'a' not present in context.");
    }

    @Test
    public void testMathematicsSinNoArguments() {
        final Expression expression = new Expression("sin()");
        assertException(expression, "In position 1:4 no viable alternative at input 'sin()'.");
    }

    @Test
    public void testMathematicsSinNoParentheses() {
        final Expression expression = new Expression("sin");
        assertException(expression, "In position 1:3 no viable alternative at input 'sin'.");
    }
}