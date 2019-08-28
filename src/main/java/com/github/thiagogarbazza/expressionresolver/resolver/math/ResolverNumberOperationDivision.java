package com.github.thiagogarbazza.expressionresolver.resolver.math;

import com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResult;

public class ResolverNumberOperationDivision {

  private static ResolverNumberOperationDivision instance;

  public BigDecimal resolver(final BigDecimal left, final BigDecimal right) {
    final BigDecimal result = left.divide(right, NormalizeResult.PRECISAO_E_ARREDONDAMENTO);

    return normalizeResult(result);
  }

  public static ResolverNumberOperationDivision getResolverNumberOperationDivision() {
    if (instance == null) {
      synchronized (ResolverNumberOperationDivision.class) {
        if (instance == null) {
          instance = new ResolverNumberOperationDivision();
        }
      }
    }

    return instance;
  }
}
