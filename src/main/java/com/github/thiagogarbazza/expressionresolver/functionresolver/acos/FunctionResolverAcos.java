package com.github.thiagogarbazza.expressionresolver.functionresolver.acos;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverAcos {

  private static final FunctionResolverAcos INSTANCE = new FunctionResolverAcos();

  public BigDecimal resolver(BigDecimal value) {
    final double acos = Math.acos(value.doubleValue());

    return normalizeResult(acos);
  }

  public static FunctionResolverAcos getFunctionResolverAcos() {
    return INSTANCE;
  }
}
