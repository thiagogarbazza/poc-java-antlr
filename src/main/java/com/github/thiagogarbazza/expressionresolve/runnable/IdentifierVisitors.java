package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.AssignmentContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.IdentifierBooleanContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.IdentifierDateContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.IdentifierNumberContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.IdentifierStringContext;

import java.math.BigDecimal;
import java.util.Calendar;

abstract class IdentifierVisitors extends PrimitiveVisitors {

  public IdentifierVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }

  @Override
  public Object visitAssignment(final AssignmentContext ctx) {
    final String variable = ctx.IDENTIFIER().getText();
    final Object value = visit(ctx.expression());
    getExecutionContext().set(variable, value);
    return value;
  }

  @Override
  public final Object visitIdentifierNumber(final IdentifierNumberContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final BigDecimal value = getExecutionContext().get(identifier, BigDecimal.class);
    return value;
  }

  @Override
  public final Object visitIdentifierDate(final IdentifierDateContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final Calendar value = getExecutionContext().get(identifier, Calendar.class);
    return value;
  }

  @Override
  public final Object visitIdentifierBoolean(final IdentifierBooleanContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final Boolean value = getExecutionContext().get(identifier, Boolean.class);
    return value;
  }

  @Override
  public final Object visitIdentifierString(final IdentifierStringContext ctx) {
    final String identifier = ctx.IDENTIFIER().getText();
    final String value = getExecutionContext().get(identifier, String.class);
    return value;
  }
}
