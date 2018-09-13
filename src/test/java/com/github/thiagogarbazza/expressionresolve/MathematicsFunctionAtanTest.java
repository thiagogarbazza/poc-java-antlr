package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

import java.math.BigDecimal;

public class MathematicsFunctionAtanTest extends AbstractFunctionsTest {

  @Test
  public void testMathematicsAtanBy0_5() {
    final Expression expression = new Expression("atan(0.5)");
    assertExpression(expression, RESULT_ATAN_0_5);
  }

  @Test
  public void testMathematicsAtanByIdentifier() {
    EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(0.5));
    final Expression expression = new Expression("atan(X)");
    assertExpression(expression, RESULT_ATAN_0_5);
  }

  @Test
  public void testMathematicsAtanByIdentifierNotInstanceValid() {
    EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
    final Expression expression = new Expression("atan(Z)");
    assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
  }

  @Test
  public void testMathematicsAtanByIdentifierNotPresentInContext() {
    final Expression expression = new Expression("atan(a)");
    assertException(expression, "Variable 'a' not present in context.");
  }

  @Test
  public void testMathematicsAtanNoArguments() throws Exception {
    final Expression expression = new Expression("atan()");
    assertException(expression, "In position 1:5 no viable alternative at input 'atan()'.");
  }

  @Test
  public void testMathematicsAtanNoParentheses() throws Exception {
    final Expression expression = new Expression("atan");
    assertException(expression, "In position 1:4 no viable alternative at input 'atan'.");
  }
}
