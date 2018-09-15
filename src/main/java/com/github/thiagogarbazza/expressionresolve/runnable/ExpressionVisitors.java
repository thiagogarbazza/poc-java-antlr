package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.exception.SyntaxException;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.StatementBlockContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParserBaseVisitor;
import org.apache.commons.lang3.BooleanUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.math.MathContext.DECIMAL128;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

final class ExpressionVisitors extends ExpressionParserBaseVisitor<Object> {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

  private final ExpressionContext executionContext;

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
  public final Object visitIfExpression(final ExpressionParser.IfExpressionContext ctx) {
    int i = -1;
    BooleanExpressionContext booleanExpression = null;
    do {
      i++;
      booleanExpression = ctx.booleanExpression(i);
    } while (booleanExpression != null && !(Boolean) visit(booleanExpression));

    final StatementBlockContext statementBlock = ctx.statementBlock(i);
    if (statementBlock != null) {
      Object response = response = visit(statementBlock);
      return response;
    }

    return null;
  }

  @Override
  public final Object visitMathematicsOperationPow(final ExpressionParser.MathematicsOperationPowContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.arithmeticExpression(0));
    final BigDecimal right = (BigDecimal) visit(ctx.arithmeticExpression(1));
    return left.pow(right.intValue());
  }

  @Override
  public final Object visitMathematicsOperationSign(final ExpressionParser.MathematicsOperationSignContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression());
    if (ctx.MINUS() != null) {
      return result.multiply(BigDecimal.valueOf(-1));
    }
    return result;
  }

  @Override
  public final Object visitMathematicsOperationDivide(final ExpressionParser.MathematicsOperationDivideContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression(0));
    for (int i = 1; i < ctx.arithmeticExpression().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression(i));
      result = result.divide(child, DECIMAL128);
    }
    return result;
  }

  @Override
  public final Object visitMathematicsOperationSubtract(final ExpressionParser.MathematicsOperationSubtractContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression(0));
    for (int i = 1; i < ctx.arithmeticExpression().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression(i));
      result = result.subtract(child);
    }
    return result;
  }

  @Override
  public final Object visitMathematicsOperationModulo(final ExpressionParser.MathematicsOperationModuloContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.arithmeticExpression(0));
    final BigDecimal right = (BigDecimal) visit(ctx.arithmeticExpression(1));
    return left.remainder(right);
  }

  @Override
  public final Object visitMathematicsFunctions(final ExpressionParser.MathematicsFunctionsContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.functionsExpression());
    return result;
  }

  @Override
  public final Object visitMathematicsOperationAddition(final ExpressionParser.MathematicsOperationAdditionContext ctx) {
    BigDecimal result = BigDecimal.ZERO;
    for (ExpressionParser.ArithmeticExpressionContext aectx : ctx.arithmeticExpression()) {
      BigDecimal child = (BigDecimal) visit(aectx);
      result = result.add(child);
    }
    return result;
  }

  @Override
  public final Object visitMathematicsGroupedBy(final ExpressionParser.MathematicsGroupedByContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression());
    return result;
  }

  @Override
  public final Object visitMathematicsNumeric(final ExpressionParser.MathematicsNumericContext ctx) {
    final BigDecimal result = (BigDecimal) visit(ctx.numberExpresion());
    return result;
  }

  @Override
  public final Object visitMathematicsOperationMultiply(final ExpressionParser.MathematicsOperationMultiplyContext ctx) {
    BigDecimal result = (BigDecimal) visit(ctx.arithmeticExpression(0));
    for (int i = 1; i < ctx.arithmeticExpression().size(); i++) {
      final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression(i));
      result = result.multiply(child);
    }
    return result;
  }

  @Override
  public final Object visitMathematicsFunctionCos(final ExpressionParser.MathematicsFunctionCosContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double cos = Math.cos(degrees);

    final BigDecimal result = BigDecimal.valueOf(cos);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionAcos(final ExpressionParser.MathematicsFunctionAcosContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double acos = Math.acos(degrees);

    final BigDecimal result = BigDecimal.valueOf(acos);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionSin(final ExpressionParser.MathematicsFunctionSinContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double sin = Math.sin(degrees);

    final BigDecimal result = BigDecimal.valueOf(sin);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionAsin(final ExpressionParser.MathematicsFunctionAsinContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double asin = Math.asin(degrees);

    final BigDecimal result = BigDecimal.valueOf(asin);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionTan(final ExpressionParser.MathematicsFunctionTanContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double tan = Math.tan(degrees);

    final BigDecimal result = BigDecimal.valueOf(tan);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionAtan(final ExpressionParser.MathematicsFunctionAtanContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double degrees = child.doubleValue();
    final double atan = Math.atan(degrees);

    final BigDecimal result = BigDecimal.valueOf(atan);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionLn(final ExpressionParser.MathematicsFunctionLnContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double value = child.doubleValue();
    final double log = Math.log(value);

    final BigDecimal result = BigDecimal.valueOf(log);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionLog(final ExpressionParser.MathematicsFunctionLogContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double value = child.doubleValue();
    final double log = Math.log10(value);

    final BigDecimal result = BigDecimal.valueOf(log);
    return resultNormatize(result);
  }

  @Override
  public final Object visitMathematicsFunctionSqrt(final ExpressionParser.MathematicsFunctionSqrtContext ctx) {
    final BigDecimal child = (BigDecimal) visit(ctx.arithmeticExpression());
    final double value = child.doubleValue();
    final double sqrt = Math.sqrt(value);

    final BigDecimal result = BigDecimal.valueOf(sqrt);
    return resultNormatize(result);
  }

  @Override
  public final Object visitCalendarFunctionDay(final ExpressionParser.CalendarFunctionDayContext ctx) {
    Calendar cal = (Calendar) visit(ctx.dateExpresion());
    return BigDecimal.valueOf(cal.get(DAY_OF_MONTH));
  }

  @Override
  public final Object visitCalendarFunctionMonth(final ExpressionParser.CalendarFunctionMonthContext ctx) {
    Calendar cal = (Calendar) visit(ctx.dateExpresion());
    return BigDecimal.valueOf(cal.get(MONTH) + 1);
  }

  @Override
  public final Object visitCalendarFunctionYear(final ExpressionParser.CalendarFunctionYearContext ctx) {
    Calendar cal = (Calendar) visit(ctx.dateExpresion());
    return BigDecimal.valueOf(cal.get(YEAR));
  }

  @Override
  public final Object visitPrimitiveNumber(final ExpressionParser.PrimitiveNumberContext ctx) {
    final BigDecimal result = new BigDecimal(ctx.getText());
    return result;
  }

  @Override
  public final Object visitIdentifierNumber(final ExpressionParser.IdentifierNumberContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final BigDecimal value = executionContext.get(identifier, BigDecimal.class);
    return value;
  }

  @Override
  public Object visitCompareNumbers(final ExpressionParser.CompareNumbersContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.numberExpresion(0));
    final BigDecimal right = (BigDecimal) visit(ctx.numberExpresion(1));
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  @Override
  public Object visitCompareStrings(final ExpressionParser.CompareStringsContext ctx) {
    final String left = (String) visit(ctx.stringExpression(0));
    final String right = (String) visit(ctx.stringExpression(1));
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  @Override
  public Object visitCompareDates(final ExpressionParser.CompareDatesContext ctx) {
    final Calendar left = (Calendar) visit(ctx.dateExpresion(0));
    final Calendar right = (Calendar) visit(ctx.dateExpresion(1));
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  @Override
  public final Object visitCalendarFunctionDate(final ExpressionParser.CalendarFunctionDateContext ctx) {
    throw new IllegalStateException("not implemented");
  }

  @Override
  public final Object visitCalendarFunctionToday(final ExpressionParser.CalendarFunctionTodayContext ctx) {
    final Calendar value = executionContext.get("today", Calendar.class);
    return value;
  }

  @Override
  public final Object visitPrimitiveDate(final ExpressionParser.PrimitiveDateContext ctx) {
    final String date = ctx.getText();
    try {
      Calendar cal = Calendar.getInstance();
      cal.setTime(DATE_FORMAT.parse(date));
      return cal;
    } catch (ParseException e) {
      throw new SyntaxException("Error format date.", e);
    }
  }

  @Override
  public final Object visitIdentifierDate(final ExpressionParser.IdentifierDateContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final Calendar value = executionContext.get(identifier, Calendar.class);
    return value;
  }

  @Override
  public final Object visitBooleanGroupedBy(final ExpressionParser.BooleanGroupedByContext ctx) {
    Boolean value = (Boolean) visit(ctx.booleanExpression());
    return value;
  }

  @Override
  public final Object visitIdentifierBoolean(final ExpressionParser.IdentifierBooleanContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final Boolean value = executionContext.get(identifier, Boolean.class);
    return value;
  }

  @Override
  public final Object visitBooleanArithmeticComparison(final ExpressionParser.BooleanArithmeticComparisonContext ctx) {
    final BigDecimal left = (BigDecimal) visit(ctx.arithmeticExpression(0));
    final BigDecimal right = (BigDecimal) visit(ctx.arithmeticExpression(1));
    Comparison comparison = Comparison.findByOperator(ctx.op.getText());
    return comparison.compare(left, right);
  }

  @Override
  public final Object visitBooleanOR(final ExpressionParser.BooleanORContext ctx) {
    Boolean left = (Boolean) visit(ctx.booleanExpression(0));
    Boolean right = (Boolean) visit(ctx.booleanExpression(1));
    return left || right;
  }

  @Override
  public final Object visitPrimitiveBoolean(final ExpressionParser.PrimitiveBooleanContext ctx) {
    final String bool = ctx.getText();
    return new Boolean(bool);
  }

  @Override
  public final Object visitBooleanNegation(final ExpressionParser.BooleanNegationContext ctx) {
    Boolean value = (Boolean) visit(ctx.booleanExpression());
    return BooleanUtils.negate(value);
  }

  @Override
  public final Object visitBooleanAND(final ExpressionParser.BooleanANDContext ctx) {
    Boolean left = (Boolean) visit(ctx.booleanExpression(0));
    Boolean right = (Boolean) visit(ctx.booleanExpression(1));
    return left && right;
  }

  @Override
  public final Object visitPrimitiveString(final ExpressionParser.PrimitiveStringContext ctx) {
    return ctx.getText();
  }

  @Override
  public final Object visitIdentifierString(final ExpressionParser.IdentifierStringContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final String value = executionContext.get(identifier, String.class);
    return value;
  }

  private final Object normalizeResultCompare(Integer result) {
    if (result == 0) {
      return resultNormatize(0);
    }

    return result < 0
      ? resultNormatize(-1)
      : resultNormatize(1);
  }

  private final Object resultNormatize(final Integer result) {
    return resultNormatize(BigDecimal.valueOf(result));
  }

  private final Object resultNormatize(final BigDecimal result) {
    return result;
  }
}
