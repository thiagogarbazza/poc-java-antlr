package com.github.thiagogarbazza.expressionresolver.it.core;

import com.github.thiagogarbazza.expressionresolver.Expression;
import com.github.thiagogarbazza.expressionresolver.domain.Result;
import com.github.thiagogarbazza.expressionresolver.it.AbstractIT;
import org.junit.Test;

import static java.util.Arrays.asList;

public class ArrayValueStringIT extends AbstractIT {

  @Test
  public void verifyArrayValueString() {
    assertExpression(new Expression("return ['any-string', 'another-string'];"), new Result(asList("any-string", "another-string")));
  }

  @Test
  public void verifyArrayValueStringDoubleQuote() {
    assertExpression(new Expression("return [\"any-string\"];"), new Result(asList("any-string")));
  }

  @Test
  public void verifyArrayValueStringSingleQuote() {
    assertExpression(new Expression("return ['any-string'];"), new Result(asList("any-string")));
  }
}
