package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsFunctionLogTest extends AbstractFunctionsTest {

    private static final BigDecimal LOG_5 = BigDecimal.valueOf(0.6989700043360189);

    @Test
    public void testMathematicsLogBy5() {
        final Expression expression = new Expression("log(5)");
        final Result expected = new Result(LOG_5);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsLogByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(5));
        final Expression expression = new Expression("log(X)");
        final Result expected = new Result(LOG_5);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsLogByIdentifierNotInstanceValid() {
        EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
        final Expression expression = new Expression("log(Z)");
        assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
    }

    @Test
    public void testMathematicsLogByIdentifierNotPresentInContext() {
        final Expression expression = new Expression("log(a)");
        assertException(expression, "Variable 'a' not present in context.");
    }

    @Test
    public void testMathematicsLogNoArguments() {
        final Expression expression = new Expression("log()");
        assertException(expression, "In position 1:4 no viable alternative at input 'log()'.");
    }

    @Test
    public void testMathematicsLogNoParentheses() {
        final Expression expression = new Expression("log");
        assertException(expression, "In position 1:3 no viable alternative at input 'log'.");
    }
}