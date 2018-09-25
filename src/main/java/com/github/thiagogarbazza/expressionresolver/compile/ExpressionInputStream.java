package com.github.thiagogarbazza.expressionresolver.compile;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;

final class ExpressionInputStream extends ANTLRInputStream {

  public ExpressionInputStream(Expression expression) {
    super(expression.getValue());
  }
}
