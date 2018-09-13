package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.exception.SyntaxException;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.StatementBlockContext;
import org.apache.commons.lang3.BooleanUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

final class ExpressionVisitors extends BooleanVisitors {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

  private final ExpressionContext executionContext;

  public ExpressionVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
    this.executionContext = expressionContext;
  }

  @Override
  public final Object visitParse(final ExpressionParser.ParseContext ctx) {
    return visit(ctx.block());
  }

  @Override
  public Object visitAssignment(final ExpressionParser.AssignmentContext ctx) {
    final String variable = ctx.IDENTIFIER().getText();
    final Object value = visit(ctx.expression());
    getExecutionContext().set(variable, value);
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
  public final Object visitCompareDate(final ExpressionParser.CompareDateContext ctx) {
    final Calendar left = (Calendar) visit(ctx.dateExpresion(0));
    final Calendar right = (Calendar) visit(ctx.dateExpresion(1));
    final Integer compare = left.compareTo(right);
    return resultNormatize(compare);
  }

  @Override
  public final Object visitCompareString(final ExpressionParser.CompareStringContext ctx) {
    final String left = (String) visit(ctx.stringExpression(0));
    final String right = (String) visit(ctx.stringExpression(1));
    final Integer compare = left.compareTo(right);
    return resultNormatize(compare);
  }

  @Override
  public final Object visitPrimitiveNumber(final ExpressionParser.PrimitiveNumberContext ctx) {
    final BigDecimal result = new BigDecimal(ctx.getText());
    return result;
  }

  @Override
  public final Object visitIdentifierNumber(final ExpressionParser.IdentifierNumberContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final BigDecimal value = getExecutionContext().get(identifier, BigDecimal.class);
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
    final Calendar value = getExecutionContext().get(identifier, Calendar.class);
    return value;
  }

  @Override
  public Object visitBooleanGroupedBy(final ExpressionParser.BooleanGroupedByContext ctx) {
    Boolean value = (Boolean) visit(ctx.booleanExpression());
    return value;
  }

  @Override
  public final Object visitIdentifierBoolean(final ExpressionParser.IdentifierBooleanContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final Boolean value = getExecutionContext().get(identifier, Boolean.class);
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
  public Object visitBooleanOR(final ExpressionParser.BooleanORContext ctx) {
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
  public Object visitBooleanAND(final ExpressionParser.BooleanANDContext ctx) {
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
    final String value = getExecutionContext().get(identifier, String.class);
    return value;
  }
}
