package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.toBigDecimal;

public class SingleValueNumberIT extends AbstractIT {

  @Test
  public void verifySingleValueNumberNegative() {
    assertExpression(new Expression("return 5;"), new Result(toBigDecimal("5")));
  }

  @Test
  public void verifySingleValueNumberPositive() {
    assertExpression(new Expression("return -11;"), new Result(toBigDecimal("-11")));
  }
}
