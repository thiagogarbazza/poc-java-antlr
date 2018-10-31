package com.github.thiagogarbazza.expressionresolver.functionresolver.comparenumber;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionCompareNumber {

  private static final ResolverFunctionCompareNumber INSTANCE = new ResolverFunctionCompareNumber();

  public BigDecimal resolver(BigDecimal left, BigDecimal right) {
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  public static ResolverFunctionCompareNumber getResolverFunctionCompareNumber() {
    return INSTANCE;
  }
}
