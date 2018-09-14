package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

@Deprecated //"replaced by acceptance test with cucumber"
public class MathematicsOperationAdditionTest extends AbstractFunctionsTest {

  @Test
  public void testOperationAddition2PositiveNumbers() {
    final Expression expression = new Expression("+ 15 + 5");
    assertExpression(expression, RESULT_20);
  }

  @Test
  public void testOperationAdditionFraction() {
    final Expression expression = new Expression("5.6 + 4.4");
    assertExpression(expression, RESULT_10_0);
  }

  @Test
  public void testOperationAdditionInteger() {
    final Expression expression = new Expression("6 + 4");
    assertExpression(expression, RESULT_10);
  }

  @Test
  public void testOperationAdditionMany() {
    final Expression expression = new Expression("+1 + 2 + 3 + 4 + 5 + 6");
    assertExpression(expression, RESULT_21);
  }
}
