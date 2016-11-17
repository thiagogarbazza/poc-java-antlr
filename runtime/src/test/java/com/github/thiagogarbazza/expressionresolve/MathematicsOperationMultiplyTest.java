package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsOperationMultiplyTest extends AbstractFunctionsTest {

    @Test
    public void testOperationMultiplyMany() {
        final Expression expression = new Expression("5 * 4 * 3 * 2 * 1");
        final Result expected = new Result(BigDecimal.valueOf(120));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationMultiplySingle() {
        final Expression expression = new Expression("5 * 5");
        final Result expected = new Result(BigDecimal.valueOf(25));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationMultiplyAndDivideAndSumUseParen() {
        final Expression expression = new Expression("(6/2) * (1+2)");
        final Result expected = new Result(BigDecimal.valueOf(9));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationMultiplyAndSumUseParen() {
        final Expression expression = new Expression("(1+2) * 3");
        final Result expected = new Result(BigDecimal.valueOf(9));
        assertExpression(expression, expected);
    }
}