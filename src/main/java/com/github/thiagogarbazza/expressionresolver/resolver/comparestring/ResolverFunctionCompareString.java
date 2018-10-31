package com.github.thiagogarbazza.expressionresolver.resolver.comparestring;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.resolver.NormalizeResult.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionCompareString {

  private static final ResolverFunctionCompareString INSTANCE = new ResolverFunctionCompareString();

  public BigDecimal resolver(String left, String right) {
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  public static ResolverFunctionCompareString getResolverFunctionCompareString() {
    return INSTANCE;
  }
}
