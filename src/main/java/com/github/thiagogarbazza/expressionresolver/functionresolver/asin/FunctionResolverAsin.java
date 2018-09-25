package com.github.thiagogarbazza.expressionresolver.functionresolver.asin;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverAsin {

  private static final FunctionResolverAsin INSTANCE = new FunctionResolverAsin();

  public BigDecimal resolver(BigDecimal value) {
    final double asin = Math.asin(value.doubleValue());

    return normalizeResult(asin);
  }

  public static FunctionResolverAsin getFunctionResolverAsin() {
    return INSTANCE;
  }
}
