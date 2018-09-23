package com.github.thiagogarbazza.expressionresolve.functionresolver.acos;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverAcos {

  private static final FunctionResolverAcos INSTANCE = new FunctionResolverAcos();

  public BigDecimal resolver(BigDecimal value) {
    final double acos = Math.acos(value.doubleValue());

    return BigDecimal.valueOf(acos);
  }

  public static FunctionResolverAcos getFunctionResolverAcos() {
    return INSTANCE;
  }
}
