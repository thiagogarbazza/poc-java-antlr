package com.github.thiagogarbazza.expressionresolver.exception;

import java.util.Map;

import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.messageProperty;

public class RunnableExpressionException extends ExpressionException {

  public RunnableExpressionException(final String message) {
    super(message);
  }

  public RunnableExpressionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static void isDataTypeOfObject(final Object value, final String variable) {
    if (!(value instanceof Map)) {
      throw new RunnableExpressionException(messageProperty("validation.context.variable.is-not-object", variable));
    }
  }
}
