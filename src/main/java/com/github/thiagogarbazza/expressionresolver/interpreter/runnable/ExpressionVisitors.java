package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.acos.ResolverFunctionAcos.getResolverFunctionAcos;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.asin.ResolverFunctionAsin.getResolverFunctionAsin;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.atan.ResolverFunctionAtan.getResolverFunctionAtan;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.comparedate.ResolverFunctionCompareDate.getResolverFunctionCompareDate;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.comparenumber.ResolverFunctionCompareNumber.getResolverFunctionCompareNumber;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.comparestring.ResolverFunctionCompareString.getResolverFunctionCompareString;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.cos.ResolverFunctionCos.getResolverFunctionCos;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.date.ResolverFunctionDate.getResolverFunctionDate;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.datesfromrange.ResolverFunctionDatesFromRange.getResolverFunctionDatesFromRange;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.day.ResolverFunctionDay.getResolverFunctionDay;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.ln.ResolverFunctionLn.getResolverFunctionLn;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.log.ResolverFunctionLog.getResolverFunctionLog;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.month.ResolverFunctionMonth.getResolverFunctionMonth;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.sin.ResolverFunctionSin.getResolverFunctionSin;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.sqrt.ResolverFunctionSqrt.getResolverFunctionSqrt;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.tan.FunctionResolverTan.getFunctionResolverTan;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.year.FunctionResolverYear.getFunctionResolvergetYear;

class ExpressionVisitors extends ExpressionCoreVisitors {

  protected ExpressionVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }

  @Override
  public final Object visitFunctionDate(final ExpressionParser.FunctionDateContext ctx) {
    BigDecimal year = (BigDecimal) visit(ctx.vlExpNumber(0));
    BigDecimal month = (BigDecimal) visit(ctx.vlExpNumber(1));
    BigDecimal day = (BigDecimal) visit(ctx.vlExpNumber(2));

    return getResolverFunctionDate().resolver(year, month, day);
  }

  @Override
  public final Object visitFunctionAcos(final ExpressionParser.FunctionAcosContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getResolverFunctionAcos().resolver(value);
  }

  @Override
  public final Object visitFunctionAsin(final ExpressionParser.FunctionAsinContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getResolverFunctionAsin().resolver(value);
  }

  @Override
  public final Object visitFunctionAtan(final ExpressionParser.FunctionAtanContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getResolverFunctionAtan().resolver(value);
  }

  @Override
  public final Object visitFunctionCompareDates(final ExpressionParser.FunctionCompareDatesContext ctx) {
    final LocalDate left = (LocalDate) visit(ctx.vlExpDate(0));
    final LocalDate right = (LocalDate) visit(ctx.vlExpDate(1));

    return getResolverFunctionCompareDate().resolver(left, right);
  }

  @Override
  public final Object visitFunctionCompareStrings(final ExpressionParser.FunctionCompareStringsContext ctx) {
    final String left = (String) visit(ctx.vlExpString(0));
    final String right = (String) visit(ctx.vlExpString(1));

    return getResolverFunctionCompareString().resolver(left, right);
  }

  @Override
  public final Object visitFunctionCompareNumbers(final ExpressionParser.FunctionCompareNumbersContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.vlExpNumber(0));
    final BigDecimal right = (BigDecimal) visit(ctx.vlExpNumber(1));

    return getResolverFunctionCompareNumber().resolver(left, right);
  }

  @Override
  public final Object visitFunctionCos(final ExpressionParser.FunctionCosContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getResolverFunctionCos().resolver(value);
  }

  @Override
  public final Object visitFunctionDay(final ExpressionParser.FunctionDayContext ctx) {
    final LocalDate value = (LocalDate) visit(ctx.vlExpDate());

    return getResolverFunctionDay().resolver(value);
  }

  @Override
  public final Object visitFunctionLn(final ExpressionParser.FunctionLnContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getResolverFunctionLn().resolver(value);
  }

  @Override
  public final Object visitFunctionLog(final ExpressionParser.FunctionLogContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getResolverFunctionLog().resolver(value);
  }

  @Override
  public final Object visitFunctionMonth(final ExpressionParser.FunctionMonthContext ctx) {
    final LocalDate value = (LocalDate) visit(ctx.vlExpDate());

    return getResolverFunctionMonth().resolver(value);
  }

  @Override
  public final Object visitFunctionSin(final ExpressionParser.FunctionSinContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getResolverFunctionSin().resolver(value);
  }

  @Override
  public final Object visitFunctionSqrt(final ExpressionParser.FunctionSqrtContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getResolverFunctionSqrt().resolver(value);
  }

  @Override
  public final Object visitFunctionTan(final ExpressionParser.FunctionTanContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverTan().resolver(value);
  }

  @Override
  public final Object visitFunctionYear(final ExpressionParser.FunctionYearContext ctx) {
    final LocalDate value = (LocalDate) visit(ctx.vlExpDate());

    return getFunctionResolvergetYear().resolver(value);
  }

  @Override
  public final Object visitFunctionDatesFromRange(final ExpressionParser.FunctionDatesFromRangeContext ctx) {
    final LocalDate left = (LocalDate) visit(ctx.vlExpDate(0));
    final LocalDate right = (LocalDate) visit(ctx.vlExpDate(1));

    return getResolverFunctionDatesFromRange().resolver(left, right);
  }
}
