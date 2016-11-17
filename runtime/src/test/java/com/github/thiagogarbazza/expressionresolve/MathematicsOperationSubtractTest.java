package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsOperationSubtractTest extends AbstractFunctionsTest {

    @Test
    public void testOperationSubtract2NegativeNumbers() {
        final Expression expression = new Expression("- 15 - 5");
        final Result expected = new Result(BigDecimal.valueOf(-20));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationSubtractAndSumSingle() {
        final Expression expression = new Expression("15 + 10 - 5");
        final Result expected = new Result(BigDecimal.valueOf(20));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationSubtractFraction() {
        final Expression expression = new Expression("15.5 - 5.2");
        final Result expected = new Result(BigDecimal.valueOf(10.3));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationSubtractInteger() {
        final Expression expression = new Expression("15 - 5");
        final Result expected = new Result(BigDecimal.valueOf(10));
        assertExpression(expression, expected);
    }
}