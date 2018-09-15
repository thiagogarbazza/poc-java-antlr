package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

@Deprecated //"replaced by acceptance test with cucumber"
public class MathematicsGroupedByBracesTest extends AbstractFunctionsTest {

  @Test
  public void testGroupedByBraces() {
    final Expression expression = new Expression("{6/2} * {1+2}");
    assertExpression(expression, RESULT_9);
  }

  @Test
  public void testGroupedByBracesAndParenthesesAndBrackets() {
    final Expression expression = new Expression("{(-2) + [(-4-2) + (-1) * (+2)] + 20} + 15");
    assertExpression(expression, RESULT_25);
  }
}
