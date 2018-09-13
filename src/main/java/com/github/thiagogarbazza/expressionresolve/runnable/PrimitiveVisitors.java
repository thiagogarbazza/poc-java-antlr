package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.exception.SyntaxException;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.PrimitiveBooleanContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.PrimitiveDateContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.PrimitiveNumberContext;
import com.github.thiagogarbazza.expressionresolve.parser.ExpressionParser.PrimitiveStringContext;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

abstract class PrimitiveVisitors extends AbstractVisitors {

  public PrimitiveVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }

  @Override
  public final Object visitPrimitiveNumber(final PrimitiveNumberContext ctx) {
    final BigDecimal result = new BigDecimal(ctx.getText());
    return result;
  }

  @Override
  public final Object visitPrimitiveDate(final PrimitiveDateContext ctx) {
    final String date = ctx.getText();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    try {
      Calendar cal = Calendar.getInstance();
      cal.setTime(formatter.parse(date));
      return cal;
    } catch (ParseException e) {
      throw new SyntaxException("Error format date.", e);
    }
  }

  @Override
  public final Object visitPrimitiveBoolean(final PrimitiveBooleanContext ctx) {
    final String bool = ctx.getText();
    return new Boolean(bool);
  }

  @Override
  public final Object visitPrimitiveString(final PrimitiveStringContext ctx) {
    return ctx.getText();
  }
}
