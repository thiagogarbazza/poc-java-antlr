package com.github.thiagogarbazza.expressionresolve.functionresolver.comparedate;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.NormalizeResult.normalizeResultCompare;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverCompareDate {

  private static final FunctionResolverCompareDate INSTANCE = new FunctionResolverCompareDate();

  public BigDecimal resolver(LocalDate left, LocalDate right) {
    final Integer result = left.compareTo(right);

    return normalizeResultCompare(result);
  }

  public static FunctionResolverCompareDate getFunctionResolverCompareDate() {
    return INSTANCE;
  }
}
