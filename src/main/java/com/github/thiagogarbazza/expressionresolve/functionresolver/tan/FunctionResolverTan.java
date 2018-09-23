package com.github.thiagogarbazza.expressionresolve.functionresolver.tan;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverTan {

  private static final FunctionResolverTan INSTANCE = new FunctionResolverTan();

  public BigDecimal resolver(BigDecimal value) {
    final double tan = Math.tan(value.doubleValue());

    return BigDecimal.valueOf(tan);
  }

  public static FunctionResolverTan getFunctionResolverTan() {
    return INSTANCE;
  }
}

