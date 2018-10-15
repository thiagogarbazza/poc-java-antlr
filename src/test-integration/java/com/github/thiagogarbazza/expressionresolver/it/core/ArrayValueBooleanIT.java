package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import static java.util.Arrays.asList;

public class ArrayValueBooleanIT extends AbstractIT {

  @Test
  public void verifyArrayValueBooleanFALSE() {
    assertExpression(new Expression("return [ false ];"), new Result(asList(false)));
  }

  @Test
  public void verifyArrayValueBooleanMany() {
    assertExpression(new Expression("return [ true, false, true ];"), new Result(asList(true, false, true)));
  }

  @Test
  public void verifyArrayValueBooleanTRUE() {
    assertExpression(new Expression("return [ true ];"), new Result(asList(true)));
  }
}
