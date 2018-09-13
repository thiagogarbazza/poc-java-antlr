package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.junit.Test;

public class CalendarFunctionMonthTest extends AbstractFunctionsTest {

  @Test
  public void testCalendarMonthBy2015_03_20() {
    final Expression expression = new Expression("month(2015/03/20)");
    assertExpression(expression, RESULT_3);
  }

  @Test
  public void testCalendarMonthByIdentifier() {
    EXPRESSION_CONTEXT.set("CURRENT", CURRENT);
    final Expression expression = new Expression("month(CURRENT)");
    assertExpression(expression, RESULT_CURRENT_MONTH);
  }

  @Test
  public void testCalendarMonthByIdentifierNotInstanceValid() {
    EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
    final Expression expression = new Expression("month(Z)");
    assertException(expression, "Variable 'Z' not instance class java.util.Calendar.");
  }

  @Test
  public void testCalendarMonthByIdentifierNotPresentInContext() {
    final Expression expression = new Expression("month(a)");
    assertException(expression, "Variable 'a' not present in context.");
  }

  @Test
  public void testCalendarMonthNoArguments() {
    final Expression expression = new Expression("month()");
    assertException(expression, "In position 1:6 no viable alternative at input 'month()'.");
  }

  @Test
  public void testCalendarMonthNoParentheses() {
    final Expression expression = new Expression("month");
    assertException(expression, "In position 1:5 no viable alternative at input 'month'.");
  }
}
