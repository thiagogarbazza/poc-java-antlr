package com.github.thiagogarbazza.expressionresolver.it;

import com.github.thiagogarbazza.expressionresolver.ExpressionContext;
import com.github.thiagogarbazza.expressionresolver.ExpressionInterpreter;
import com.github.thiagogarbazza.expressionresolver.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.joining;

class RunnerExpressionInterpreter {

  public static void main(String[] args) {
    try {
      final String expression = getFormula();
      //final String expression = "$var = 11; return $var;";

      final ExpressionContext context = new ExpressionContext()
        .set("$response", new ArrayList<>());

      final Result result = ExpressionInterpreter.getExpressionInterpreter().toInterpret(expression, context);
      System.out.println(format("Result is: `{0}`", result));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String getFormula() throws IOException {
    try (
      final InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("expression.expression");
      final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
      final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    ) {
      return bufferedReader.lines().collect(joining(getProperty("line.separator")));
    }
  }
}
