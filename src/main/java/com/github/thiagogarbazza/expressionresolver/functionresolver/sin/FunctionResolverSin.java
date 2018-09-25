package com.github.thiagogarbazza.expressionresolver.functionresolver.sin;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverSin {

  private static final FunctionResolverSin INSTANCE = new FunctionResolverSin();

  public BigDecimal resolver(BigDecimal value) {
    final double sin = Math.sin(value.doubleValue());

    return normalizeResult(sin);
  }

  public static FunctionResolverSin getFunctionResolverSin() {
    return INSTANCE;
  }
}
