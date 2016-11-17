package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class ComplexFunctionsTest extends AbstractFunctionsTest {

    @Test
    public void testMathematicUseDayFunction() {
        final Expression expression = new Expression("[(5.6 + day(2015/03/20) -0.6)*2]/5");
        final Result expected = new Result(BigDecimal.valueOf(10.0));
        assertExpression(expression, expected);
    }

    @Test
    public void testNotPresentInContext() {
        final Expression expression = new Expression("NotPresentInContext");
        assertException(expression, "Variable 'NotPresentInContext' not present in context.");
    }
}