package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

public class CalendarFunctionYearTest extends AbstractFunctionsTest {

  @Test
  public void testCalendarYearBy2015_03_20() {
    final Expression expression = new Expression("year(2015/03/20)");
    assertExpression(expression, RESULT_2015);
  }

  @Test
  public void testCalendarYearByIdentifier() {
    EXPRESSION_CONTEXT.set("CURRENT", CURRENT);
    final Expression expression = new Expression("year(CURRENT)");
    assertExpression(expression, RESULT_CURRENT_YEAR);
  }

  @Test
  public void testCalendarYearByIdentifierNotInstanceValid() {
    EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
    final Expression expression = new Expression("year(Z)");
    assertException(expression, "Variable 'Z' not instance class java.util.Calendar.");
  }

  @Test
  public void testCalendarYearByIdentifierNotPresentInContext() {
    final Expression expression = new Expression("year(a)");
    assertException(expression, "Variable 'a' not present in context.");
  }

  @Test
  public void testCalendarYearNoArguments() {
    final Expression expression = new Expression("year()");
    assertException(expression, "In position 1:5 no viable alternative at input 'year()'.");
  }

  @Test
  public void testCalendarYearNoParentheses() {
    final Expression expression = new Expression("year");
    assertException(expression, "In position 1:4 no viable alternative at input 'year'.");
  }
}
