package com.github.thiagogarbazza.expressionresolver.it;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.ExpressionInterpreter;
import com.github.thiagogarbazza.expressionresolver.domain.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class AbstractIT {

  protected ExpressionContext context;
  protected ExpressionInterpreter interpreter;

  @Before
  public void before() {
    interpreter = ExpressionInterpreter.getExpressionInterpreter();
    context = new ExpressionContext();
  }

  protected void assertExpression(final Expression expression, final Result expected) {
    final Result result = interpreter.toInterpret(expression, context);

    assertEquals("Result value invalid.", expected, result);
  }
}
