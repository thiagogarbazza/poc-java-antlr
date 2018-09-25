package com.github.thiagogarbazza.expressionresolver.domain;

import org.junit.Before;
import org.junit.Test;

import static java.math.BigDecimal.TEN;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;

public class ExpressionContextTest {

  private ExpressionContext expressionContext;

  @Before
  public void setUp() {
    expressionContext = new ExpressionContext();
  }

  @Test(expected = NullPointerException.class)
  public void testGetVariableNameCanNotBeBlank() {
    expressionContext.get(SPACE, Object.class);
  }

  @Test(expected = NullPointerException.class)
  public void testGetVariableNameCanNotBeEmpty() {
    expressionContext.get(EMPTY, Object.class);
  }

  @Test(expected = NullPointerException.class)
  public void testGetVariableNameCanNotBeNull() {
    expressionContext.get(null, Object.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetVariableNotInstanceValid() {
    final String variable = "variableNotInstanceValid";
    expressionContext.set(variable, TEN);
    expressionContext.get(variable, String.class);
  }

  @Test(expected = NullPointerException.class)
  public void testGetVariableNotPresentInContext() {
    final String variable = "variableNotPresentInContext";
    expressionContext.get(variable, Object.class);
  }

  @Test(expected = NullPointerException.class)
  public void testSetVariableNameCanNotBeBlank() {
    expressionContext.set(SPACE, null);
  }

  @Test(expected = NullPointerException.class)
  public void testSetVariableNameCanNotBeEmpty() {
    expressionContext.set(EMPTY, null);
  }

  @Test(expected = NullPointerException.class)
  public void testSetVariableNameCanNotBeNull() {
    expressionContext.set(null, null);
  }
}
