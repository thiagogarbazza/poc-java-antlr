package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsOperationPowTest extends AbstractFunctionsTest {

    @Test
    public void testOperationPow8_3() {
        final Expression expression = new Expression("8 ^ 3");
        final Result expected = new Result(BigDecimal.valueOf(512));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationPow12_2() {
        final Expression expression = new Expression("12 ^ 2");
        final Result expected = new Result(BigDecimal.valueOf(144));
        assertExpression(expression, expected);
    }
}