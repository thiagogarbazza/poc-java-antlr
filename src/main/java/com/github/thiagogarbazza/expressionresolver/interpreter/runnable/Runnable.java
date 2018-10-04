package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.tree.ParseTree;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Runnable {

  private static final Runnable INSTANCE = new Runnable();

  public Result run(final ParseTree tree, final ExpressionContext expressionContext) {
    ExpressionVisitors visitor = new ExpressionVisitors(expressionContext);

    final Object result = visitor.visit(tree);
    return new Result(visitor.getValueToBeReturned());
  }

  public static Runnable getRunnable() {
    return INSTANCE;
  }
}
