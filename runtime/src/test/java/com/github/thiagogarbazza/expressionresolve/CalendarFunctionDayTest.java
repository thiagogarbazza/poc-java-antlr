package com.github.thiagogarbazza.expressionresolve;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;

public class CalendarFunctionDayTest extends AbstractFunctionsTest {

    @Test
    public void testCalendarDayBy2015_03_20() {
        final Expression expression = new Expression("day(2015/03/20)");
        assertExpression(expression, RESULT_20);
    }

    @Test
    public void testCalendarDayByIdentifier() {
        EXPRESSION_CONTEXT.set("CURRENT", CURRENT);
        final Expression expression = new Expression("day(CURRENT)");
        assertExpression(expression, RESULT_CURRENT_DAY_OF_MONTH);
    }

    @Test
    public void testCalendarDayByIdentifierNotInstanceValid() {
        EXPRESSION_CONTEXT.set("Z", "InstanceInvalid");
        final Expression expression = new Expression("day(Z)");
        assertException(expression, "Variable 'Z' not instance class java.util.Calendar.");
    }

    @Test
    public void testCalendarDayByIdentifierNotPresentInContext() {
        final Expression expression = new Expression("day(a)");
        assertException(expression, "Variable 'a' not present in context.");
    }

    @Test
    public void testCalendarDayNoArguments() {
        final Expression expression = new Expression("day()");
        assertException(expression, "In position 1:4 no viable alternative at input 'day()'.");
    }

    @Test
    public void testCalendarDayNoParentheses() {
        final Expression expression = new Expression("day");
        assertException(expression, "In position 1:3 no viable alternative at input 'day'.");
    }
}