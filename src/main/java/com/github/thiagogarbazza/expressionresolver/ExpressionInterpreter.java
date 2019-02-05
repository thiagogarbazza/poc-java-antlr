package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.interpreter.compile.Compiler;
import com.github.thiagogarbazza.expressionresolver.interpreter.runnable.Runnable;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.Compiler.getCompiler;
import static com.github.thiagogarbazza.expressionresolver.interpreter.runnable.Runnable.getRunnable;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ExpressionInterpreter {

  private static final ExpressionInterpreter INSTANCE = new ExpressionInterpreter();

  private Compiler compiler = getCompiler();
  private Runnable runnable = getRunnable();

  public Result toInterpret(final Expression expression, final ExpressionContext context) {
    ParseTree tree = compiler.compile(expression);
    return runnable.run(tree, context);
  }

  public void toValid(final Expression expression) {
    compiler.compile(expression);
  }

  public static ExpressionInterpreter getExpressionInterpreter() {
    return INSTANCE;
  }
}
