package com.github.thiagogarbazza.expressionresolve.functionresolver.comparenumber;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.NormalizeResult.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverCompareNumber {

  private static final FunctionResolverCompareNumber INSTANCE = new FunctionResolverCompareNumber();

  public BigDecimal resolver(BigDecimal left, BigDecimal right) {
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  public static FunctionResolverCompareNumber getFunctionResolverCompareNumber() {
    return INSTANCE;
  }
}
