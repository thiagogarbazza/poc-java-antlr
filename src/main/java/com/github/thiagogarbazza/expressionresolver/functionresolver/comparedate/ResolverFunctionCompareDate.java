package com.github.thiagogarbazza.expressionresolver.functionresolver.comparedate;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionCompareDate {

  private static final ResolverFunctionCompareDate INSTANCE = new ResolverFunctionCompareDate();

  public BigDecimal resolver(LocalDate left, LocalDate right) {
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  public static ResolverFunctionCompareDate getResolverFunctionCompareDate() {
    return INSTANCE;
  }
}
