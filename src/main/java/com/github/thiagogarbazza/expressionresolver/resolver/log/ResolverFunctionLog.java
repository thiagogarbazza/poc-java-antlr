package com.github.thiagogarbazza.expressionresolver.resolver.log;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionLog {

  private static final ResolverFunctionLog INSTANCE = new ResolverFunctionLog();

  public BigDecimal resolver(BigDecimal value) {
    final double log = Math.log10(value.doubleValue());

    return normalizeResult(log);
  }

  public static ResolverFunctionLog getResolverFunctionLog() {
    return INSTANCE;
  }
}

