package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.exception.SyntaxException;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CompareDateContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CompareStringContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.IfExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.ParseContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.StatementBlockContext;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

final class ExpressionVisitors extends BooleanVisitors {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

  public ExpressionVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }

  @Override
  public final Object visitParse(final ParseContext ctx) {
    return visit(ctx.block());
  }

  @Override
  public final Object visitIfExpression(final IfExpressionContext ctx) {
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
  public final Object visitCompareDate(final CompareDateContext ctx) {
    final Calendar left = (Calendar) visit(ctx.dateExpresion(0));
    final Calendar right = (Calendar) visit(ctx.dateExpresion(1));
    final Integer compare = left.compareTo(right);
    return resultNormatize(compare);
  }

  @Override
  public final Object visitCompareString(final CompareStringContext ctx) {
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
  public final Object visitPrimitiveBoolean(final ExpressionParser.PrimitiveBooleanContext ctx) {
    final String bool = ctx.getText();
    return new Boolean(bool);
  }

  @Override
  public final Object visitPrimitiveString(final ExpressionParser.PrimitiveStringContext ctx) {
    return ctx.getText();
  }
}
