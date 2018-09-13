package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

public class ComplexFunctionsTest extends AbstractFunctionsTest {

  @Test
  public void testMathematicUseDayFunction() {
    final Expression expression = new Expression("[(5.6 + day(2015/03/20) -0.7)*2]/5");
    assertExpression(expression, RESULT_9_96);
  }

  @Test
  public void testNotPresentInContext() {
    final Expression expression = new Expression("NotPresentInContext");
    assertException(expression, "Variable 'NotPresentInContext' not present in context.");
  }
}
