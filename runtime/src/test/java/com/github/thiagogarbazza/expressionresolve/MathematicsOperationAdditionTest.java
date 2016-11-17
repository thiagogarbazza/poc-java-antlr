package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsOperationAdditionTest extends AbstractFunctionsTest {

    @Test
    public void testOperationAddition2PositiveNumbers() {
        final Expression expression = new Expression("+ 15 + 5");
        final Result expected = new Result(BigDecimal.valueOf(20));
        assertExpression(expression, expected);
    }


    @Test
    public void testOperationAdditionFraction() {
        final Expression expression = new Expression("5.6 + 4.4");
        final Result expected = new Result(BigDecimal.valueOf(10.0));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationAdditionInteger() {
        final Expression expression = new Expression("6 + 4");
        final Result expected = new Result(BigDecimal.valueOf(10));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationAdditionMany() {
        final Expression expression = new Expression("+1 + 2 + 3 + 4 + 5 + 6");
        final Result expected = new Result(BigDecimal.valueOf(21));
        assertExpression(expression, expected);
    }
}