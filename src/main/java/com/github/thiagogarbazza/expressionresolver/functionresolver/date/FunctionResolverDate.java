package com.github.thiagogarbazza.expressionresolver.functionresolver.date;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FunctionResolverDate {

  private static final FunctionResolverDate INSTANCE = new FunctionResolverDate();

  public LocalDate resolver(BigDecimal year, BigDecimal month, BigDecimal day) {
    return LocalDate.of(year.intValue(), month.intValue(), day.intValue());
  }

  public static FunctionResolverDate getFunctionResolverDate() {
    return INSTANCE;
  }
}

