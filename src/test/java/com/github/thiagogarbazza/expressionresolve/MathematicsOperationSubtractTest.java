package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;
import org.junit.Test;

import java.math.BigDecimal;

public class MathematicsOperationSubtractTest extends AbstractFunctionsTest {

  @Test
  public void testOperationSubtract2NegativeNumbers() {
    final Expression expression = new Expression("- 15 - 5");
    assertExpression(expression, RESULT_20_NEGATIVE);
  }

  @Test
  public void testOperationSubtractAndSumSingle() {
    final Expression expression = new Expression("15 + 10 - 5");
    final Result expected = new Result(BigDecimal.valueOf(20));
    assertExpression(expression, RESULT_20);
  }

  @Test
  public void testOperationSubtractFraction() {
    final Expression expression = new Expression("15.5 - 5.2");
    assertExpression(expression, RESULT_10_3);
  }

  @Test
  public void testOperationSubtractInteger() {
    final Expression expression = new Expression("15 - 5");
    assertExpression(expression, RESULT_10);
  }
}
