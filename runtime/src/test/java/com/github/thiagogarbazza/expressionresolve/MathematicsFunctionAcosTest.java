package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class MathematicsFunctionAcosTest extends AbstractFunctionsTest {

    private static final BigDecimal ACOS_0_5 = BigDecimal.valueOf(1.0471975511965979);

    @Test
    public void testMathematicsAcosBy5() {
        final Expression expression = new Expression("acos(0.5)");
        final Result expected = new Result(ACOS_0_5);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsAcosByIdentifier() {
        EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(0.5));
        final Expression expression = new Expression("acos(X)");
        final Result expected = new Result(ACOS_0_5);
        assertExpression(expression, expected);
    }

    @Test
    public void testMathematicsAcosByIdentifierNotInstanceValid() {
        EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
        final Expression expression = new Expression("acos(Z)");
        assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
    }

    @Test
    public void testMathematicsAcosByIdentifierNotPresentInContext() {
        final Expression expression = new Expression("acos(a)");
        assertException(expression, "Variable 'a' not present in context.");
    }

    @Test
    public void testMathematicsAcosNoArguments() {
        final Expression expression = new Expression("acos()");
        assertException(expression, "In position 1:5 no viable alternative at input 'acos()'.");
    }

    @Test
    public void testMathematicsAcosNoParentheses() {
        final Expression expression = new Expression("acos");
        assertException(expression, "In position 1:4 no viable alternative at input 'acos'.");
    }
}