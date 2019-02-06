package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.interpreter.compile.Compiler;
import com.github.thiagogarbazza.expressionresolver.interpreter.runnable.Runnable;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.github.thiagogarbazza.expressionresolver.interpreter.compile.Compiler.getCompiler;
import static com.github.thiagogarbazza.expressionresolver.interpreter.runnable.Runnable.getRunnable;
import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.messageProperty;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.Validate.notNull;

@NoArgsConstructor(access = PRIVATE)
public class ExpressionInterpreter {

  private static ExpressionInterpreter instance;

  private Compiler compiler = getCompiler();
  private Runnable runnable = getRunnable();

  public Result toInterpret(final Expression expression, final ExpressionContext context) {
    notNull(expression, messageProperty("validation.expression.not-be-null-or-empty"));
    notNull(context, messageProperty("validation.context.not-be-null"));

    ParseTree tree = compiler.compile(expression);
    return runnable.run(tree, context);
  }

  public void toValid(final Expression expression) {
    notNull(expression, messageProperty("validation.expression.not-be-null-or-empty"));

    compiler.compile(expression);
  }

  public static ExpressionInterpreter getExpressionInterpreter() {
    if (instance == null) {
      instance = new ExpressionInterpreter();
    }

    return instance;
  }
}
