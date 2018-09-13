package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CompareDateTest extends AbstractFunctionsTest {

  private Calendar past = null;
  private Calendar present = null;

  @Before
  public void setUp() {
    present = Calendar.getInstance();
    past = Calendar.getInstance();
    past.set(Calendar.YEAR, present.get(Calendar.YEAR) - 1);
  }

  @Test
  public void testCompareToEquals() {
    final Expression expression = new Expression("compareDate(2015/03/20, 2015/03/20)");
    assertExpression(expression, RESULT_0);
  }

  @Test
  public void testCompareToGreater() {
    final Expression expression = new Expression("compareDate(2015/10/31, 2015/01/01)");
    assertExpression(expression, RESULT_1);
  }

  @Test
  public void testCompareToIdentifierAndText() {
    EXPRESSION_CONTEXT.set("DATE", new GregorianCalendar(2015, 9, 31));
    final Expression expression = new Expression("compareDate(2015/10/31, DATE)");
    assertExpression(expression, RESULT_0);
  }

  @Test
  public void testCompareToIdentifiers() {
    EXPRESSION_CONTEXT.set("A", present);
    EXPRESSION_CONTEXT.set("B", past);
    final Expression expression = new Expression("compareDate(A, B)");
    assertExpression(expression, RESULT_1);
  }

  @Test
  public void testCompareToLess() {
    final Expression expression = new Expression("compareDate(2015/01/01, 2015/10/31)");
    assertExpression(expression, RESULT_1_NEGATIVE);
  }
}
