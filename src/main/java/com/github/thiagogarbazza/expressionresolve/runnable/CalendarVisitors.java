package com.github.thiagogarbazza.expressionresolve.runnable;

import java.math.BigDecimal;
import java.util.Calendar;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CalendarFunctionDateContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CalendarFunctionDayContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CalendarFunctionMonthContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CalendarFunctionTodayContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CalendarFunctionYearContext;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

abstract class CalendarVisitors extends MathematicsVisitors {

    public CalendarVisitors(final ExpressionContext expressionContext) {
        super(expressionContext);
    }

    @Override
    public final Object visitCalendarFunctionDay(final CalendarFunctionDayContext ctx) {
        Calendar cal = (Calendar) visit(ctx.dateExpresion());
        return BigDecimal.valueOf(cal.get(DAY_OF_MONTH));
    }

    @Override
    public final Object visitCalendarFunctionMonth(final CalendarFunctionMonthContext ctx) {
        Calendar cal = (Calendar) visit(ctx.dateExpresion());
        return BigDecimal.valueOf(cal.get(MONTH) + 1);
    }

    @Override
    public final Object visitCalendarFunctionYear(final CalendarFunctionYearContext ctx) {
        Calendar cal = (Calendar) visit(ctx.dateExpresion());
        return BigDecimal.valueOf(cal.get(YEAR));
    }

    @Override
    public final Object visitCalendarFunctionToday(final CalendarFunctionTodayContext ctx) {
        final Calendar value = getExecutionContext().get("today", Calendar.class);
        return value;
    }

    @Override
    public final Object visitCalendarFunctionDate(final CalendarFunctionDateContext ctx) {
        throw new IllegalStateException("not implemented");
    }
}