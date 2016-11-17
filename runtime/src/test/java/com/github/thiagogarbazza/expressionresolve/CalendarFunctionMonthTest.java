package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class CalendarFunctionMonthTest extends AbstractFunctionsTest {

    private static final Calendar CURRENT = Calendar.getInstance();

    private static final BigDecimal CURRENT_MONTH = BigDecimal.valueOf(CURRENT.get(Calendar.MONTH) + 1);

    @Test
    public void testCalendarMonthBy2015_03_20() {
        final Expression expression = new Expression("month(2015/03/20)");
        final Result expected = new Result(BigDecimal.valueOf(3));
        assertExpression(expression, expected);
    }

    @Test
    public void testCalendarMonthByIdentifier() {
        EXPRESSION_CONTEXT.set("CURRENT", CURRENT);
        final Expression expression = new Expression("month(CURRENT)");
        final Result expected = new Result(CURRENT_MONTH);
        assertExpression(expression, expected);
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