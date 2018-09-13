package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;

abstract class IdentifierVisitors extends PrimitiveVisitors {

  public IdentifierVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }


}
