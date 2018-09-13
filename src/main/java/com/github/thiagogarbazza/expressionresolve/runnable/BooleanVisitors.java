package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;

abstract class BooleanVisitors extends CalendarVisitors {

  public BooleanVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }

}
