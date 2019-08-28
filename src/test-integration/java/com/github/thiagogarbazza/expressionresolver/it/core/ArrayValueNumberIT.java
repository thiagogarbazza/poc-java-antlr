package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.toBigDecimal;
import static java.util.Arrays.asList;

public class ArrayValueNumberIT extends AbstractIT {

  @Test
  public void verifyArrayValueNumberMany() {
    assertExpression(new Expression("return [5, -11, 7];"), new Result(asList(toBigDecimal("5"), toBigDecimal("-11"), toBigDecimal("7"))));
  }
}
