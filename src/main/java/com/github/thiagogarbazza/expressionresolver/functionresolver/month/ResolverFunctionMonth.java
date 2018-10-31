package com.github.thiagogarbazza.expressionresolver.functionresolver.month;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionMonth {

  private static final ResolverFunctionMonth INSTANCE = new ResolverFunctionMonth();

  public BigDecimal resolver(LocalDate value) {
    final int day = value.getMonthValue();

    return normalizeResult(day);
  }

  public static ResolverFunctionMonth getResolverFunctionMonth() {
    return INSTANCE;
  }
}
