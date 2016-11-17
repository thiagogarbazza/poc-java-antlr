package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsFunctionAsinTest extends AbstractFunctionsTest {

    private static final BigDecimal ASIN_0_5 = BigDecimal.valueOf(0.5235987755982989);

    @Test
    public void testMathematicsAsinBy5() {
        final Expression expression = new Expression("asin(0.5)");
        final Result expected = new Result(ASIN_0_5);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsAsinByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(0.5));
        final Expression expression = new Expression("asin(X)");
        final Result expected = new Result(ASIN_0_5);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsAsinByIdentifierNotInstanceValid() {
        EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
        final Expression expression = new Expression("asin(Z)");
        assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
    }

    @Test
    public void testMathematicsAsinByIdentifierNotPresentInContext() {
        final Expression expression = new Expression("asin(a)");
        assertException(expression, "Variable 'a' not present in context.");
    }

    @Test
    public void testMathematicsAsinNoArguments() {
        final Expression expression = new Expression("asin()");
        assertException(expression, "In position 1:5 no viable alternative at input 'asin()'.");
    }

    @Test
    public void testMathematicsAsinNoParentheses() {
        final Expression expression = new Expression("asin");
        assertException(expression, "In position 1:4 no viable alternative at input 'asin'.");
    }
}