package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsFunctionLnTest extends AbstractFunctionsTest {

    private static final BigDecimal LN_5 = BigDecimal.valueOf(1.6094379124341003);

    @Test
    public void testMathematicsLnBy5() {
        final Expression expression = new Expression("ln(5)");
        final Result expected = new Result(LN_5);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsLnByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(5));
        final Expression expression = new Expression("ln(X)");
        final Result expected = new Result(LN_5);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsLnByIdentifierNotInstanceValid() {
        EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
        final Expression expression = new Expression("ln(Z)");
        assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
    }

    @Test
    public void testMathematicsLnByIdentifierNotPresentInContext() {
        final Expression expression = new Expression("ln(a)");
        assertException(expression, "Variable 'a' not present in context.");
    }

    @Test
    public void testMathematicsLnNoArguments() {
        final Expression expression = new Expression("ln()");
        assertException(expression, "In position 1:3 no viable alternative at input 'ln()'.");
    }

    @Test
    public void testMathematicsLnNoParentheses() {
        final Expression expression = new Expression("ln");
        assertException(expression, "In position 1:2 no viable alternative at input 'ln'.");
    }
}