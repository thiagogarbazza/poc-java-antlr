package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.interpreter.compile.CompilerService;
import com.github.thiagogarbazza.expressionresolver.interpreter.runnable.RunnableService;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.tree.ParseTree;

import static com.github.thiagogarbazza.expressionresolver.exception.SyntaxExpressionException.isExpressionNotEmpty;
import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.messageProperty;
import static com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.DependencyInjection.getDependencyInjection;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.Validate.notNull;

@Service
@RequiredArgsConstructor(access = PRIVATE, onConstructor_ = {@Inject})
public class ExpressionInterpreter {

  private final CompilerService compilerService;
  private final RunnableService runnableService;

  public Result toInterpret(final String expression, final ExpressionContext context) {
    isExpressionNotEmpty(expression);
    notNull(context, messageProperty("validation.context.not-be-null"));

    final ParseTree tree = compilerService.compile(expression);
    return runnableService.run(tree, context);
  }

  public void toValid(final String expression) {
    isExpressionNotEmpty(expression);

    compilerService.compile(expression);
  }

  public static ExpressionInterpreter getExpressionInterpreter() {
    return getDependencyInjection().getBean(ExpressionInterpreter.class);
  }
}
