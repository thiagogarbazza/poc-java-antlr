package com.github.thiagogarbazza.expressionresolve.runnable;

import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;

abstract class MathematicsVisitors extends IdentifierVisitors {

  public MathematicsVisitors(final ExpressionContext expressionContext) {
    super(expressionContext);
  }


}
