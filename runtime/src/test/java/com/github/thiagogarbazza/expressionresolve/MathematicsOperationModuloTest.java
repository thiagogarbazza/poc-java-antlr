package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsOperationModuloTest extends AbstractFunctionsTest {

    @Test
    public void testOperationModulo10_3() {
        final Expression expression = new Expression("10 % 3");
        final Result expected = new Result(BigDecimal.valueOf(1));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationModulo24_5() {
        final Expression expression = new Expression("24 % 5");
        final Result expected = new Result(BigDecimal.valueOf(4));
        assertExpression(expression, expected);
    }
}