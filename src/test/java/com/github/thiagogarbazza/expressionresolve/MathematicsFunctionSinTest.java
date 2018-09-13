package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

import java.math.BigDecimal;

public class MathematicsFunctionSinTest extends AbstractFunctionsTest {

  @Test
  public void testMathematicsSinBy5() {
    final Expression expression = new Expression("sin(90)");
    assertExpression(expression, RESULT_SIN_90);
  }

  @Test
  public void testMathematicsSinByIdentifier() {
    EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(90));
    final Expression expression = new Expression("sin(X)");
    assertExpression(expression, RESULT_SIN_90);
  }

  @Test
  public void testMathematicsSinByIdentifierNotInstanceValid() {
    EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
    final Expression expression = new Expression("sin(Z)");
    assertException(expression, "Variable 'Z' not instance class java.math.BigDecimal.");
  }

  @Test
  public void testMathematicsSinByIdentifierNotPresentInContext() {
    final Expression expression = new Expression("sin(a)");
    assertException(expression, "Variable 'a' not present in context.");
  }

  @Test
  public void testMathematicsSinNoArguments() {
    final Expression expression = new Expression("sin()");
    assertException(expression, "In position 1:4 no viable alternative at input 'sin()'.");
  }

  @Test
  public void testMathematicsSinNoParentheses() {
    final Expression expression = new Expression("sin");
    assertException(expression, "In position 1:3 no viable alternative at input 'sin'.");
  }
}
