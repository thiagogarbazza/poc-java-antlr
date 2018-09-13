package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

import java.math.BigDecimal;

public class ComparisonEqualTest extends AbstractFunctionsTest {

  @Test
  public void testEqualsIdentifiers() {
    EXPRESSION_CONTEXT.set("A", BigDecimal.ONE);
    EXPRESSION_CONTEXT.set("B", BigDecimal.ONE);
    final Expression expression = new Expression("A==B");
    assertExpression(expression, RESULT_TRUE);
  }

  @Test
  public void testEqualsUseFunctions() {
    final Expression expression = new Expression("20==day(2015/03/20)");
    assertExpression(expression, RESULT_TRUE);
  }

  @Test
  public void testEqualsValue() {
    final Expression expression = new Expression("1==1");
    assertExpression(expression, RESULT_TRUE);
  }

  @Test
  public void testNonEquals() {
    final Expression expression = new Expression("1==0");
    assertExpression(expression, RESULT_FALSE);
  }

  @Test
  public void testNonEqualsIdentifiers() {
    EXPRESSION_CONTEXT.set("A", BigDecimal.ONE);
    EXPRESSION_CONTEXT.set("B", BigDecimal.TEN);
    final Expression expression = new Expression("A==B");
    assertExpression(expression, RESULT_FALSE);
  }

  @Test
  public void testNonEqualsUseFunctions() {
    final Expression expression = new Expression("20==year(2015/03/20)");
    assertExpression(expression, RESULT_FALSE);
  }
}
