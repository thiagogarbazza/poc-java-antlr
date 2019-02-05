package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import java.time.LocalDate;

public class SingleValueDateIT extends AbstractIT {

  @Test
  public void verifySingleValueDate() {
    assertExpression(new Expression("return 2018/01/01;"), new Result(LocalDate.of(2018, 1, 1)));
  }
}
