package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;

abstract class CalendarVisitors extends MathematicsVisitors {

  public CalendarVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }

}
