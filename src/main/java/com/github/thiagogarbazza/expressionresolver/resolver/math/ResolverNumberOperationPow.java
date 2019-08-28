package com.github.thiagogarbazza.expressionresolver.resolver.math;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResult;

public class ResolverNumberOperationPow {

  private static ResolverNumberOperationPow instance;

  public BigDecimal resolver(final BigDecimal left, final BigDecimal right) {
    final BigDecimal result = left.pow(right.intValue());

    return normalizeResult(result);
  }

  public static ResolverNumberOperationPow getResolverNumberOperationPow() {
    if (instance == null) {
      synchronized (ResolverNumberOperationPow.class) {
        if (instance == null) {
          instance = new ResolverNumberOperationPow();
        }
      }
    }

    return instance;
  }
}
