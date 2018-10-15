package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import java.time.LocalDate;

import static java.util.Arrays.asList;

public class ArrayValueDateIT extends AbstractIT {

  @Test
  public void verifyArrayValueDate() {
    assertExpression(new Expression("return [2018/01/01];"), new Result(asList(LocalDate.of(2018, 1, 1))));
  }

  @Test
  public void verifyArrayValueDateMany() {
    assertExpression(new Expression("return [2018/01/01, 1900/02/28];"), new Result(asList(LocalDate.of(2018, 1, 1), LocalDate.of(1900, 2, 28))));
  }
}
