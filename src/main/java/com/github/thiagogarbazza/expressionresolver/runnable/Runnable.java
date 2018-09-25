package com.github.thiagogarbazza.expressionresolver.runnable;

import com.github.thiagogarbazza.expressionresolver.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import org.antlr.v4.runtime.tree.ParseTree;

public final class Runnable {

  public Result run(final ParseTree tree, final ExpressionContext expressionContext) {
    ExpressionVisitors visitor = new ExpressionVisitors(expressionContext);

    final Object result = visitor.visit(tree);
    return new Result(visitor.getValueToBeReturned());
  }
}
