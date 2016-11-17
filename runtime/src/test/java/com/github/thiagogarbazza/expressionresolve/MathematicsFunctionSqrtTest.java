package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsFunctionSqrtTest extends AbstractFunctionsTest {

    private static final BigDecimal SQRT_25 = BigDecimal.valueOf(5.0);

    @Test
    public void testMathematicsSqrtBy25() {
        final Expression expression = new Expression("sqrt(25)");
        final Result expected = new Result(SQRT_25);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsSqrtByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(25));
        final Expression expression = new Expression("sqrt(X)");
        final Result expected = new Result(SQRT_25);
        assertExpression(expression, expected);
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