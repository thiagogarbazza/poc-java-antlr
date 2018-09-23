package com.github.thiagogarbazza.expressionresolve.functionresolver.ln;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverLn {

  private static final FunctionResolverLn INSTANCE = new FunctionResolverLn();

  public BigDecimal resolver(BigDecimal value) {
    final double ln = Math.log(value.doubleValue());

    return normalizeResult(ln);
  }

  public static FunctionResolverLn getFunctionResolverLn() {
    return INSTANCE;
  }
}

