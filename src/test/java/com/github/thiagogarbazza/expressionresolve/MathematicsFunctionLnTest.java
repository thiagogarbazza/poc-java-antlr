package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class MathematicsFunctionLnTest extends AbstractFunctionsTest {

    @Test
    public void testMathematicsLnBy5() {
        final Expression expression = new Expression("ln(5)");
        assertExpression(expression, RESULT_LN_5);
    }

    @Test
    public void testMathematicsLnByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(5));
        final Expression expression = new Expression("ln(X)");
        assertExpression(expression, RESULT_LN_5);
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