package com.github.thiagogarbazza.expressionresolver.resolver.cos;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionCos {

  private static final ResolverFunctionCos INSTANCE = new ResolverFunctionCos();

  public BigDecimal resolver(BigDecimal value) {
    final double cos = Math.cos(value.doubleValue());

    return normalizeResult(cos);
  }

  public static ResolverFunctionCos getResolverFunctionCos() {
    return INSTANCE;
  }
}
