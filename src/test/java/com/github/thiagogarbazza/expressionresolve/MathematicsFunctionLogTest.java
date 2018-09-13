package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

import java.math.BigDecimal;

public class MathematicsFunctionLogTest extends AbstractFunctionsTest {

  @Test
  public void testMathematicsLogBy5() {
    final Expression expression = new Expression("log(5)");
    assertExpression(expression, RESULT_LOG_5);
  }

  @Test
  public void testMathematicsLogByIdentifier() {
    EXPRESSION_CONTEXT.set("X", BigDecimal.valueOf(5));
    final Expression expression = new Expression("log(X)");
    assertExpression(expression, RESULT_LOG_5);
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
