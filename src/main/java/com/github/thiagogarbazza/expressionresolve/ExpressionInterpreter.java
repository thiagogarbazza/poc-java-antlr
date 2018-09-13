package com.github.thiagogarbazza.expressionresolve;

import com.github.thiagogarbazza.expressionresolve.compile.Compiler;
import com.github.thiagogarbazza.expressionresolve.domain.Expression;
import com.github.thiagogarbazza.expressionresolve.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolve.domain.Result;
import com.github.thiagogarbazza.expressionresolve.exception.ExpressionException;
import com.github.thiagogarbazza.expressionresolve.runnable.Runnable;
import org.antlr.v4.runtime.tree.ParseTree;

public final class ExpressionInterpreter {

  private final Compiler compiler = new Compiler();
  private final Runnable runnable = new Runnable();

  public Result toInterpret(final Expression expression, final ExpressionContext context) throws ExpressionException {
    ParseTree tree = compiler.compile(expression);
    return runnable.run(tree, context);
  }

  public void toValid(final Expression expression) throws ExpressionException {
    compiler.compile(expression);
  }
}
