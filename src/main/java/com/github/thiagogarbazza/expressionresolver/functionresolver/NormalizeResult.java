package com.github.thiagogarbazza.expressionresolver.functionresolver;

import java.math.BigDecimal;

public class NormalizeResult {

  public static BigDecimal normalizeResult(final int value) {
    final BigDecimal result = BigDecimal.valueOf(value);

    return normalizeResult(result);
  }

  public static BigDecimal normalizeResult(final BigDecimal value) {
    return value;
  }

  public static BigDecimal normalizeResult(final double value) {
    final BigDecimal result = BigDecimal.valueOf(value);

    return normalizeResult(result);
  }

  public static BigDecimal normalizeResultCompare(final int value) {
    if (value == 0) {
      return normalizeResult(0);
    }

    return value < 0
      ? normalizeResult(-1)
      : normalizeResult(1);
  }
}

