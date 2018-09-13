package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

public class MathematicsGroupedByParenthesesTest extends AbstractFunctionsTest {

  @Test
  public void testGroupedByParentheses() {
    final Expression expression = new Expression("(6/2) * (1+2)");
    assertExpression(expression, RESULT_9);
  }
}
