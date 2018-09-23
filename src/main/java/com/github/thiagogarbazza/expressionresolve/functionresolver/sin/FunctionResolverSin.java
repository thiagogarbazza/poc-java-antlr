package com.github.thiagogarbazza.expressionresolve.functionresolver.sin;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverSin {

  private static final FunctionResolverSin INSTANCE = new FunctionResolverSin();

  public BigDecimal resolver(BigDecimal value) {
    final double asin = Math.sin(value.doubleValue());

    return BigDecimal.valueOf(asin);
  }

  public static FunctionResolverSin getFunctionResolverSin() {
    return INSTANCE;
  }
}
