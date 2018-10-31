package com.github.thiagogarbazza.expressionresolver.functionresolver.tan;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionTan {

  private static final ResolverFunctionTan INSTANCE = new ResolverFunctionTan();

  public BigDecimal resolver(BigDecimal value) {
    final double tan = Math.tan(value.doubleValue());

    return normalizeResult(tan);
  }

  public static ResolverFunctionTan getResolverFunctionTan() {
    return INSTANCE;
  }
}

