package com.github.thiagogarbazza.expressionresolver.resolver.math;

import com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResult;

public class ResolverNumberOperationMultiply {

  private static ResolverNumberOperationMultiply instance;

  public BigDecimal resolver(final BigDecimal left, final BigDecimal right) {
    final BigDecimal result = left.multiply(right);

    return normalizeResult(result);
  }

  public static ResolverNumberOperationMultiply getResolverNumberOperationMultiply() {
    if (instance == null) {
      synchronized (ResolverNumberOperationMultiply.class) {
        if (instance == null) {
          instance = new ResolverNumberOperationMultiply();
        }
      }
    }

    return instance;
  }
}
