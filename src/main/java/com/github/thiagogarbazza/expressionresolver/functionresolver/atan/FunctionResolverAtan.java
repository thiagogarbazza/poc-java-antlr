package com.github.thiagogarbazza.expressionresolver.functionresolver.atan;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverAtan {

  private static final FunctionResolverAtan INSTANCE = new FunctionResolverAtan();

  public BigDecimal resolver(BigDecimal value) {
    final double atan = Math.atan(value.doubleValue());

    return normalizeResult(atan);
  }

  public static FunctionResolverAtan getFunctionResolverAtan() {
    return INSTANCE;
  }
}

