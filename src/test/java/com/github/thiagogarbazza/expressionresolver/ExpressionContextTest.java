package com.github.thiagogarbazza.expressionresolver;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.Assert.assertEquals;

public class ExpressionContextTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private ExpressionContext expressionContext;

  @Before
  public void setUp() {
    expressionContext = new ExpressionContext();
  }

  @Test
  public void verifyGetAnyTypeBoolean() {
    expressionContext.set("$anyTypeBoolean", true);

    assertEquals(true, expressionContext.get("$anyTypeBoolean"));
  }

  @Test
  public void verifyGetAnyTypeDate() {
    expressionContext.set("$anyTypeDate", LocalDate.of(2016, 2, 29));

    assertEquals(LocalDate.of(2016, 2, 29), expressionContext.get("$anyTypeDate"));
  }

  @Test
  public void verifyGetAnyTypeNumber() {
    expressionContext.set("$anyTypeNumber", new BigDecimal("3.14159265359"));

    assertEquals(new BigDecimal("3.14159265359"), expressionContext.get("$anyTypeNumber"));
  }

  @Test
  public void verifyGetAnyTypeText() {
    expressionContext.set("$anyTypeText", "any text value");

    assertEquals("any text value", expressionContext.get("$anyTypeText"));
  }

  @Test
  public void verifyGetCurrentDate() {
    expressionContext.setCurrentDate(LocalDate.of(2018, 1, 31));

    assertEquals(LocalDate.of(2018, 1, 31), expressionContext.getCurrentDate());
  }

  @Test
  public void verifyGetCurrentDateNotPresentInContext() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("The variable $CURRENT_DATE is not present in the execution context of the expression.");

    expressionContext.setCurrentDate(null);

    expressionContext.getCurrentDate();
  }

  @Test
  public void verifyGetVariableNameCanNotBeBlank() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("The context variable \" \" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.get(SPACE);
  }

  @Test
  public void verifyGetVariableNameCanNotBeEmpty() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("The context variable \"\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.get(EMPTY);
  }

  @Test
  public void verifyGetVariableNameCanNotBeNull() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("The context variable \"null\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.get(null);
  }

  @Test
  public void verifyGetVariableNotPresentInContext() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("The variable $anyVariableNotPresentInContext is not present in the execution context of the expression.");

    expressionContext.get("$anyVariableNotPresentInContext");
  }

  @Test
  public void verifySetVariableNameCanNotBeBlank() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("The context variable \" \" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.set(SPACE, "any text value");
  }

  @Test
  public void verifySetVariableNameCanNotBeEmpty() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("The context variable \"\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.set(EMPTY, "any text value");
  }

  @Test
  public void verifySetVariableNameCanNotBeNull() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("The context variable \"null\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.set(null, "any text value");
  }

  @Test
  public void verifySetVariableNameCanNotVontainCharacter00() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("The context variable \"$any-variable\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.set("$any-variable", "any text value");
  }

  @Test
  public void verifySetVariableNameCanNotVontainCharacter01() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("The context variable \"$anyVáriable\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.set("$anyVáriable", "any text value");
  }

  @Test
  public void verifySetVariableNameShouldStartWith$() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("The context variable \"anyVariable\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.set("anyVariable", "any text value");
  }

  @Test
  public void verifySetVariableNameShouldStartWith$FollowedByLetter() {
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("The context variable \"$4anyVariable\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
      + " It should start with \"$\" followed by letter. May contain letters and numbers.");

    expressionContext.set("$4anyVariable", "any text value");
  }
}
