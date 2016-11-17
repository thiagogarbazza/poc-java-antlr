package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsOperationDivideTest extends AbstractFunctionsTest {

    @Test
    public void testOperationDivideSingle() {
        final Expression expression = new Expression("25 / 5");
        final Result expected = new Result(BigDecimal.valueOf(5));
        assertExpression(expression, expected);
    }
}