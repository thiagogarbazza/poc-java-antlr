package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.BooleanExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CompareDateContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.CompareStringContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.IfExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.ParseContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.StatementBlockContext;

import java.util.Calendar;

final class ExpressionVisitors extends BooleanVisitors {

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
}
