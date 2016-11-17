package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class CalendarFunctionDayTest extends AbstractFunctionsTest {

    private static final BigDecimal CURRENT_DAY = BigDecimal.valueOf(CURRENT.get(Calendar.DAY_OF_MONTH));

    @Test
    public void testCalendarDayBy2015_03_20() {
        final Expression expression = new Expression("day(2015/03/20)");
        final Result expected = new Result(BigDecimal.valueOf(20));
        assertExpression(expression, expected);
    }

    @Test
    public void testCalendarDayByIdentifier() {
        EXPRESSION_CONTEXT.set("CURRENT", CURRENT);
        final Expression expression = new Expression("day(CURRENT)");
        final Result expected = new Result(CURRENT_DAY);
        assertExpression(expression, expected);
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