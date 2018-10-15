package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.domain.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

public class SingleValueBooleanIT extends AbstractIT {

  @Test
  public void verifySingleValueBooleanFALSE() {
    assertExpression(new Expression("return false;"), new Result(false));
  }

  @Test
  public void verifySingleValueBooleanTRUE() {
    assertExpression(new Expression("return true;"), new Result(true));
  }
}
