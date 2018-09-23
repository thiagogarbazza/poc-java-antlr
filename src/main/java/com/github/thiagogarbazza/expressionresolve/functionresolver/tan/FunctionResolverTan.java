package com.github.thiagogarbazza.expressionresolve.functionresolver.tan;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverTan {

  private static final FunctionResolverTan INSTANCE = new FunctionResolverTan();

  public BigDecimal resolver(BigDecimal value) {
    final double tan = Math.tan(value.doubleValue());

    return normalizeResult(tan);
  }

  public static FunctionResolverTan getFunctionResolverTan() {
    return INSTANCE;
  }
}

