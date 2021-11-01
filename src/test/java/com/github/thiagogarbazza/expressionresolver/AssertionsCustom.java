package com.github.thiagogarbazza.expressionresolver;

import com.github.thiagogarbazza.expressionresolver.exception.ExpressionException;
import lombok.experimental.UtilityClass;

import static com.github.thiagogarbazza.expressionresolver.util.json.JsonUtils.objectToJsonNode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@UtilityClass
public class AssertionsCustom {

  public static void assertNotThrowException(final Exception exception) {
    assertNull(exception, "There is an exception thrown.");
  }

  public static void assertResultEquals(final Result expected, final Result actual) {
    assertNotNull(expected, "The expected result can't be null.");
    assertNotNull(actual, "The actual result can't be null.");
    assertEquals(objectToJsonNode(expected.getValue()), objectToJsonNode(actual.getValue()), "The expected result is diff from actual.");
  }

  public static void assertThrowException(final String messageExpected, final Exception exception) {
    assertNotNull(exception, "Should have thrown an exception.");
    assertTrue(exception instanceof ExpressionException, "Should have thrown an exception instance of ExpressionException.");
    assertEquals(messageExpected, exception.getMessage(), "Should have thrown an exception with message.");
  }
}
