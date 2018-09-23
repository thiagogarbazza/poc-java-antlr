package com.github.thiagogarbazza.expressionresolve.functionresolver.sqrt;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverSqrt {

  private static final FunctionResolverSqrt INSTANCE = new FunctionResolverSqrt();

  public BigDecimal resolver(BigDecimal value) {
    final double sqrt = Math.sqrt(value.doubleValue());

    return BigDecimal.valueOf(sqrt);
  }

  public static FunctionResolverSqrt getFunctionResolverSqrt() {
    return INSTANCE;
  }
}

