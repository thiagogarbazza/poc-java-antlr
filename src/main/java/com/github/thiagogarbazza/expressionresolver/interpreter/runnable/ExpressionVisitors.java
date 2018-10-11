package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParser;
import com.github.thiagogarbazza.expressionresolver.parser.ExpressionParserBaseVisitor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.acos.FunctionResolverAcos.getFunctionResolverAcos;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.asin.FunctionResolverAsin.getFunctionResolverAsin;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.atan.FunctionResolverAtan.getFunctionResolverAtan;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.comparedate.FunctionResolverCompareDate.getFunctionResolverCompareDate;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.comparenumber.FunctionResolverCompareNumber.getFunctionResolverCompareNumber;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.comparestring.FunctionResolverCompareString.getFunctionResolverCompareString;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.cos.FunctionResolverCos.getFunctionResolverCos;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.date.FunctionResolverDate.getFunctionResolverDate;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.datesfromrange.FunctionResolverDatesFromRange.getFunctionResolverDatesFromRange;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.day.FunctionResolverDay.getFunctionResolverDay;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.ln.FunctionResolverLn.getFunctionResolverLn;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.log.FunctionResolverLog.getFunctionResolverLog;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.month.FunctionResolverMonth.getFunctionResolverMonth;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.sin.FunctionResolverSin.getFunctionResolverSin;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.sqrt.FunctionResolverSqrt.getFunctionResolverSqrt;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.tan.FunctionResolverTan.getFunctionResolverTan;
import static com.github.thiagogarbazza.expressionresolver.functionresolver.year.FunctionResolverYear.getFunctionResolvergetYear;
import static com.github.thiagogarbazza.expressionresolver.util.LocalDateUtil.toLocalDate;
import static java.math.MathContext.DECIMAL128;
import static org.apache.commons.lang3.BooleanUtils.negate;
import static org.apache.commons.lang3.BooleanUtils.toBoolean;
import static org.apache.commons.lang3.StringUtils.EMPTY;

final class ExpressionVisitors extends ExpressionParserBaseVisitor<Object> {

  private static final String END_QUOTES = "['\"]$";
  private static final String START_QUOTES = "^['\"]";

  private final ExpressionContext executionContext;

  @Getter
  private Object valueToBeReturned;

  public ExpressionVisitors(final ExpressionContext expressionContext) {
    this.executionContext = expressionContext;
  }

  @Override
  public final Object visitParse(final ExpressionParser.ParseContext ctx) {
    return visit(ctx.block());
  }

  @Override
  public final Object visitAssignment(final ExpressionParser.AssignmentContext ctx) {
    final String variable = ctx.IDENTIFIER().getText();
    final Object value = visit(ctx.expression());
    executionContext.set(variable, value);

    return value;
  }

  @Override
  public Object visitIterableExpression(final ExpressionParser.IterableExpressionContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    Iterator iterator = (Iterator) ((Iterable) visit(ctx.collectionExpression())).iterator();

    while (iterator.hasNext()) {
      executionContext.set(identifier, iterator.next());
      visit(ctx.statementBlock());
    }

    return null;
  }

  @Override
  public Object visitReturnExpression(final ExpressionParser.ReturnExpressionContext ctx) {
    if (this.valueToBeReturned == null) {
      this.valueToBeReturned = visit(ctx.expression());
    }

    return this.valueToBeReturned;
  }

  @Override
  public Object visitIfConditional(final ExpressionParser.IfConditionalContext ctx) {
    int i = -1;
    ExpressionParser.VlExpBooleanContext vlExpBooleanCtx;

    do {
      i++;
      vlExpBooleanCtx = ctx.vlExpBoolean(i);
    } while (vlExpBooleanCtx != null && !(Boolean) visit(vlExpBooleanCtx));

    final ExpressionParser.StatementBlockContext statementBlock = ctx.statementBlock(i);
    if (statementBlock != null) {
      return visit(statementBlock);
    }

    return null;
  }

  @Override
  public Object visitVlExpJson(final ExpressionParser.VlExpJsonContext ctx) {
    final Map<String, Object> map = new HashMap<>();

    ctx.vlExpJsonPair().stream().forEach(context -> {
      final String key = context.STRING().getText().replaceAll(START_QUOTES, EMPTY).replaceAll(END_QUOTES, EMPTY);
      map.put(key, visit(context.expression()));
    });

    return map;
  }

  @Override
  public final Object visitBooleanGroupedBy(final ExpressionParser.BooleanGroupedByContext ctx) {
    return visit(ctx.vlExpBoolean());
  }

  @Override
  public Object visitBooleanNumberComparison(final ExpressionParser.BooleanNumberComparisonContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.vlExpNumber(0));
    final BigDecimal right = (BigDecimal) visit(ctx.vlExpNumber(1));
    Comparison comparison = Comparison.findByOperator(ctx.op.getText());

    return comparison.compare(left, right);
  }

  @Override
  public final Object visitIdentifierBoolean(final ExpressionParser.IdentifierBooleanContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return executionContext.get(identifier);
  }

  @Override
  public final Object visitBooleanOR(final ExpressionParser.BooleanORContext ctx) {
    Boolean left = (Boolean) visit(ctx.vlExpBoolean(0));
    Boolean right = (Boolean) visit(ctx.vlExpBoolean(1));

    return left || right;
  }

  @Override
  public final Object visitPrimitiveBoolean(final ExpressionParser.PrimitiveBooleanContext ctx) {
    final String bool = ctx.getText();

    return toBoolean(bool);
  }

  @Override
  public final Object visitBooleanNegation(final ExpressionParser.BooleanNegationContext ctx) {
    Boolean value = (Boolean) visit(ctx.vlExpBoolean());

    return negate(value);
  }

  @Override
  public final Object visitBooleanAND(final ExpressionParser.BooleanANDContext ctx) {
    Boolean left = (Boolean) visit(ctx.vlExpBoolean(0));
    Boolean right = (Boolean) visit(ctx.vlExpBoolean(1));

    return left && right;
  }

  @Override
  public final Object visitIdentifierDate(final ExpressionParser.IdentifierDateContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return executionContext.get(identifier);
  }

  @Override
  public final Object visitPrimitiveDate(final ExpressionParser.PrimitiveDateContext ctx) {
    final String date = ctx.getText();

    return toLocalDate(date);
  }

  @Override
  public Object visitCollectionBooleanExpresion(final ExpressionParser.CollectionBooleanExpresionContext ctx) {
    final Collection<Boolean> booleans = new ArrayList<>();

    ctx.vlExpBoolean().stream().forEach(context -> booleans.add((Boolean) visit(context)));

    return booleans;
  }

  @Override
  public Object visitCollectionStringExpresion(final ExpressionParser.CollectionStringExpresionContext ctx) {
    final Collection<String> strings = new ArrayList<>();

    ctx.vlExpString().stream().forEach(context -> strings.add((String) visit(context)));

    return strings;
  }

  @Override
  public Object visitCollectionDateExpresion(final ExpressionParser.CollectionDateExpresionContext ctx) {
    final Collection<LocalDate> dates = new ArrayList<>();

    ctx.vlExpDate().stream().forEach(context -> dates.add((LocalDate) visit(context)));

    return dates;
  }

  @Override
  public Object visitIdentifierDates(final ExpressionParser.IdentifierDatesContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return executionContext.get(identifier);
  }

  @Override
  public Object visitCollectionNumberExpresion(final ExpressionParser.CollectionNumberExpresionContext ctx) {
    final Collection<BigDecimal> numbers = new ArrayList<>();

    ctx.vlExpNumber().stream().forEach(context -> numbers.add((BigDecimal) visit(context)));

    return numbers;
  }

  @Override
  public Object visitIdentifierNumbers(final ExpressionParser.IdentifierNumbersContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return executionContext.get(identifier);
  }

  @Override
  public final Object visitNumberOperationSign(final ExpressionParser.NumberOperationSignContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.vlExpNumber());
    if (ctx.MINUS() != null) {
      return result.multiply(BigDecimal.valueOf(-1));
    }

    return result;
  }

  @Override
  public final Object visitIdentifierNumber(final ExpressionParser.IdentifierNumberContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return executionContext.get(identifier);
  }

  @Override
  public final Object visitNumberOperationSubtract(final ExpressionParser.NumberOperationSubtractContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.vlExpNumber(0));
    for (int i = 1; i < ctx.vlExpNumber().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.vlExpNumber(i));
      result = result.subtract(child);
    }

    return result;
  }

  @Override
  public final Object visitNumberOperationPow(final ExpressionParser.NumberOperationPowContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.vlExpNumber(0));
    final BigDecimal right = (BigDecimal) visit(ctx.vlExpNumber(1));

    return left.pow(right.intValue());
  }

  @Override
  public final Object visitNumberOperationMultiply(final ExpressionParser.NumberOperationMultiplyContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.vlExpNumber(0));
    for (int i = 1; i < ctx.vlExpNumber().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.vlExpNumber(i));
      result = result.multiply(child);
    }

    return result;
  }

  @Override
  public final Object visitNumberOperationDivide(final ExpressionParser.NumberOperationDivideContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.vlExpNumber(0));
    for (int i = 1; i < ctx.vlExpNumber().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.vlExpNumber(i));
      result = result.divide(child, DECIMAL128);
    }

    return result;
  }

  @Override
  public final Object visitPrimitiveNumber(final ExpressionParser.PrimitiveNumberContext ctx) {
    return new BigDecimal(ctx.getText());
  }

  @Override
  public final Object visitNumberOperationAddition(final ExpressionParser.NumberOperationAdditionContext ctx) {
    BigDecimal result = BigDecimal.ZERO;
    for (ExpressionParser.VlExpNumberContext vlExpNumberCtx : ctx.vlExpNumber()) {
      BigDecimal child = (BigDecimal) visit(vlExpNumberCtx);
      result = result.add(child);
    }

    return result;
  }

  @Override
  public final Object visitNumberOperationModulo(final ExpressionParser.NumberOperationModuloContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.vlExpNumber(0));
    final BigDecimal right = (BigDecimal) visit(ctx.vlExpNumber(1));

    return left.remainder(right);
  }

  @Override
  public final Object visitNumberGroupedBy(final ExpressionParser.NumberGroupedByContext ctx) {
    return visit(ctx.vlExpNumber());
  }

  @Override
  public final Object visitIdentifierString(final ExpressionParser.IdentifierStringContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return executionContext.get(identifier);
  }

  @Override
  public final Object visitPrimitiveString(final ExpressionParser.PrimitiveStringContext ctx) {
    return ctx.getText().replaceAll(START_QUOTES, EMPTY).replaceAll(END_QUOTES, EMPTY);
  }

  @Override
  public Object visitFunctionDate(final ExpressionParser.FunctionDateContext ctx) {
    BigDecimal year = (BigDecimal) visit(ctx.vlExpNumber(0));
    BigDecimal month = (BigDecimal) visit(ctx.vlExpNumber(1));
    BigDecimal day = (BigDecimal) visit(ctx.vlExpNumber(2));

    return getFunctionResolverDate().resolver(year, month, day);
  }

  @Override
  public Object visitFunctionToday(final ExpressionParser.FunctionTodayContext ctx) {
    return executionContext.get("today", LocalDate.class);
  }

  @Override
  public Object visitFunctionDatesFromRange(final ExpressionParser.FunctionDatesFromRangeContext ctx) {
    final LocalDate left = (LocalDate) visit(ctx.vlExpDate(0));
    final LocalDate right = (LocalDate) visit(ctx.vlExpDate(1));

    return getFunctionResolverDatesFromRange().resolver(left, right);
  }

  @Override
  public Object visitFunctionAcos(final ExpressionParser.FunctionAcosContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverAcos().resolver(value);
  }

  @Override
  public Object visitFunctionAsin(final ExpressionParser.FunctionAsinContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverAsin().resolver(value);
  }

  @Override
  public Object visitFunctionAtan(final ExpressionParser.FunctionAtanContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverAtan().resolver(value);
  }

  @Override
  public Object visitFunctionCompareDates(final ExpressionParser.FunctionCompareDatesContext ctx) {
    final LocalDate left = (LocalDate) visit(ctx.vlExpDate(0));
    final LocalDate right = (LocalDate) visit(ctx.vlExpDate(1));

    return getFunctionResolverCompareDate().resolver(left, right);
  }

  @Override
  public Object visitFunctionCompareStrings(final ExpressionParser.FunctionCompareStringsContext ctx) {
    final String left = (String) visit(ctx.vlExpString(0));
    final String right = (String) visit(ctx.vlExpString(1));

    return getFunctionResolverCompareString().resolver(left, right);
  }

  @Override
  public Object visitFunctionCompareNumbers(final ExpressionParser.FunctionCompareNumbersContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.vlExpNumber(0));
    final BigDecimal right = (BigDecimal) visit(ctx.vlExpNumber(1));

    return getFunctionResolverCompareNumber().resolver(left, right);
  }

  @Override
  public Object visitFunctionCos(final ExpressionParser.FunctionCosContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverCos().resolver(value);
  }

  @Override
  public Object visitFunctionDay(final ExpressionParser.FunctionDayContext ctx) {
    final LocalDate value = (LocalDate) visit(ctx.vlExpDate());

    return getFunctionResolverDay().resolver(value);
  }

  @Override
  public Object visitFunctionLn(final ExpressionParser.FunctionLnContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverLn().resolver(value);
  }

  @Override
  public Object visitFunctionLog(final ExpressionParser.FunctionLogContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverLog().resolver(value);
  }

  @Override
  public Object visitFunctionMonth(final ExpressionParser.FunctionMonthContext ctx) {
    final LocalDate value = (LocalDate) visit(ctx.vlExpDate());

    return getFunctionResolverMonth().resolver(value);
  }

  @Override
  public Object visitFunctionSin(final ExpressionParser.FunctionSinContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverSin().resolver(value);
  }

  @Override
  public Object visitFunctionSqrt(final ExpressionParser.FunctionSqrtContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverSqrt().resolver(value);
  }

  @Override
  public Object visitFunctionTan(final ExpressionParser.FunctionTanContext ctx) {
    final BigDecimal value = (BigDecimal) visit(ctx.vlExpNumber());

    return getFunctionResolverTan().resolver(value);
  }

  @Override
  public Object visitFunctionYear(final ExpressionParser.FunctionYearContext ctx) {
    final LocalDate value = (LocalDate) visit(ctx.vlExpDate());

    return getFunctionResolvergetYear().resolver(value);
  }
}
