package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import java.math.BigDecimal;

public class SingleValueNumberIT extends AbstractIT {

  @Test
  public void verifySingleValueNumberNegative() {
    assertExpression(new Expression("return 5;"), new Result(new BigDecimal("5")));
  }

  @Test
  public void verifySingleValueNumberPositive() {
    assertExpression(new Expression("return -11;"), new Result(new BigDecimal("-11")));
  }
}
