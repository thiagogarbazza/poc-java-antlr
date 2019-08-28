package com.github.thiagogarbazza.expressionresolver.resolver.math;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResult;

public class ResolverNumberOperationSubtraction {

  private static ResolverNumberOperationSubtraction instance;

  public BigDecimal resolver(final BigDecimal left, final BigDecimal right) {
    final BigDecimal result = left.subtract(right);

    return normalizeResult(result);
  }

  public static ResolverNumberOperationSubtraction getResolverNumberOperationSubtraction() {
    if (instance == null) {
      synchronized (ResolverNumberOperationSubtraction.class) {
        if (instance == null) {
          instance = new ResolverNumberOperationSubtraction();
        }
      }
    }

    return instance;
  }
}
