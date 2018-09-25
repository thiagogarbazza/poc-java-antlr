package com.github.thiagogarbazza.expressionresolver.exception;

public class SyntaxException extends ExpressionException {

  public SyntaxException(final String message) {
    super(message);
  }

  public SyntaxException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
