package com.github.thiagogarbazza.expressionresolve;

import java.util.Calendar;

import org.junit.Test;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.Result;

public class CalendarFunctionTodayTest extends AbstractFunctionsTest {
    @Test
    public void testToday() {
        final Expression expression = new Expression("today");
        final Result expected = new Result(EXPRESSION_CONTEXT.get("today", Calendar.class));
        assertExpression(expression, expected);
    }
}