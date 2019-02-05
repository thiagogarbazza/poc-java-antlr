package com.github.thiagogarbazza.expressionresolver;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class ExpressionTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testExpressionCanNotBeBlank() {
    thrown.expectMessage("Expression can not be null or empty.");
    thrown.expect(IllegalArgumentException.class);

    new Expression(SPACE);
  }

  @Test
  public void testExpressionCanNotBeEmpty() {
    thrown.expectMessage("Expression can not be null or empty.");
    thrown.expect(IllegalArgumentException.class);

    new Expression(EMPTY);
  }

  @Test
  public void testExpressionCanNotBeNull() {
    thrown.expectMessage("Expression can not be null or empty.");
    thrown.expect(NullPointerException.class);

    new Expression(null);
  }
}
