package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;

public class ArrayValueNumberIT extends AbstractIT {

  @Test
  public void verifyArrayValueNumberMany() {
    assertExpression(new Expression("return [5, -11, 7];"), new Result(asList(new BigDecimal("5"), new BigDecimal("-11"), new BigDecimal("7"))));
  }
}
