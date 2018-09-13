package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

public class BooleanExpressionTest extends AbstractFunctionsTest {

  @Test
  public void testConjunctionANDResultFalse() {
    final Expression expression = new Expression("true && false && true");
    assertExpression(expression, RESULT_FALSE);
  }

  @Test
  public void testConjunctionANDResultTrue() {
    final Expression expression = new Expression("true && true && true");
    assertExpression(expression, RESULT_TRUE);
  }

  @Test
  public void testConjunctionORAndResultFalse() {
    final Expression expression = new Expression("false || false && true");
    assertExpression(expression, RESULT_FALSE);
  }

  @Test
  public void testConjunctionORAndResultTrue() {
    final Expression expression = new Expression("false && true || true");
    assertExpression(expression, RESULT_TRUE);
  }

  @Test
  public void testConjunctionORResultFalse() {
    final Expression expression = new Expression("false || false");
    assertExpression(expression, RESULT_FALSE);
  }

  @Test
  public void testConjunctionORResultTrue() {
    final Expression expression = new Expression("true || false || false");
    assertExpression(expression, RESULT_TRUE);
  }

  @Test
  public void testPrimitiveBooleanFalse() {
    final Expression expression = new Expression("false");
    assertExpression(expression, RESULT_FALSE);
  }

  @Test
  public void testPrimitiveBooleanTrue() {
    final Expression expression = new Expression("true");
    assertExpression(expression, RESULT_TRUE);
  }
}
