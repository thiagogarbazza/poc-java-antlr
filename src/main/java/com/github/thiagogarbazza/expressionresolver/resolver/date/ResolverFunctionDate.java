package com.github.thiagogarbazza.expressionresolver.resolver.date;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResolverFunctionDate {

  private static final ResolverFunctionDate INSTANCE = new ResolverFunctionDate();

  public LocalDate resolver(BigDecimal year, BigDecimal month, BigDecimal day) {
    return LocalDate.of(year.intValue(), month.intValue(), day.intValue());
  }

  public static ResolverFunctionDate getResolverFunctionDate() {
    return INSTANCE;
  }
}

