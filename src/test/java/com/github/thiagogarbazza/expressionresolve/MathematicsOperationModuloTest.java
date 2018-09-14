package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

@Deprecated //"replaced by acceptance test with cucumber"
public class MathematicsOperationModuloTest extends AbstractFunctionsTest {

  @Test
  public void testOperationModulo10_3() {
    final Expression expression = new Expression("10 % 3");
    assertExpression(expression, RESULT_1);
  }

  @Test
  public void testOperationModulo10_5() {
    final Expression expression = new Expression("10 % 5");
    assertExpression(expression, RESULT_0);
  }

  @Test
  public void testOperationModulo24_5() {
    final Expression expression = new Expression("24 % 5");
    assertExpression(expression, RESULT_4);
  }
}
