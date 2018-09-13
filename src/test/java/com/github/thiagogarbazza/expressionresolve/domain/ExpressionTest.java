package com.github.thiagogarbazza.expressionresolve.domain;

import org.junit.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class ExpressionTest {

  @Test(expected = NullPointerException.class)
  public void testExpressionCanNotBeBlank() {
    new Expression(SPACE);
  }

  @Test(expected = NullPointerException.class)
  public void testExpressionCanNotBeEmpty() {
    new Expression(EMPTY);
  }

  @Test(expected = NullPointerException.class)
  public void testExpressionCanNotBeNull() {
    new Expression(null);
  }
}
