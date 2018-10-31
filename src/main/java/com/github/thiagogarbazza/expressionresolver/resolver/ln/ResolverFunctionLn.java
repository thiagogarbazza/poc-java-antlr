package com.github.thiagogarbazza.expressionresolver.resolver.ln;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionLn {

  private static final ResolverFunctionLn INSTANCE = new ResolverFunctionLn();

  public BigDecimal resolver(BigDecimal value) {
    final double ln = Math.log(value.doubleValue());

    return normalizeResult(ln);
  }

  public static ResolverFunctionLn getResolverFunctionLn() {
    return INSTANCE;
  }
}

