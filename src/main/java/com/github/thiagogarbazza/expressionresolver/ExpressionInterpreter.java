package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.interpreter.compile.Compiler;
import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.interpreter.runnable.Runnable;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.Compiler.getCompiler;
import static com.github.thiagogarbazza.expressionresolver.interpreter.runnable.Runnable.getRunnable;

public final class ExpressionInterpreter {

  private final Compiler compiler = getCompiler();
  private final Runnable runnable = getRunnable();

  public Result toInterpret(final Expression expression, final ExpressionContext context) {
    ParseTree tree = compiler.compile(expression);
    return runnable.run(tree, context);
  }

  public void toValid(final Expression expression) {
    compiler.compile(expression);
  }
}
