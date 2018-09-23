package com.github.thiagogarbazza.expressionresolve.functionresolver.month;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverMonth {

  private static final FunctionResolverMonth INSTANCE = new FunctionResolverMonth();

  public BigDecimal resolver(LocalDate value) {
    final int day = value.getMonthValue();

    return normalizeResult(day);
  }

  public static FunctionResolverMonth getFunctionResolverMonth() {
    return INSTANCE;
  }
}
