package com.github.thiagogarbazza.expressionresolver.functionresolver.year;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.github.thiagogarbazza.expressionresolver.functionresolver.NormalizeResult.normalizeResult;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionYear {

  private static final ResolverFunctionYear INSTANCE = new ResolverFunctionYear();

  public BigDecimal resolver(LocalDate value) {
    final int day = value.getYear();

    return normalizeResult(day);
  }

  public static ResolverFunctionYear getResolverFunctionYear() {
    return INSTANCE;
  }
}
