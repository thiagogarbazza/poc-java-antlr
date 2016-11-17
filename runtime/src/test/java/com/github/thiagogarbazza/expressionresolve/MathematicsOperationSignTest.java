package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsOperationSignTest extends AbstractFunctionsTest {

    @Test
    public void testOperationSignNegativeNumber() {
        final Expression expression = new Expression("-7");
        final Result expected = new Result(BigDecimal.valueOf(-7));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationSignNegativeExpression() {
        final Expression expression = new Expression("-(7-14)");
        final Result expected = new Result(BigDecimal.valueOf(7));
        assertExpression(expression, expected);
    }

    @Test
    public void testOperationSignPositiveNumber() {
        final Expression expression = new Expression("+7");
        final Result expected = new Result(BigDecimal.valueOf(7));
        assertExpression(expression, expected);
    }
}