package com.github.thiagogarbazza.expressionresolve.functionresolver.log;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverLog {

  private static final FunctionResolverLog INSTANCE = new FunctionResolverLog();

  public BigDecimal resolver(BigDecimal value) {
    final double log = Math.log10(value.doubleValue());

    return normalizeResult(log);
  }

  public static FunctionResolverLog getFunctionResolverLog() {
    return INSTANCE;
  }
}

