package com.github.thiagogarbazza.expressionresolve.functionresolver.comparestring;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.NormalizeResult.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverCompareString {

  private static final FunctionResolverCompareString INSTANCE = new FunctionResolverCompareString();

  public BigDecimal resolver(String left, String right) {
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  public static FunctionResolverCompareString getFunctionResolverCompareString() {
    return INSTANCE;
  }
}
