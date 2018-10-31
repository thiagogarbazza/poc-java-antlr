package com.github.thiagogarbazza.expressionresolver.functionresolver.day;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionDay {

  private static final ResolverFunctionDay INSTANCE = new ResolverFunctionDay();

  public BigDecimal resolver(LocalDate value) {
    final int day = value.getDayOfMonth();

    return normalizeResult(day);
  }

  public static ResolverFunctionDay getResolverFunctionDay() {
    return INSTANCE;
  }
}
