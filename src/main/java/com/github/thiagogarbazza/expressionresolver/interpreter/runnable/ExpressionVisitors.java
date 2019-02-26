package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
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

import static com.github.thiagogarbazza.expressionresolver.resolver.acos.ResolverFunctionAcos.getResolverFunctionAcos;
import static com.github.thiagogarbazza.expressionresolver.resolver.asin.ResolverFunctionAsin.getResolverFunctionAsin;
import static com.github.thiagogarbazza.expressionresolver.resolver.atan.ResolverFunctionAtan.getResolverFunctionAtan;
import static com.github.thiagogarbazza.expressionresolver.resolver.comparedate.ResolverFunctionCompareDate.getResolverFunctionCompareDate;
import static com.github.thiagogarbazza.expressionresolver.resolver.comparenumber.ResolverFunctionCompareNumber.getResolverFunctionCompareNumber;
import static com.github.thiagogarbazza.expressionresolver.resolver.comparestring.ResolverFunctionCompareString.getResolverFunctionCompareString;
import static com.github.thiagogarbazza.expressionresolver.resolver.cos.ResolverFunctionCos.getResolverFunctionCos;
import static com.github.thiagogarbazza.expressionresolver.resolver.date.ResolverFunctionDate.getResolverFunctionDate;
import static com.github.thiagogarbazza.expressionresolver.resolver.datesfromrange.ResolverFunctionDatesFromRange.getResolverFunctionDatesFromRange;
import static com.github.thiagogarbazza.expressionresolver.resolver.day.ResolverFunctionDay.getResolverFunctionDay;
import static com.github.thiagogarbazza.expressionresolver.resolver.ln.ResolverFunctionLn.getResolverFunctionLn;
import static com.github.thiagogarbazza.expressionresolver.resolver.log.ResolverFunctionLog.getResolverFunctionLog;
import static com.github.thiagogarbazza.expressionresolver.resolver.month.ResolverFunctionMonth.getResolverFunctionMonth;
import static com.github.thiagogarbazza.expressionresolver.resolver.primitive.ResolverPrimitiveString.getResolverPrimitiveString;
import static com.github.thiagogarbazza.expressionresolver.resolver.sin.ResolverFunctionSin.getResolverFunctionSin;
import static com.github.thiagogarbazza.expressionresolver.resolver.sqrt.ResolverFunctionSqrt.getResolverFunctionSqrt;
import static com.github.thiagogarbazza.expressionresolver.resolver.tan.ResolverFunctionTan.getResolverFunctionTan;
import static com.github.thiagogarbazza.expressionresolver.resolver.year.ResolverFunctionYear.getResolverFunctionYear;
import static com.github.thiagogarbazza.expressionresolver.util.LocalDateUtil.toLocalDate;
import static java.math.MathContext.DECIMAL128;
import static org.apache.commons.lang3.BooleanUtils.negate;
import static org.apache.commons.lang3.BooleanUtils.toBoolean;

class ExpressionVisitors extends ExpressionParserBaseVisitor<Object> {

  private final ExpressionContext expressionContext;

  @Getter
  private Object valueToBeReturned;

  public ExpressionVisitors(final ExpressionContext expressionContext) {
    this.expressionContext = expressionContext;
  }

  @Override
  public final Object visitParse(final ExpressionParser.ParseContext ctx) {
    return visit(ctx.block());
  }

  @Override
  public final Object visitAssignment(final ExpressionParser.AssignmentContext ctx) {
    final String variable = ctx.IDENTIFIER().getText();
    final Object value = visit(ctx.valueExpression());
    expressionContext.set(variable, value);

    return value;
  }

  @Override
  public final Object visitIfConditional(final ExpressionParser.IfConditionalContext ctx) {
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
  public final Object visitIterableExpression(final ExpressionParser.IterableExpressionContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    Iterator iterator = (Iterator) ((Iterable) visit(ctx.arrayExpression())).iterator();

    while (iterator.hasNext()) {
      expressionContext.set(identifier, iterator.next());
      visit(ctx.statementBlock());
    }

    expressionContext.set(identifier, null);

    return null;
  }

  @Override
  public Object visitArrayPushExpression(final ExpressionParser.ArrayPushExpressionContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    Collection array = (Collection) expressionContext.get(identifier);

    array.add(visit(ctx.valueExpression()));

    return null;
  }

  @Override
  public final Object visitReturnExpression(final ExpressionParser.ReturnExpressionContext ctx) {
    if (this.valueToBeReturned == null) {
      this.valueToBeReturned = visit(ctx.valueExpression());
    }

    return this.valueToBeReturned;
  }

  @Override
  public final Object visitCollectionBooleanExpresion(final ExpressionParser.CollectionBooleanExpresionContext ctx) {
    final Collection<Boolean> booleans = new ArrayList<>();

    ctx.vlExpBoolean().stream().forEach(context -> booleans.add((Boolean) visit(context)));

    return booleans;
  }

  @Override
  public final Object visitCollectionDateExpresion(final ExpressionParser.CollectionDateExpresionContext ctx) {
    final Collection<LocalDate> dates = new ArrayList<>();

    ctx.vlExpDate().stream().forEach(context -> dates.add((LocalDate) visit(context)));

    return dates;
  }

  @Override
  public final Object visitIdentifierDates(final ExpressionParser.IdentifierDatesContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public Object visitCollectionJsonExpresion(final ExpressionParser.CollectionJsonExpresionContext ctx) {
    final Collection<Map<String, Object>> booleans = new ArrayList<>();

    ctx.vlExpJson().stream().forEach(context -> booleans.add((Map<String, Object>) visit(context)));

    return booleans;
  }

  @Override
  public final Object visitCollectionNumberExpresion(final ExpressionParser.CollectionNumberExpresionContext ctx) {
    final Collection<BigDecimal> numbers = new ArrayList<>();

    ctx.vlExpNumber().stream().forEach(context -> numbers.add((BigDecimal) visit(context)));

    return numbers;
  }

  @Override
  public final Object visitIdentifierNumbers(final ExpressionParser.IdentifierNumbersContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitCollectionStringExpresion(final ExpressionParser.CollectionStringExpresionContext ctx) {
    final Collection<String> strings = new ArrayList<>();

    ctx.vlExpString().stream().forEach(context -> strings.add((String) visit(context)));

    return strings;
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

    return expressionContext.get(identifier);
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
  public final Object visitFunctionToday(final ExpressionParser.FunctionTodayContext ctx) {
    return expressionContext.getCurrentDate();
  }

  @Override
  public final Object visitIdentifierDate(final ExpressionParser.IdentifierDateContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitPrimitiveDate(final ExpressionParser.PrimitiveDateContext ctx) {
    final String date = ctx.getText();

    return toLocalDate(date);
  }

  @Override
  public final Object visitVlExpJson(final ExpressionParser.VlExpJsonContext ctx) {
    final Map<String, Object> map = new HashMap<>();

    ctx.vlExpJsonPair().stream().forEach(context -> {
      final String key = getResolverPrimitiveString().resolver(context.STRING().getText());
      map.put(key, visit(context.valueExpression()));
    });

    return map;
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

    return expressionContext.get(identifier);
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

    return expressionContext.get(identifier);
  }

  @Override
  public final Object visitPrimitiveString(final ExpressionParser.PrimitiveStringContext ctx) {
    return getResolverPrimitiveString().resolver(ctx.getText());
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

    return getResolverFunctionTan().resolver(value);
  }

  @Override
  public final Object visitFunctionYear(final ExpressionParser.FunctionYearContext ctx) {
    final LocalDate value = (LocalDate) visit(ctx.vlExpDate());

    return getResolverFunctionYear().resolver(value);
  }

  @Override
  public final Object visitFunctionDatesFromRange(final ExpressionParser.FunctionDatesFromRangeContext ctx) {
    final LocalDate left = (LocalDate) visit(ctx.vlExpDate(0));
    final LocalDate right = (LocalDate) visit(ctx.vlExpDate(1));

    return getResolverFunctionDatesFromRange().resolver(left, right);
  }
}
