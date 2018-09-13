package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;

abstract class PrimitiveVisitors extends AbstractVisitors {

  public PrimitiveVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }


}
