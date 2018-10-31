package com.github.thiagogarbazza.expressionresolver.resolver.asin;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionAsin {

  private static final ResolverFunctionAsin INSTANCE = new ResolverFunctionAsin();

  public BigDecimal resolver(BigDecimal value) {
    final double asin = Math.asin(value.doubleValue());

    return normalizeResult(asin);
  }

  public static ResolverFunctionAsin getResolverFunctionAsin() {
    return INSTANCE;
  }
}
