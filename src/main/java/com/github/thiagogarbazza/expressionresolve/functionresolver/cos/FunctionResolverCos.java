package com.github.thiagogarbazza.expressionresolve.functionresolver.cos;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverCos {

  private static final FunctionResolverCos INSTANCE = new FunctionResolverCos();

  public BigDecimal resolver(BigDecimal value) {
    final double cos = Math.cos(value.doubleValue());

    return BigDecimal.valueOf(cos);
  }

  public static FunctionResolverCos getFunctionResolverCos() {
    return INSTANCE;
  }
}
