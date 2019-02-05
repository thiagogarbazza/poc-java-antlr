package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

public class SingleValueStringIT extends AbstractIT {

  @Test
  public void verifySingleValueStringSingleQuote() {
    assertExpression(new Expression("return 'any-string';"), new Result("any-string"));
  }

  @Test
  public void verifySingleValueStringDoubleQuote() {
    assertExpression(new Expression("return \"any-string\";"), new Result("any-string"));
  }
}
