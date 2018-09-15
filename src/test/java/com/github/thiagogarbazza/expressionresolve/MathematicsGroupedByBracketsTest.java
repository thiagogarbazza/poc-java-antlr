package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

@Deprecated //"replaced by acceptance test with cucumber"
public class MathematicsGroupedByBracketsTest extends AbstractFunctionsTest {

  @Test
  public void testGroupedByBrackets() {
    final Expression expression = new Expression("[6/2] * [1+2]");
    assertExpression(expression, RESULT_9);
  }

  @Test
  public void testGroupedByBracketsAndParentheses() {
    final Expression expression = new Expression("[(-6) + (-1) * (+2)]");
    assertExpression(expression, RESULT_8_NEGATIVE);
  }
}
