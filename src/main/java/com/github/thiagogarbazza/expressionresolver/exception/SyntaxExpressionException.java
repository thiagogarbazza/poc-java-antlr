package com.github.thiagogarbazza.expressionresolver.exception;

import static com.github.thiagogarbazza.expressionresolver.util.PropertieUtil.messageProperty;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class SyntaxExpressionException extends ExpressionException {

  public SyntaxExpressionException(final String message) {
    super(message);
  }

  public SyntaxExpressionException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static void isExpressionNotEmpty(final String expression) {
    if (isBlank(expression)) {
      throw new SyntaxExpressionException(messageProperty("validation.expression.not-be-null-or-empty"));
    }
  }
}
