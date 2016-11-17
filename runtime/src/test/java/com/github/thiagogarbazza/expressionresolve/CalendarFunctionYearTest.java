package com.github.thiagogarbazza.expressionresolve;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class CalendarFunctionYearTest extends AbstractFunctionsTest {

    private static final Calendar CURRENT = Calendar.getInstance();

    private static final BigDecimal CURRENT_YEAR = BigDecimal.valueOf(CURRENT.get(Calendar.YEAR));

    @Test
    public void testCalendarYearBy2015_03_20() {
        final Expression expression = new Expression("year(2015/03/20)");
        final Result expected = new Result(BigDecimal.valueOf(2015));
        assertExpression(expression, expected);
    }

    @Test
    public void testCalendarYearByIdentifier() {
        EXPRESSION_CONTEXT.set("CURRENT", CURRENT);
        final Expression expression = new Expression("year(CURRENT)");
        final Result expected = new Result(CURRENT_YEAR);
        assertExpression(expression, expected);
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