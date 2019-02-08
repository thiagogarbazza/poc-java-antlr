package com.github.thiagogarbazza.expressionresolver.it;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.ExpressionInterpreter;
import com.github.thiagogarbazza.expressionresolver.Result;
import com.github.thiagogarbazza.expressionresolver.exception.ExpressionException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static java.text.MessageFormat.format;

class RunnerExpressionInterpreter {

  public static void main(String[] args) {
    final Expression expression = new Expression(getFormula());
    //final Expression expression  = new Expression("return 55 * 10;");

    final ExpressionContext context = new ExpressionContext();
    context.set("$response", new ArrayList<>());

    try {
      final Result result = ExpressionInterpreter.getExpressionInterpreter().toInterpret(expression, context);
      System.out.println(format("Result is: `{0}`", result));
    } catch (ExpressionException e) {
      e.printStackTrace();
    }
  }

  private static String getFormula() {
    final InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("expression.expression");
    Scanner s = new Scanner(is).useDelimiter("\\A");
    return s.hasNext() ? s.next() : "";
  }
}
