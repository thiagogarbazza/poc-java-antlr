package com.github.thiagogarbazza.expressionresolver.functionresolver.sqrt;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionSqrt {

  private static final ResolverFunctionSqrt INSTANCE = new ResolverFunctionSqrt();

  public BigDecimal resolver(BigDecimal value) {
    final double sqrt = Math.sqrt(value.doubleValue());

    return normalizeResult(sqrt);
  }

  public static ResolverFunctionSqrt getResolverFunctionSqrt() {
    return INSTANCE;
  }
}

