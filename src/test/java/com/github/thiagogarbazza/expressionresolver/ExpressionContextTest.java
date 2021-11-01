package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.exception.RunnableExpressionException;
import com.github.thiagogarbazza.expressionresolver.exception.SyntaxExpressionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionContextTest {

  private ExpressionContext expressionContext;

  @BeforeEach
  void beforeEach() {
    expressionContext = new ExpressionContext();
  }

  @Nested
  class GetVariable {

    @Test
    void verifyDataTypeBoolean() {
      expressionContext.set("$variable", true);

      assertEquals(true, expressionContext.get("$variable"));
    }

    @Test
    void verifyDataTypeDate() {
      expressionContext.set("$variable", LocalDate.of(2016, 2, 29));

      assertEquals(LocalDate.of(2016, 2, 29), expressionContext.get("$variable"));
    }

    @Test
    void verifyDataTypeNumber() {
      expressionContext.set("$variable", new BigDecimal("3.14159265359"));

      assertEquals(new BigDecimal("3.14159265359"), expressionContext.get("$variable"));
    }

    @Test
    void verifyDataTypeObject() {
      expressionContext.set("$variable", new HashMap<String, Object>() {{put("key", "value");}});

      assertEquals(new HashMap<String, Object>() {{put("key", "value");}}, expressionContext.get("$variable"));
    }

    @Test
    void verifyDataTypeText() {
      expressionContext.set("$variable", "any text value");

      assertEquals("any text value", expressionContext.get("$variable"));
    }

    @Test
    void verifyVariableNameCanNotBeBlank() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.get(SPACE));
      assertEquals("The context variable \" \" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNameCanNotBeEmpty() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.get(EMPTY));
      assertEquals("The context variable \"\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNameCanNotBeNull() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.get(null));
      assertEquals("The context variable \"null\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNotPresentInContext() {
      final Exception exception = assertThrows(RunnableExpressionException.class, () -> expressionContext.get("$variable"));
      assertEquals("The variable $variable is not present in the execution context of the expression.", exception.getMessage());
    }
  }

  @Nested
  class SetVariable {

    @Test
    void verifyVariableNameCanNotBeBlank() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.set(SPACE, "value"));
      assertEquals("The context variable \" \" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNameCanNotBeEmpty() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.set(EMPTY, "value"));
      assertEquals("The context variable \"\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNameCanNotBeNull() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.set(null, "value"));
      assertEquals("The context variable \"null\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNameCanNotVontainCharacter00() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.set("$any-variable", "any text value"));
      assertEquals("The context variable \"$any-variable\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNameCanNotVontainCharacter01() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.set("$anyVáriable", "any text value"));
      assertEquals("The context variable \"$anyVáriable\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNameShouldStartWith$() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.set("anyVariable", "any text value"));
      assertEquals("The context variable \"anyVariable\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }

    @Test
    void verifyVariableNameShouldStartWith$FollowedByLetter() {
      final Exception exception = assertThrows(SyntaxExpressionException.class, () -> expressionContext.set("$4anyVariable", "any text value"));
      assertEquals("The context variable \"$4anyVariable\" is out of the standard \"^$[a-zA-Z][a-zA-Z0-9_]*$\"."
        + " It should start with \"$\" followed by letter. May contain letters and numbers.", exception.getMessage());
    }
  }
}
