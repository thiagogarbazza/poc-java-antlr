package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

import java.math.BigDecimal;

public class MathematicsFunctionAcosTest extends AbstractFunctionsTest {

  @Test
  public void testMathematicsAcosBy5() {
    final Expression expression = new Expression("acos(0.5)");
    assertExpression(expression, RESULT_ACOS_0_5);
  }

  @Test
  public void testMathematicsAcosByIdentifier() {
    EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(0.5));
    final Expression expression = new Expression("acos(X)");
    assertExpression(expression, RESULT_ACOS_0_5);
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
