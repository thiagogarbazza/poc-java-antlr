package com.github.thiagogarbazza.expressionresolver.exception;

public class ExpressionException extends RuntimeException {

  public ExpressionException(String message) {
    super(message);
  }

  public ExpressionException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
