package com.github.thiagogarbazza.expressionresolve.compile;

import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;

final class ExpressionInputStream extends ANTLRInputStream {

  public ExpressionInputStream(Expression expression) {
    super(expression.getValue());
  }
}
