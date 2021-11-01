package com.github.thiagogarbazza.expressionresolver.interpreter.runnable;

import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.Result;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.DependencyInjection;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Inject;
import com.github.thiagogarbazza.expressionresolver.util.dependencyinjection.Service;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.tree.ParseTree;

import static lombok.AccessLevel.PRIVATE;

@Service
@NoArgsConstructor(access = PRIVATE, onConstructor = @__(@Inject))
public class RunnableService {

  public Result run(final ParseTree tree, final ExpressionContext expressionContext) {
    final ExpressionVisitors expressionVisitors = DependencyInjection.getDependencyInjection().getBeanRequest(ExpressionVisitors.class, expressionContext);

    expressionVisitors.visit(tree);

    return new Result(expressionVisitors.getValueToBeReturned());
  }
}
