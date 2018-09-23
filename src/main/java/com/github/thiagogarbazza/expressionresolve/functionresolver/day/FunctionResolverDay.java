package com.github.thiagogarbazza.expressionresolve.functionresolver.day;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolve.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverDay {

  private static final FunctionResolverDay INSTANCE = new FunctionResolverDay();

  public BigDecimal resolver(LocalDate value) {
    final int day = value.getDayOfMonth();

    return normalizeResult(day);
  }

  public static FunctionResolverDay getFunctionResolverDay() {
    return INSTANCE;
  }
}
